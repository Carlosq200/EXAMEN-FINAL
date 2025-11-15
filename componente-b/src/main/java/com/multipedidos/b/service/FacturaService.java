package com.multipedidos.b.service;

import com.multipedidos.b.api.dto.FacturaInput;
import com.multipedidos.b.api.dto.PedidoReferenciaInput;
import com.multipedidos.b.domain.Factura;
import com.multipedidos.b.domain.PedidoReferenciaEmbeddable;
import com.multipedidos.b.repository.FacturaRepository;
import com.multipedidos.common.OperacionesNegocio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public Factura crearFactura(FacturaInput input) {
        Factura f = new Factura();
        f.setProveedorId(input.getProveedorId());

        List<PedidoReferenciaEmbeddable> pedidos =
                input.getPedidos().stream().map(this::mapearPedido).collect(Collectors.toList());
        f.setPedidos(pedidos);

        double subtotal = pedidos.stream().mapToDouble(PedidoReferenciaEmbeddable::getTotal).sum();
        double totalConIva = OperacionesNegocio.calcularTotalConIVA(subtotal);
        f.setTotalFactura(totalConIva);

        return facturaRepository.save(f);
    }

    private PedidoReferenciaEmbeddable mapearPedido(PedidoReferenciaInput input) {
        PedidoReferenciaEmbeddable p = new PedidoReferenciaEmbeddable();
        p.setPedidoId(input.getPedidoId());
        p.setTotal(input.getTotal());
        return p;
    }
}
