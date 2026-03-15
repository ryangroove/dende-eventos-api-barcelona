package br.com.softhouse.dende.controllers;

import br.com.dende.softhouse.annotations.Controller;
import br.com.dende.softhouse.annotations.request.*;
import br.com.dende.softhouse.process.route.ResponseEntity;
import br.com.softhouse.dende.dto.EventoRequestDTO;
import br.com.softhouse.dende.dto.EventoResponseDTO;
import br.com.softhouse.dende.dto.StatusEventoRequest;
import br.com.softhouse.dende.mappers.EventoMapper;
import br.com.softhouse.dende.model.Evento;
import br.com.softhouse.dende.repositories.Repositorio;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/organizadores/{organizadorId}/eventos")
public class EventoController {

    private final Repositorio repo = Repositorio.getInstance();

    @PostMapping
    public ResponseEntity<EventoResponseDTO> cadastrar(
            @PathVariable(parameter = "organizadorId") Long organizadorId,
            @RequestBody EventoRequestDTO dto
    ) {

        Evento evento = EventoMapper.toModel(dto, organizadorId);

        evento.validarCadastro();

        repo.salvarEvento(evento);

        return ResponseEntity.ok(
                EventoMapper.toDTO(evento)
        );
    }

    @PutMapping(path = "/{eventoId}")
    public ResponseEntity<EventoResponseDTO> atualizar(
            @PathVariable(parameter = "eventoId") Long eventoId,
            @RequestBody EventoRequestDTO dto
    ) {

        Evento evento = repo.buscarEvento(eventoId);

        evento.setNome(dto.getNome());
        evento.setDescricao(dto.getDescricao());
        evento.setInicio(dto.getInicio());
        evento.setFim(dto.getFim());
        evento.setTipo(dto.getTipo());
        evento.setModalidade(dto.getModalidade());
        evento.setLocal(dto.getLocal());
        evento.setCapacidadeMaxima(dto.getCapacidadeMaxima());
        evento.setPrecoIngresso(dto.getPrecoIngresso());
        evento.setEstorna(dto.isEstorna());
        evento.setTaxaEstorno(dto.getTaxaEstorno());

        evento.validarCadastro();

        return ResponseEntity.ok(
                EventoMapper.toDTO(evento)
        );
    }

    @PatchMapping(path = "/{eventoId}/status")
    public ResponseEntity<EventoResponseDTO> status(
            @PathVariable(parameter = "eventoId") Long eventoId,
            @RequestBody StatusEventoRequest req
    ) {

        Evento evento = repo.buscarEvento(eventoId);

        if (!req.isAtivo()) {
            repo.cancelarEventoComEstorno(eventoId);
        } else {
            evento.setAtivo(true);
        }

        return ResponseEntity.ok(
                EventoMapper.toDTO(evento)
        );
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> listar(
            @PathVariable(parameter = "organizadorId") Long organizadorId
    ) {

        List<EventoResponseDTO> lista = repo
                .listarEventosPorOrganizador(organizadorId)
                .stream()
                .map(EventoMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}