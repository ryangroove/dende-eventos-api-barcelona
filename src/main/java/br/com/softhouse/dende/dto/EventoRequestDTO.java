package br.com.softhouse.dende.dto;

import br.com.softhouse.dende.model.ModalidadeEvento;
import br.com.softhouse.dende.model.TipoEvento;

import java.time.LocalDateTime;

public class EventoRequestDTO {

    private String paginaEvento;
    private String nome;
    private String descricao;

    private LocalDateTime inicio;
    private LocalDateTime fim;

    private TipoEvento tipo;
    private ModalidadeEvento modalidade;
    private String local;

    private int capacidadeMaxima;

    private double precoIngresso;
    private boolean estorna;
    private double taxaEstorno;

    public String getPaginaEvento() {
        return paginaEvento;
    }

    public void setPaginaEvento(String paginaEvento) {
        this.paginaEvento = paginaEvento;
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

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public double getPrecoIngresso() {
        return precoIngresso;
    }

    public void setPrecoIngresso(double precoIngresso) {
        this.precoIngresso = precoIngresso;
    }

    public boolean isEstorna() {
        return estorna;
    }

    public void setEstorna(boolean estorna) {
        this.estorna = estorna;
    }

    public double getTaxaEstorno() {
        return taxaEstorno;
    }

    public void setTaxaEstorno(double taxaEstorno) {
        this.taxaEstorno = taxaEstorno;
    }
}