package br.com.softhouse.dende.controllers;

import br.com.dende.softhouse.annotations.Controller;
import br.com.dende.softhouse.annotations.request.*;
import br.com.dende.softhouse.process.route.ResponseEntity;
import br.com.softhouse.dende.model.Ingresso;
import br.com.softhouse.dende.repositories.Repositorio;

import java.util.List;

@Controller
public class IngressoController {

    private final Repositorio repositorio = Repositorio.getInstance();

    // POST /organizadores/{organizadorId}/eventos/{eventoId}/ingressos
    @PostMapping(path = "/organizadores/{organizadorId}/eventos/{eventoId}/ingressos")
    public ResponseEntity<Ingresso> comprarIngresso(
            @PathVariable(parameter = "organizadorId") Long organizadorId,
            @PathVariable(parameter = "eventoId") Long eventoId,
            @RequestBody Ingresso ingresso) {

        ingresso.setEventoId(eventoId);
        return ResponseEntity.ok(repositorio.salvarIngresso(ingresso));
    }

    // POST /usuarios/{usuarioId}/ingressos/{ingressoId}
    @PostMapping(path = "/usuarios/{usuarioId}/ingressos/{ingressoId}")
    public ResponseEntity<Ingresso> cancelarIngresso(
            @PathVariable(parameter = "usuarioId") Long usuarioId,
            @PathVariable(parameter = "ingressoId") Long ingressoId) {

        Ingresso ingresso = repositorio.buscarIngresso(ingressoId);
        ingresso.setAtivo(false);

        return ResponseEntity.ok(ingresso);
    }

    // GET /usuarios/{usuarioId}/ingressos
    @GetMapping(path = "/usuarios/{usuarioId}/ingressos")
    public ResponseEntity<List<Ingresso>> listarIngressos(
            @PathVariable(parameter = "usuarioId") Long usuarioId) {

        return ResponseEntity.ok(
                repositorio.listarIngressosUsuario(usuarioId)
        );
    }
}