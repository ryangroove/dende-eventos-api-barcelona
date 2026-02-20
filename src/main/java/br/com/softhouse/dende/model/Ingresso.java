package br.com.softhouse.dende.model;

public class Ingresso {

    private Long id;
    private Long usuarioId;
    private Long eventoId;
    private double valorPago;
    private boolean ativo;

    public Ingresso() {
        this.ativo = true;
    }

    public void cancelar() {
        this.ativo = false;
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getEventoId() { return eventoId; }
    public void setEventoId(Long eventoId) { this.eventoId = eventoId; }

    public double getValorPago() { return valorPago; }
    public void setValorPago(double valorPago) { this.valorPago = valorPago; }

    public boolean isAtivo() { return ativo; }
}