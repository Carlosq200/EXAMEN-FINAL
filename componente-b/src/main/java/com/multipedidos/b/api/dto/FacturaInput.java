package com.multipedidos.b.api.dto;

import java.util.List;

public class FacturaInput {

    private Long proveedorId;
    private List<PedidoReferenciaInput> pedidos;

    public Long getProveedorId() { return proveedorId; }
    public void setProveedorId(Long proveedorId) { this.proveedorId = proveedorId; }

    public List<PedidoReferenciaInput> getPedidos() { return pedidos; }
    public void setPedidos(List<PedidoReferenciaInput> pedidos) { this.pedidos = pedidos; }
}
