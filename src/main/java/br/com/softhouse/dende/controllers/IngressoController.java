package br.com.softhouse.dende.controllers;

import br.com.dende.softhouse.annotations.Controller;
import br.com.dende.softhouse.annotations.request.*;
import br.com.dende.softhouse.process.route.ResponseEntity;
import br.com.softhouse.dende.dto.CompraIngressoRequest;
import br.com.softhouse.dende.model.Evento;
import br.com.softhouse.dende.model.Ingresso;
import br.com.softhouse.dende.repositories.Repositorio;

import java.util.List;

@Controller
@RequestMapping(path = "/ingressos")
public class IngressoController {

    private final Repositorio repo = Repositorio.getInstance();

    @PostMapping(path = "/comprar")
    public ResponseEntity<List<Ingresso>> comprar(
            @RequestBody CompraIngressoRequest req
    ) {
        Evento evento = repo.buscarEvento(req.getEventoId());

        if (!evento.isAtivo()) {
            throw new IllegalStateException("Evento não está ativo");
        }

        if (!evento.temVagas()) {
            throw new IllegalStateException("Evento lotado");
        }

        evento.venderIngresso();

        Ingresso ingresso = new Ingresso();
        ingresso.setUsuarioId(req.getUsuarioId());
        ingresso.setEventoId(req.getEventoId());
        ingresso.setValorPago(evento.getPrecoIngresso());

        repo.salvarIngresso(ingresso);

        return ResponseEntity.ok(List.of(ingresso));
    }

    @PostMapping(path = "/{ingressoId}/cancelar")
    public ResponseEntity<Ingresso> cancelar(
            @PathVariable(parameter = "ingressoId") Long ingressoId
    ) {
        Ingresso ingresso = repo.buscarIngresso(ingressoId);
        Evento evento = repo.buscarEvento(ingresso.getEventoId());

        if (!ingresso.isAtivo()) {
            throw new IllegalStateException("Ingresso já cancelado");
        }

        if (evento.isEstorna()) {
            double valorEstornado =
                    ingresso.getValorPago() * (1 - evento.getTaxaEstorno());
            ingresso.setValorPago(valorEstornado);
        } else {
            ingresso.setValorPago(0);
        }

        ingresso.cancelar();
        evento.cancelarIngresso();

        return ResponseEntity.ok(ingresso);
    }

    @GetMapping(path = "/usuarios/{usuarioId}")
    public ResponseEntity<List<Ingresso>> listar(
            @PathVariable(parameter = "usuarioId") Long usuarioId
    ) {
        return ResponseEntity.ok(
                repo.ingressosPorUsuario(usuarioId)
        );
    }
}