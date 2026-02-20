package br.com.softhouse.dende.repositories;

import br.com.softhouse.dende.model.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Repositorio {

    private static final Repositorio INSTANCE = new Repositorio();

    public static Repositorio getInstance() {
        return INSTANCE;
    }

    private final AtomicLong seq = new AtomicLong(1);

    private final Map<Long, Usuario> usuarios = new HashMap<>();
    private final Map<Long, Organizador> organizadores = new HashMap<>();
    private final Map<Long, Evento> eventos = new HashMap<>();
    private final Map<Long, Ingresso> ingressos = new HashMap<>();

    private Repositorio() {}

    // ===== USUÁRIO =====

    public Usuario salvarUsuario(Usuario u) {
        if (u.getId() == null)
            u.setId(seq.getAndIncrement());
        usuarios.put(u.getId(), u);
        return u;
    }

    public Usuario buscarUsuario(Long id) {
        Usuario u = usuarios.get(id);
        if (u == null)
            throw new NoSuchElementException("Usuário não encontrado");
        return u;
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarios.values().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
    }

    // ===== ORGANIZADOR =====

    public Organizador salvarOrganizador(Organizador o) {
        if (o.getId() == null)
            o.setId(seq.getAndIncrement());
        organizadores.put(o.getId(), o);
        return o;
    }

    public Organizador buscarOrganizador(Long id) {
        Organizador o = organizadores.get(id);
        if (o == null)
            throw new NoSuchElementException("Organizador não encontrado");
        return o;
    }

    // ===== EVENTO =====

    public Evento salvarEvento(Evento e) {
        if (e.getId() == null)
            e.setId(seq.getAndIncrement());
        eventos.put(e.getId(), e);
        return e;
    }

    public Evento buscarEvento(Long id) {
        Evento e = eventos.get(id);
        if (e == null)
            throw new NoSuchElementException("Evento não encontrado");
        return e;
    }

    public List<Evento> listarEventosPorOrganizador(Long organizadorId) {
        return eventos.values().stream()
                .filter(e -> e.getOrganizadorId().equals(organizadorId))
                .collect(Collectors.toList());
    }

    public List<Evento> feedEventos() {
        return eventos.values().stream()
                .filter(Evento::isAtivo)
                .collect(Collectors.toList());
    }

    // ===== INGRESSO =====

    public Ingresso salvarIngresso(Ingresso i) {
        if (i.getId() == null)
            i.setId(seq.getAndIncrement());
        ingressos.put(i.getId(), i);
        return i;
    }

    public Ingresso buscarIngresso(Long id) {
        Ingresso i = ingressos.get(id);
        if (i == null)
            throw new NoSuchElementException("Ingresso não encontrado");
        return i;
    }

    public List<Ingresso> ingressosPorUsuario(Long usuarioId) {
        return ingressos.values().stream()
                .filter(i -> i.getUsuarioId().equals(usuarioId))
                .collect(Collectors.toList());
    }
}