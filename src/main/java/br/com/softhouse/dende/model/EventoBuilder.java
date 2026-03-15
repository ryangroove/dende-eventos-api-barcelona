package br.com.softhouse.dende.model;

import java.time.LocalDateTime;

public class EventoBuilder {

    private final Evento evento;

    public EventoBuilder() {
        this.evento = new Evento();
    }

    public EventoBuilder id(Long id) {
        evento.setId(id);
        return this;
    }

    public EventoBuilder organizadorId(Long organizadorId) {
        evento.setOrganizadorId(organizadorId);
        return this;
    }

    public EventoBuilder paginaEvento(String paginaEvento) {
        evento.setPaginaEvento(paginaEvento);
        return this;
    }

    public EventoBuilder nome(String nome) {
        evento.setNome(nome);
        return this;
    }

    public EventoBuilder descricao(String descricao) {
        evento.setDescricao(descricao);
        return this;
    }

    public EventoBuilder inicio(LocalDateTime inicio) {
        evento.setInicio(inicio);
        return this;
    }

    public EventoBuilder fim(LocalDateTime fim) {
        evento.setFim(fim);
        return this;
    }

    public EventoBuilder tipo(TipoEvento tipo) {
        evento.setTipo(tipo);
        return this;
    }

    public EventoBuilder modalidade(ModalidadeEvento modalidade) {
        evento.setModalidade(modalidade);
        return this;
    }

    public EventoBuilder local(String local) {
        evento.setLocal(local);
        return this;
    }

    public EventoBuilder capacidadeMaxima(int capacidadeMaxima) {
        evento.setCapacidadeMaxima(capacidadeMaxima);
        return this;
    }

    public EventoBuilder precoIngresso(double precoIngresso) {
        evento.setPrecoIngresso(precoIngresso);
        return this;
    }

    public EventoBuilder estorna(boolean estorna) {
        evento.setEstorna(estorna);
        return this;
    }

    public EventoBuilder taxaEstorno(double taxaEstorno) {
        evento.setTaxaEstorno(taxaEstorno);
        return this;
    }

    public EventoBuilder ativo(boolean ativo) {
        evento.setAtivo(ativo);
        return this;
    }

    public EventoBuilder eventoPrincipalId(Long eventoPrincipalId) {
        evento.setEventoPrincipalId(eventoPrincipalId);
        return this;
    }

    public Evento build() {
        return evento;
    }
}