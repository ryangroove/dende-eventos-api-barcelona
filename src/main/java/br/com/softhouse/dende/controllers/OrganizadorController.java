package br.com.softhouse.dende.controllers;

import br.com.dende.softhouse.annotations.Controller;
import br.com.dende.softhouse.annotations.request.*;
import br.com.dende.softhouse.process.route.ResponseEntity;
import br.com.softhouse.dende.dto.StatusRequest;
import br.com.softhouse.dende.model.Organizador;
import br.com.softhouse.dende.repositories.Repositorio;

@Controller
@RequestMapping(path = "/organizadores")
public class OrganizadorController {

    private final Repositorio repo = Repositorio.getInstance();

    @PostMapping
    public ResponseEntity<Organizador> cadastrar(
            @RequestBody Organizador organizador
    ) {
        repo.salvarOrganizador(organizador);
        return ResponseEntity.ok(organizador);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Organizador> atualizar(
            @PathVariable(parameter = "id") Long id,
            @RequestBody Organizador dados
    ) {
        Organizador o = repo.buscarOrganizador(id);

        o.setNome(dados.getNome());
        o.setSenha(dados.getSenha());
        o.setSexo(dados.getSexo());
        o.setDataNascimento(dados.getDataNascimento());

        return ResponseEntity.ok(o);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Organizador> visualizar(
            @PathVariable(parameter = "id") Long id
    ) {
        return ResponseEntity.ok(repo.buscarOrganizador(id));
    }

    @PatchMapping(path = "/{id}/status")
    public ResponseEntity<Organizador> status(
            @PathVariable(parameter = "id") Long id,
            @RequestBody StatusRequest req
    ) {
        if (!req.isAtivo() && repo.organizadorPossuiEventosAtivos(id)) {
            throw new IllegalStateException(
                    "Organizador possui eventos ativos ou em execução"
            );
        }

        Organizador o = repo.buscarOrganizador(id);
        o.setAtivo(req.isAtivo());

        return ResponseEntity.ok(o);
    }
}