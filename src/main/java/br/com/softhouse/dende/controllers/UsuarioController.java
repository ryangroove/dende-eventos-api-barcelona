package br.com.softhouse.dende.controllers;

import br.com.dende.softhouse.annotations.Controller;
import br.com.dende.softhouse.annotations.request.*;
import br.com.dende.softhouse.process.route.ResponseEntity;
import br.com.softhouse.dende.model.Usuario;
import br.com.softhouse.dende.repositories.Repositorio;

@Controller
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    private final Repositorio repositorio = Repositorio.getInstance();

    // POST /usuarios
    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario salvo = repositorio.salvarUsuario(usuario);
        return ResponseEntity.ok(salvo);
    }

    // GET /usuarios/{usuarioId}
    @GetMapping(path = "/{usuarioId}")
    public ResponseEntity<Usuario> buscar(@PathVariable(parameter = "usuarioId") Long usuarioId) {
        Usuario usuario = repositorio.buscarUsuario(usuarioId);
        return ResponseEntity.ok(usuario);
    }

    // PUT /usuarios/{usuarioId}
    @PutMapping(path = "/{usuarioId}")
    public ResponseEntity<Usuario> alterar(
            @PathVariable(parameter = "usuarioId") Long usuarioId,
            @RequestBody Usuario usuarioAtualizado) {

        Usuario usuario = repositorio.buscarUsuario(usuarioId);

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSexo(usuarioAtualizado.getSexo());
        usuario.setDataNascimento(usuarioAtualizado.getDataNascimento());

        return ResponseEntity.ok(usuario);
    }

    // PATCH /usuarios/{usuarioId}/{status}
    @PatchMapping(path = "/{usuarioId}/{status}")
    public ResponseEntity<Usuario> alterarStatus(
            @PathVariable(parameter = "usuarioId") Long usuarioId,
            @PathVariable(parameter = "status") Boolean status) {

        Usuario usuario = repositorio.buscarUsuario(usuarioId);
        usuario.setAtivo(status);

        return ResponseEntity.ok(usuario);
    }
}