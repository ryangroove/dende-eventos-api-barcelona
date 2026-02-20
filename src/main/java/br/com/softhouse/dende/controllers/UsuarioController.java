package br.com.softhouse.dende.controllers;

import br.com.softhouse.dende.model.Usuario;
import br.com.softhouse.dende.repositories.Repositorio;

public class UsuarioController {

    private final Repositorio repo = Repositorio.getInstance();

    // Criar usuário
    public Usuario criarUsuario(Usuario usuario) {
        return repo.salvarUsuario(usuario);
    }

    // Buscar usuário por id
    public Usuario buscarUsuario(Long id) {
        return repo.buscarUsuario(id);
    }

    // Login / reativação por e-mail
    public Usuario login(String email, String senha) {
        Usuario usuario = repo.buscarUsuarioPorEmail(email);

        if (!usuario.getSenha().equals(senha))
            throw new IllegalArgumentException("Senha inválida");

        usuario.setAtivo(true);
        return usuario;
    }

    // Desativar usuário
    public Usuario desativarUsuario(Long id) {
        Usuario usuario = repo.buscarUsuario(id);
        usuario.setAtivo(false);
        return usuario;
    }
}