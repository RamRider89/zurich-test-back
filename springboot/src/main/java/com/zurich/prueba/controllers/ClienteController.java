package com.zurich.prueba.controllers;

import com.zurich.prueba.entities.Cliente;
import com.zurich.prueba.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteActualizado = clienteService.updateCliente(id, cliente);
        return clienteActualizado != null ? ResponseEntity.ok(clienteActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}