package br.com.softhouse.dende.repositories;

import br.com.softhouse.dende.model.*;

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

    // ===== USUARIO =====
    public Usuario salvarUsuario(Usuario usuario) {
        usuario.setId(usuarioId.getAndIncrement());
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    public Usuario buscarUsuario(Long id) {
        return usuarios.get(id);
    }

    public Collection<Usuario> listarUsuarios() {
        return usuarios.values();
    }

    // ===== ORGANIZADOR =====
    public Organizador salvarOrganizador(Organizador organizador) {
        organizador.setId(organizadorId.getAndIncrement());
        organizadores.put(organizador.getId(), organizador);
        return organizador;
    }

    public Organizador buscarOrganizador(Long id) {
        return organizadores.get(id);
    }

    // ===== EVENTO =====
    public Evento salvarEvento(Evento evento) {
        evento.setId(eventoId.getAndIncrement());
        eventos.put(evento.getId(), evento);
        return evento;
    }

    public List<Evento> listarEventosPorOrganizador(Long organizadorId) {
        return eventos.values().stream()
                .filter(e -> e.getOrganizadorId().equals(organizadorId))
                .collect(Collectors.toList());
    }

    public List<Evento> listarEventosAtivos() {
        return eventos.values().stream()
                .filter(Evento::isAtivo)
                .collect(Collectors.toList());
    }

    public Evento buscarEvento(Long id) {
        return eventos.get(id);
    }

    // ===== INGRESSO =====
    public Ingresso salvarIngresso(Ingresso ingresso) {
        ingresso.setId(ingressoId.getAndIncrement());
        ingressos.put(ingresso.getId(), ingresso);
        return ingresso;
    }

    public List<Ingresso> listarIngressosUsuario(Long usuarioId) {
        return ingressos.values().stream()
                .filter(i -> i.getUsuarioId().equals(usuarioId))
                .collect(Collectors.toList());
    }

    public Ingresso buscarIngresso(Long id) {
        return ingressos.get(id);
    }
}