package br.com.softhouse.dende.repositories;

import br.com.softhouse.dende.model.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Repositorio {

    private static final Repositorio instance = new Repositorio();

    private final Map<Long, Usuario> usuarios = new HashMap<>();
    private final Map<Long, Organizador> organizadores = new HashMap<>();
    private final Map<Long, Evento> eventos = new HashMap<>();
    private final Map<Long, Ingresso> ingressos = new HashMap<>();

    private final AtomicLong usuarioId = new AtomicLong(1);
    private final AtomicLong organizadorId = new AtomicLong(1);
    private final AtomicLong eventoId = new AtomicLong(1);
    private final AtomicLong ingressoId = new AtomicLong(1);

    private Repositorio() {}

    public static Repositorio getInstance() {
        return instance;
    }

    // =========================================================
    // EMAIL ÚNICO GLOBAL
    // =========================================================
    private void validarEmailUnico(String email) {
        boolean existeUsuario = usuarios.values().stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));

        boolean existeOrganizador = organizadores.values().stream()
                .anyMatch(o -> o.getEmail().equalsIgnoreCase(email));

        if (existeUsuario || existeOrganizador)
            throw new IllegalArgumentException("E-mail já cadastrado na plataforma");
    }

    // =========================================================
    // USUÁRIO
    // =========================================================
    public Usuario salvarUsuario(Usuario u) {
        validarEmailUnico(u.getEmail());
        u.setId(usuarioId.getAndIncrement());
        usuarios.put(u.getId(), u);
        return u;
    }

    public Usuario buscarUsuario(Long id) {
        Usuario u = usuarios.get(id);
        if (u == null) throw new IllegalArgumentException("Usuário não encontrado");
        return u;
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarios.values().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }

    // =========================================================
    // ORGANIZADOR
    // =========================================================
    public Organizador salvarOrganizador(Organizador o) {
        validarEmailUnico(o.getEmail());
        o.setId(organizadorId.getAndIncrement());
        organizadores.put(o.getId(), o);
        return o;
    }

    public Organizador buscarOrganizador(Long id) {
        Organizador o = organizadores.get(id);
        if (o == null) throw new IllegalArgumentException("Organizador não encontrado");
        return o;
    }

    public boolean organizadorTemEventoAtivoOuEmExecucao(Long organizadorId) {
        LocalDateTime agora = LocalDateTime.now();
        return eventos.values().stream()
                .anyMatch(e ->
                        e.getOrganizadorId().equals(organizadorId)
                        && e.isAtivo()
                        && e.getFim().isAfter(agora)
                );
    }

    // =========================================================
    // EVENTO
    // =========================================================
    public Evento salvarEvento(Evento e) {
        e.setId(eventoId.getAndIncrement());
        eventos.put(e.getId(), e);
        return e;
    }

    public Evento buscarEvento(Long id) {
        Evento e = eventos.get(id);
        if (e == null) throw new IllegalArgumentException("Evento não encontrado");
        return e;
    }

    public List<Evento> eventosDoOrganizador(Long organizadorId) {
        return eventos.values().stream()
                .filter(e -> e.getOrganizadorId().equals(organizadorId))
                .collect(Collectors.toList());
    }

    public List<Evento> feedEventos() {
        return eventos.values().stream()
                .filter(e ->
                        e.isAtivo()
                        && !e.jaFinalizou()
                        && e.temVagas()
                )
                .sorted(Comparator
                        .comparing(Evento::getInicio)
                        .thenComparing(Evento::getNome))
                .collect(Collectors.toList());
    }

    // =========================================================
    // INGRESSO
    // =========================================================
    public List<Ingresso> comprarIngresso(Long usuarioId, Long eventoId) {
        Usuario u = buscarUsuario(usuarioId);
        if (!u.isAtivo())
            throw new IllegalStateException("Usuário inativo");

        Evento evento = buscarEvento(eventoId);
        if (!evento.isAtivo())
            throw new IllegalStateException("Evento inativo");

        List<Ingresso> gerados = new ArrayList<>();

        // Evento principal
        if (evento.getEventoPrincipalId() != null) {
            Evento principal = buscarEvento(evento.getEventoPrincipalId());

            principal.venderIngresso();
            Ingresso ip = new Ingresso();
            ip.setId(ingressoId.getAndIncrement());
            ip.setUsuarioId(usuarioId);
            ip.setEventoId(principal.getId());
            ip.setValorPago(principal.getPrecoIngresso());
            ingressos.put(ip.getId(), ip);
            gerados.add(ip);
        }

        // Evento solicitado
        evento.venderIngresso();
        Ingresso i = new Ingresso();
        i.setId(ingressoId.getAndIncrement());
        i.setUsuarioId(usuarioId);
        i.setEventoId(evento.getId());
        i.setValorPago(evento.getPrecoIngresso());
        ingressos.put(i.getId(), i);
        gerados.add(i);

        return gerados;
    }

    public Ingresso cancelarIngresso(Long ingressoId) {
        Ingresso i = buscarIngresso(ingressoId);
        if (!i.isAtivo()) return i;

        Evento e = buscarEvento(i.getEventoId());

        i.cancelar();
        e.cancelarIngresso();

        if (e.isEstorna()) {
            double valorEstorno = i.getValorPago() * (1 - e.getTaxaEstorno());
            i.setValorPago(valorEstorno);
        } else {
            i.setValorPago(0);
        }

        return i;
    }

    public Ingresso buscarIngresso(Long id) {
        Ingresso i = ingressos.get(id);
        if (i == null) throw new IllegalArgumentException("Ingresso não encontrado");
        return i;
    }

    public List<Ingresso> ingressosDoUsuario(Long usuarioId) {
        return ingressos.values().stream()
                .filter(i -> i.getUsuarioId().equals(usuarioId))
                .sorted(Comparator
                        .comparing((Ingresso i) -> {
                            Evento e = eventos.get(i.getEventoId());
                            return e.isAtivo() && !e.jaFinalizou() && i.isAtivo();
                        }).reversed()
                        .thenComparing(i -> eventos.get(i.getEventoId()).getInicio())
                        .thenComparing(i -> eventos.get(i.getEventoId()).getNome()))
                .collect(Collectors.toList());
    }

    // =========================================================
    // CANCELAMENTO EM CASCATA (EVENTO)
    // =========================================================
    public void cancelarEvento(Long eventoId) {
        Evento e = buscarEvento(eventoId);
        e.setAtivo(false);

        ingressos.values().stream()
                .filter(i -> i.getEventoId().equals(eventoId) && i.isAtivo())
                .forEach(i -> cancelarIngresso(i.getId()));
    }
}