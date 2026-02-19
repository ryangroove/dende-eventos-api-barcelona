package br.com.softhouse.dende.controllers;

import br.com.dende.softhouse.annotations.Controller;
import br.com.dende.softhouse.annotations.request.*;
import br.com.dende.softhouse.process.route.ResponseEntity;
import br.com.softhouse.dende.model.Organizador;
import br.com.softhouse.dende.repositories.Repositorio;

@Controller
@RequestMapping(path = "/organizadores")
public class OrganizadorController {

    private final Repositorio repositorio = Repositorio.getInstance();

    // POST /organizadores
    @PostMapping
    public ResponseEntity<Organizador> cadastrar(@RequestBody Organizador organizador) {
        Organizador salvo = repositorio.salvarOrganizador(organizador);
        return ResponseEntity.ok(salvo);
    }

    // GET /organizadores/{organizadorId}
    @GetMapping(path = "/{organizadorId}")
    public ResponseEntity<Organizador> buscar(
            @PathVariable(parameter = "organizadorId") Long organizadorId) {

        return ResponseEntity.ok(repositorio.buscarOrganizador(organizadorId));
    }

    // PUT /organizadores/{organizadorId}
    @PutMapping(path = "/{organizadorId}")
    public ResponseEntity<Organizador> alterar(
            @PathVariable(parameter = "organizadorId") Long organizadorId,
            @RequestBody Organizador atualizado) {

        Organizador organizador = repositorio.buscarOrganizador(organizadorId);

        organizador.setNome(atualizado.getNome());
        organizador.setEmail(atualizado.getEmail());
        organizador.setSexo(atualizado.getSexo());
        organizador.setDataNascimento(atualizado.getDataNascimento());
        organizador.setCnpj(atualizado.getCnpj());
        organizador.setRazaoSocial(atualizado.getRazaoSocial());
        organizador.setNomeFantasia(atualizado.getNomeFantasia());

        return ResponseEntity.ok(organizador);
    }

    // PATCH /organizadores/{organizadorId}/{status}
    @PatchMapping(path = "/{organizadorId}/{status}")
    public ResponseEntity<Organizador> alterarStatus(
            @PathVariable(parameter = "organizadorId") Long organizadorId,
            @PathVariable(parameter = "status") Boolean status) {

        Organizador organizador = repositorio.buscarOrganizador(organizadorId);
        organizador.setAtivo(status);

        return ResponseEntity.ok(organizador);
    }
}