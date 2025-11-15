package com.multipedidos.b.api.dto;

public class PedidoReferenciaInput {

    private Long pedidoId;
    private double total;

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
