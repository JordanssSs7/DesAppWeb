package com.tecsup.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tecsup.model.Detalle;
import com.tecsup.service.DetalleService;

@RestController
@RequestMapping("/api/detalles")
public class DetalleController {
    @Autowired
    private DetalleService service;

    @GetMapping
    public List<Detalle> listar() { return service.listar(); }

    @PostMapping
    public ResponseEntity<Detalle> guardar(@RequestBody Detalle detalle) {
        return ResponseEntity.status(201).body(service.guardar(detalle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detalle> obtener(@PathVariable Long id) {
        Detalle d = service.obtener(id);
        if (d == null) { return ResponseEntity.notFound().build(); }
        return ResponseEntity.ok(d);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Detalle> actualizar(@PathVariable Long id, @RequestBody Detalle d) {
        Detalle existente = service.obtener(id);
        if (existente == null) { return ResponseEntity.notFound().build(); }

        existente.setCantidad(d.getCantidad());
        existente.setPrecio(d.getPrecio());
        existente.setSubtotal(d.getSubtotal());
        existente.setVenta(d.getVenta());
        existente.setProducto(d.getProducto());

        return ResponseEntity.ok(service.guardar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Detalle d = service.obtener(id);
        if (d == null) { return ResponseEntity.notFound().build(); }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}