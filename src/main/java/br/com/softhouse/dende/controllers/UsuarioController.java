package br.com.softhouse.dende.controllers;

import br.com.softhouse.dende.model.Usuario;
import br.com.softhouse.dende.repositories.Repositorio;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final Repositorio repo = Repositorio.getInstance();

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return repo.salvarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id,
                             @RequestBody Usuario dados) {
        Usuario u = repo.buscarUsuario(id);
        u.setNome(dados.getNome());
        u.setSenha(dados.getSenha());
        u.setSexo(dados.getSexo());
        u.setDataNascimento(dados.getDataNascimento());
        return u;
    }

    @GetMapping("/{id}")
    public Usuario visualizar(@PathVariable Long id) {
        return repo.buscarUsuario(id);
    }

    @PatchMapping("/{id}/status")
    public Usuario alterarStatus(@PathVariable Long id,
                                 @RequestParam boolean ativo) {
        Usuario u = repo.buscarUsuario(id);
        u.setAtivo(ativo);
        return u;
    }

    @PostMapping("/login")
    public Usuario login(@RequestParam String email,
                         @RequestParam String senha) {
        Usuario u = repo.buscarUsuarioPorEmail(email);
        if (!u.getSenha().equals(senha))
            throw new IllegalArgumentException("Senha inválida");
        u.setAtivo(true);
        return u;
    }
}