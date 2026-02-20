package br.com.softhouse.dende.controllers;

import br.com.softhouse.dende.model.Evento;
import br.com.softhouse.dende.repositories.Repositorio;

import java.util.List;

public class EventoController {

    private final Repositorio repo = Repositorio.getInstance();

    // Criar evento
    public Evento criarEvento(Evento evento) {
        return repo.salvarEvento(evento);
    }

    // Buscar evento
    public Evento buscarEvento(Long id) {
        return repo.buscarEvento(id);
    }

    // Eventos do organizador
    public List<Evento> eventosDoOrganizador(Long organizadorId) {
        return repo.eventosDoOrganizador(organizadorId);
    }

    // Feed de eventos
    public List<Evento> feed() {
        return repo.feedEventos();
    }

    // Cancelar evento (cascata)
    public void cancelarEvento(Long eventoId) {
        repo.cancelarEvento(eventoId);
    }
}