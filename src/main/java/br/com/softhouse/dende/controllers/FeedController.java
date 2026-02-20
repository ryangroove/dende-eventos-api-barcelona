package br.com.softhouse.dende.controllers;

import br.com.softhouse.dende.model.Evento;
import br.com.softhouse.dende.repositories.Repositorio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    private final Repositorio repo = Repositorio.getInstance();

    @GetMapping
    public List<Evento> feed() {
        return repo.feedEventos();
    }
}