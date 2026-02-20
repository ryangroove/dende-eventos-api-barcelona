package br.com.softhouse.dende.controllers;

import br.com.softhouse.dende.model.Organizador;
import br.com.softhouse.dende.repositories.Repositorio;

public class OrganizadorController {

    private final Repositorio repo = Repositorio.getInstance();

    // Criar organizador
    public Organizador criarOrganizador(Organizador organizador) {
        return repo.salvarOrganizador(organizador);
    }

    // Buscar organizador
    public Organizador buscarOrganizador(Long id) {
        return repo.buscarOrganizador(id);
    }

    // Desativar organizador
    public Organizador desativarOrganizador(Long id) {
        if (repo.organizadorTemEventoAtivoOuEmExecucao(id))
            throw new IllegalStateException(
                    "Organizador possui evento ativo ou em execução");

        Organizador o = repo.buscarOrganizador(id);
        o.setAtivo(false);
        return o;
    }
}