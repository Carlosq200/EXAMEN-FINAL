package com.multipedidos.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.multipedidos.repository.ClienteRepository;
import com.multipedidos.entity.Cliente;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repo;

    @PostMapping
    public Cliente crear(@RequestBody Cliente c) {
        return repo.save(c);
    }

    @GetMapping
    public List<Cliente> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Cliente obtener(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }
}
