package br.com.softhouse.dende.controllers;

import br.com.dende.softhouse.annotations.Controller;
import br.com.dende.softhouse.annotations.request.*;
import br.com.dende.softhouse.process.route.ResponseEntity;
import br.com.softhouse.dende.model.Evento;
import br.com.softhouse.dende.repositories.Repositorio;

import java.util.List;

@Controller
public class EventoController {

    private final Repositorio repositorio = Repositorio.getInstance();

    // POST /organizadores/{organizadorId}/eventos
    @PostMapping(path = "/organizadores/{organizadorId}/eventos")
    public ResponseEntity<Evento> cadastrarEvento(
            @PathVariable(parameter = "organizadorId") Long organizadorId,
            @RequestBody Evento evento) {

        evento.setOrganizadorId(organizadorId);
        return ResponseEntity.ok(repositorio.salvarEvento(evento));
    }

    // PUT /organizadores/{organizadorId}/eventos/{eventoId}
    @PutMapping(path = "/organizadores/{organizadorId}/eventos/{eventoId}")
    public ResponseEntity<Evento> alterarEvento(
            @PathVariable(parameter = "organizadorId") Long organizadorId,
            @PathVariable(parameter = "eventoId") Long eventoId,
            @RequestBody Evento atualizado) {

        Evento evento = repositorio.buscarEvento(eventoId);

        evento.setNome(atualizado.getNome());
        evento.setDescricao(atualizado.getDescricao());
        evento.setData(atualizado.getData());
        evento.setOrganizadorId(organizadorId); // mantém consistência

        return ResponseEntity.ok(evento);
    }

    // PATCH /organizadores/{organizadorId}/eventos/{eventoId}/{status}
    @PatchMapping(path = "/organizadores/{organizadorId}/eventos/{eventoId}/{status}")
    public ResponseEntity<Evento> alterarStatusEvento(
            @PathVariable(parameter = "organizadorId") Long organizadorId,
            @PathVariable(parameter = "eventoId") Long eventoId,
            @PathVariable(parameter = "status") Boolean status) {

        Evento evento = repositorio.buscarEvento(eventoId);
        evento.setOrganizadorId(organizadorId);
        evento.setAtivo(status);

        return ResponseEntity.ok(evento);
    }

    // GET /organizadores/{organizadorId}/eventos
    @GetMapping(path = "/organizadores/{organizadorId}/eventos")
    public ResponseEntity<List<Evento>> listarPorOrganizador(
            @PathVariable(parameter = "organizadorId") Long organizadorId) {

        return ResponseEntity.ok(
                repositorio.listarEventosPorOrganizador(organizadorId)
        );
    }

    // GET /eventos
    @GetMapping(path = "/eventos")
    public ResponseEntity<List<Evento>> feedEventos() {
        return ResponseEntity.ok(repositorio.listarEventosAtivos());
    }
}