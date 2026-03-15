package br.com.softhouse.dende.dto;

import br.com.softhouse.dende.model.ModalidadeEvento;
import br.com.softhouse.dende.model.TipoEvento;

import java.time.LocalDateTime;

public class EventoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;

    private LocalDateTime inicio;
    private LocalDateTime fim;

    private TipoEvento tipo;
    private ModalidadeEvento modalidade;

    private String local;

    private double precoIngresso;
    private int capacidadeMaxima;

    private boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public ModalidadeEvento getModalidade() {
        return modalidade;
    }

    public void setModalidade(ModalidadeEvento modalidade) {
        this.modalidade = modalidade;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public double getPrecoIngresso() {
        return precoIngresso;
    }

    public void setPrecoIngresso(double precoIngresso) {
        this.precoIngresso = precoIngresso;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}