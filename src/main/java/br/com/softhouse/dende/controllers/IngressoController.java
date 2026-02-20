package br.com.softhouse.dende.controllers;

import br.com.softhouse.dende.model.Evento;
import br.com.softhouse.dende.model.Ingresso;
import br.com.softhouse.dende.repositories.Repositorio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    private final Repositorio repo = Repositorio.getInstance();

    @PostMapping("/comprar")
    public Ingresso comprar(@RequestParam Long usuarioId,
                            @RequestParam Long eventoId) {

        Evento e = repo.buscarEvento(eventoId);
        if (!e.temVagas())
            throw new IllegalStateException("Evento lotado");

        e.venderIngresso();

        Ingresso i = new Ingresso();
        i.setUsuarioId(usuarioId);
        i.setEventoId(eventoId);
        i.setValorPago(e.getPrecoIngresso());

        return repo.salvarIngresso(i);
    }

    @PostMapping("/{ingressoId}/cancelar")
    public Ingresso cancelar(@PathVariable Long ingressoId) {
        Ingresso i = repo.buscarIngresso(ingressoId);
        i.cancelar();
        return i;
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Ingresso> listar(@PathVariable Long usuarioId) {
        return repo.ingressosPorUsuario(usuarioId);
    }
}