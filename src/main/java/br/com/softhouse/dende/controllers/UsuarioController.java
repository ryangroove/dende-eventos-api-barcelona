package br.com.softhouse.dende.controllers;

import br.com.dende.softhouse.annotations.Controller;
import br.com.dende.softhouse.annotations.request.*;
import br.com.dende.softhouse.process.route.ResponseEntity;
import br.com.softhouse.dende.dto.StatusRequest;
import br.com.softhouse.dende.model.Usuario;
import br.com.softhouse.dende.repositories.Repositorio;

@Controller
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    private final Repositorio repo = Repositorio.getInstance();

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        repo.salvarUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Usuario> atualizar(
            @PathVariable(parameter = "id") Long id,
            @RequestBody Usuario dados
    ) {
        Usuario u = repo.buscarUsuario(id);
        u.setNome(dados.getNome());
        u.setSexo(dados.getSexo());
        u.setDataNascimento(dados.getDataNascimento());
        u.setSenha(dados.getSenha());
        return ResponseEntity.ok(u);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Usuario> visualizar(
            @PathVariable(parameter = "id") Long id
    ) {
        return ResponseEntity.ok(repo.buscarUsuario(id));
    }

    // User Stories 5 e 6
    @PatchMapping(path = "/status")
    public ResponseEntity<Usuario> status(@RequestBody StatusRequest req) {
        Usuario u = repo.buscarUsuarioPorEmail(req.getEmail());

        if (!u.getSenha().equals(req.getSenha())) {
            throw new IllegalStateException("Credenciais inválidas");
        }

        u.setAtivo(req.isAtivo());
        return ResponseEntity.ok(u);
    }
}