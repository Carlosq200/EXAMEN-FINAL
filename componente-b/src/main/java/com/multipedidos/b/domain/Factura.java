package com.multipedidos.b.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long proveedorId;

    @ElementCollection
    @CollectionTable(name = "factura_pedidos", joinColumns = @JoinColumn(name = "factura_id"))
    private List<PedidoReferenciaEmbeddable> pedidos = new ArrayList<>();

    private double totalFactura;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProveedorId() { return proveedorId; }
    public void setProveedorId(Long proveedorId) { this.proveedorId = proveedorId; }

    public List<PedidoReferenciaEmbeddable> getPedidos() { return pedidos; }
    public void setPedidos(List<PedidoReferenciaEmbeddable> pedidos) { this.pedidos = pedidos; }

    public double getTotalFactura() { return totalFactura; }
    public void setTotalFactura(double totalFactura) { this.totalFactura = totalFactura; }
}
