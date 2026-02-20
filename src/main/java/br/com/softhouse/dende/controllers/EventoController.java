package br.com.softhouse.dende.controllers;

import br.com.dende.softhouse.annotations.Controller;
import br.com.dende.softhouse.annotations.request.*;
import br.com.dende.softhouse.process.route.ResponseEntity;
import br.com.softhouse.dende.dto.StatusEventoRequest;
import br.com.softhouse.dende.model.Evento;
import br.com.softhouse.dende.repositories.Repositorio;

import java.util.List;

@Controller
@RequestMapping(path = "/organizadores/{organizadorId}/eventos")
public class EventoController {

    private final Repositorio repo = Repositorio.getInstance();

    @PostMapping
    public ResponseEntity<Evento> cadastrar(
            @PathVariable(parameter = "organizadorId") Long organizadorId,
            @RequestBody Evento evento
    ) {
        evento.setOrganizadorId(organizadorId);
        evento.validarCadastro();
        repo.salvarEvento(evento);
        return ResponseEntity.ok(evento);
    }

    @PutMapping(path = "/{eventoId}")
    public ResponseEntity<Evento> atualizar(
            @PathVariable(parameter = "eventoId") Long eventoId,
            @RequestBody Evento dados
    ) {
        dados.validarCadastro();
        Evento e = repo.buscarEvento(eventoId);
        e.setNome(dados.getNome());
        e.setDescricao(dados.getDescricao());
        e.setInicio(dados.getInicio());
        e.setFim(dados.getFim());
        e.setLocal(dados.getLocal());
        e.setPrecoIngresso(dados.getPrecoIngresso());
        e.setCapacidadeMaxima(dados.getCapacidadeMaxima());
        return ResponseEntity.ok(e);
    }

    // User Story 9 e 10
    @PatchMapping(path = "/{eventoId}/status")
    public ResponseEntity<Evento> status(
            @PathVariable(parameter = "eventoId") Long eventoId,
            @RequestBody StatusEventoRequest req
    ) {
        if (!req.isAtivo()) {
            repo.cancelarEventoComEstorno(eventoId);
        } else {
            repo.buscarEvento(eventoId).setAtivo(true);
        }
        return ResponseEntity.ok(repo.buscarEvento(eventoId));
    }

    @GetMapping
    public ResponseEntity<List<Evento>> listar(
            @PathVariable(parameter = "organizadorId") Long organizadorId
    ) {
        return ResponseEntity.ok(
                repo.listarEventosPorOrganizador(organizadorId)
        );
    }
}