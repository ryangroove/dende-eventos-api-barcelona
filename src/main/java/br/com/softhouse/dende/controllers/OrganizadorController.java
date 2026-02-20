package br.com.softhouse.dende.controllers;

import br.com.softhouse.dende.model.Organizador;
import br.com.softhouse.dende.repositories.Repositorio;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizadores")
public class OrganizadorController {

    private final Repositorio repo = Repositorio.getInstance();

    @PostMapping
    public Organizador criar(@RequestBody Organizador organizador) {
        return repo.salvarOrganizador(organizador);
    }

    @PutMapping("/{id}")
    public Organizador atualizar(@PathVariable Long id,
                                 @RequestBody Organizador dados) {
        Organizador o = repo.buscarOrganizador(id);
        o.setNome(dados.getNome());
        o.setSenha(dados.getSenha());
        return o;
    }

    @GetMapping("/{id}")
    public Organizador visualizar(@PathVariable Long id) {
        return repo.buscarOrganizador(id);
    }

    @PatchMapping("/{id}/status")
    public Organizador status(@PathVariable Long id,
                              @RequestParam boolean ativo) {
        Organizador o = repo.buscarOrganizador(id);
        o.setAtivo(ativo);
        return o;
    }
}