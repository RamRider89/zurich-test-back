/**
 * Servicio: Cliente
 */
package com.zurich.prueba.services;

import com.zurich.prueba.entities.Cliente;
import com.zurich.prueba.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Obtiene todos los clientes.
     *
     * @return Array de clientes
     */
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param id
     * @return cliente
     */
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    /**
     * Crea un nuevo cliente.
     *
     * @param cliente
     * @return cliente creado.
     */
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Actualiza un cliente existente.
     *
     * @param id
     * @param cliente
     * @return cliente || null
     */
    public Cliente updateCliente(Long id, Cliente cliente) {
        // Validando que el cliente existe
        if (!clienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }
        cliente.setId(id); 
        return clienteRepository.save(cliente);
    }
    
    /**
     * Elimina un cliente por su ID.
     *
     * @param id
     */
    public void deleteCliente(Long id) {
        // Validando que el cliente existe
        if (!clienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }
        clienteRepository.deleteById(id);
    }

}