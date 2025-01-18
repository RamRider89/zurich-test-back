package com.zurich.prueba.services;

import com.zurich.prueba.entities.Cliente;
import com.zurich.prueba.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        cliente.setId(id); 
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}