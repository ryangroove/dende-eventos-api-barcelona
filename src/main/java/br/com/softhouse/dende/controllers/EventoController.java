package br.com.softhouse.dende.controllers;

import br.com.softhouse.dende.model.Evento;
import br.com.softhouse.dende.repositories.Repositorio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final Repositorio repo = Repositorio.getInstance();

    @PostMapping("/{organizadorId}")
    public Evento criar(@PathVariable Long organizadorId,
                        @RequestBody Evento evento) {
        evento.setOrganizadorId(organizadorId);
        evento.validarCadastro();
        return repo.salvarEvento(evento);
    }

    @PutMapping("/{eventoId}")
    public Evento atualizar(@PathVariable Long eventoId,
                            @RequestBody Evento dados) {
        Evento e = repo.buscarEvento(eventoId);
        e.setNome(dados.getNome());
        e.setDescricao(dados.getDescricao());
        e.setInicio(dados.getInicio());
        e.setFim(dados.getFim());
        return e;
    }

    @PatchMapping("/{eventoId}/status")
    public Evento status(@PathVariable Long eventoId,
                         @RequestParam boolean ativo) {
        Evento e = repo.buscarEvento(eventoId);
        e.setAtivo(ativo);
        return e;
    }

    @GetMapping("/organizador/{organizadorId}")
    public List<Evento> listar(@PathVariable Long organizadorId) {
        return repo.listarEventosPorOrganizador(organizadorId);
    }
}