package com.multipedidos.b.api;

import com.multipedidos.b.api.dto.FacturaInput;
import com.multipedidos.b.api.dto.ProveedorInput;
import com.multipedidos.b.domain.Factura;
import com.multipedidos.b.domain.Proveedor;
import com.multipedidos.b.repository.FacturaRepository;
import com.multipedidos.b.repository.ProveedorRepository;
import com.multipedidos.b.service.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ComponenteBController {

    private final ProveedorRepository proveedorRepository;
    private final FacturaRepository facturaRepository;
    private final FacturaService facturaService;

    public ComponenteBController(ProveedorRepository proveedorRepository,
                                 FacturaRepository facturaRepository,
                                 FacturaService facturaService) {
        this.proveedorRepository = proveedorRepository;
        this.facturaRepository = facturaRepository;
        this.facturaService = facturaService;
    }

    @PostMapping("/proveedores")
    @ResponseStatus(HttpStatus.CREATED)
    public Proveedor crearProveedor(@RequestBody ProveedorInput input) {
        Proveedor p = new Proveedor();
        p.setNombre(input.getNombre());
        p.setCorreo(input.getCorreo());
        return proveedorRepository.save(p);
    }

    @GetMapping("/proveedores")
    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }

    @PostMapping("/facturas")
    @ResponseStatus(HttpStatus.CREATED)
    public Factura crearFactura(@RequestBody FacturaInput input) {
        return facturaService.crearFactura(input);
    }

    @GetMapping("/facturas")
    public List<Factura> listarFacturas() {
        return facturaRepository.findAll();
    }

    @GetMapping("/facturas/{id}")
    public Factura obtenerFactura(@PathVariable Long id) {
        return facturaRepository.findById(id).orElseThrow();
    }
}
