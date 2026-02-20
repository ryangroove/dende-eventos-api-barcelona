package br.com.softhouse.dende.controllers;

import br.com.softhouse.dende.model.Ingresso;
import br.com.softhouse.dende.repositories.Repositorio;

import java.util.List;

public class IngressoController {

    private final Repositorio repo = Repositorio.getInstance();

    // Comprar ingresso (inclui evento principal)
    public List<Ingresso> comprar(Long usuarioId, Long eventoId) {
        return repo.comprarIngresso(usuarioId, eventoId);
    }

    // Cancelar ingresso
    public Ingresso cancelar(Long ingressoId) {
        return repo.cancelarIngresso(ingressoId);
    }

    // Listar ingressos do usuário
    public List<Ingresso> ingressosDoUsuario(Long usuarioId) {
        return repo.ingressosDoUsuario(usuarioId);
    }
}