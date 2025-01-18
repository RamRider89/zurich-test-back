package com.zurich.prueba.controllers;

import com.zurich.prueba.entities.Poliza;
import com.zurich.prueba.services.PolizaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polizas")
public class PolizaController {

    @Autowired
    private PolizaService polizaService;

    @GetMapping
    public List<Poliza> getAllPolizas() {
        return polizaService.getAllPolizas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poliza> getPolizaById(@PathVariable Long id) {
        Poliza poliza = polizaService.getPolizaById(id);
        return poliza != null ? ResponseEntity.ok(poliza) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Poliza> createPoliza(@RequestBody Poliza poliza) {
        Poliza nuevaPoliza = polizaService.createPoliza(poliza);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPoliza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poliza> updatePoliza(@PathVariable Long id, @RequestBody Poliza poliza) {
        Poliza polizaActualizada = polizaService.updatePoliza(id, poliza);
        return polizaActualizada != null ? ResponseEntity.ok(polizaActualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoliza(@PathVariable Long id) {
        polizaService.deletePoliza(id);
        return ResponseEntity.noContent().build();
    }

}