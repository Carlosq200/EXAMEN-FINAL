package com.multipedidos.b.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class PedidoReferenciaEmbeddable {

    private Long pedidoId;
    private double total;

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
