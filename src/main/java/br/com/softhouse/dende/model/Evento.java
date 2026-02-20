package br.com.softhouse.dende.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Evento {

    private Long id;
    private Long organizadorId;
    private String paginaEvento;
    private String nome;
    private String descricao;

    private LocalDateTime inicio;
    private LocalDateTime fim;

    private TipoEvento tipo;
    private ModalidadeEvento modalidade;
    private String local;

    private int capacidadeMaxima;
    private int ingressosVendidos;

    private double precoIngresso;
    private boolean estorna;
    private double taxaEstorno;

    private boolean ativo;
    private Long eventoPrincipalId;

    public Evento() {
        this.ativo = false;
    }

    public void validarCadastro() {
        LocalDateTime agora = LocalDateTime.now();

        if (inicio.isBefore(agora))
            throw new IllegalArgumentException("Data de início inválida");

        if (fim.isBefore(inicio) || fim.isBefore(agora))
            throw new IllegalArgumentException("Data de fim inválida");

        if (Duration.between(inicio, fim).toMinutes() < 30)
            throw new IllegalArgumentException("Evento deve ter no mínimo 30 minutos");

        if (capacidadeMaxima <= 0)
            throw new IllegalArgumentException("Capacidade inválida");

        if (precoIngresso < 0)
            throw new IllegalArgumentException("Preço inválido");
    }

    public boolean temVagas() {
        return ingressosVendidos < capacidadeMaxima;
    }

    public boolean jaFinalizou() {
        return fim.isBefore(LocalDateTime.now());
    }

    public void venderIngresso() {
        if (!temVagas())
            throw new IllegalStateException("Evento lotado");
        ingressosVendidos++;
    }

    public void cancelarIngresso() {
        ingressosVendidos--;
    }

    // ===== GETTERS & SETTERS (todos) =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getOrganizadorId() { return organizadorId; }
    public void setOrganizadorId(Long organizadorId) { this.organizadorId = organizadorId; }

    public String getPaginaEvento() { return paginaEvento; }
    public void setPaginaEvento(String paginaEvento) { this.paginaEvento = paginaEvento; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getInicio() { return inicio; }
    public void setInicio(LocalDateTime inicio) { this.inicio = inicio; }

    public LocalDateTime getFim() { return fim; }
    public void setFim(LocalDateTime fim) { this.fim = fim; }

    public TipoEvento getTipo() { return tipo; }
    public void setTipo(TipoEvento tipo) { this.tipo = tipo; }

    public ModalidadeEvento getModalidade() { return modalidade; }
    public void setModalidade(ModalidadeEvento modalidade) { this.modalidade = modalidade; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public int getCapacidadeMaxima() { return capacidadeMaxima; }
    public void setCapacidadeMaxima(int capacidadeMaxima) { this.capacidadeMaxima = capacidadeMaxima; }

    public double getPrecoIngresso() { return precoIngresso; }
    public void setPrecoIngresso(double precoIngresso) { this.precoIngresso = precoIngresso; }

    public boolean isEstorna() { return estorna; }
    public void setEstorna(boolean estorna) { this.estorna = estorna; }

    public double getTaxaEstorno() { return taxaEstorno; }
    public void setTaxaEstorno(double taxaEstorno) { this.taxaEstorno = taxaEstorno; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public Long getEventoPrincipalId() { return eventoPrincipalId; }
    public void setEventoPrincipalId(Long eventoPrincipalId) { this.eventoPrincipalId = eventoPrincipalId; }
}