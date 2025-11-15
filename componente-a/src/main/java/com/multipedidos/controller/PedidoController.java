package com.multipedidos.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.multipedidos.repository.PedidoRepository;
import com.multipedidos.entity.Pedido;
import com.multipedidos.OperacionesNegocio;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repo;

    @PostMapping
    public Pedido crear(@RequestBody Pedido p) {
        double totalConIVA = OperacionesNegocio.calcularTotalConIVA(p.getTotal());
        p.setTotal(totalConIVA);
        return repo.save(p);
    }

    @GetMapping
    public List<Pedido> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Pedido obtener(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }
}
