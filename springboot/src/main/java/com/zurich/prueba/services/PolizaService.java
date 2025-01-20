/**
 * Servicio: Poliza
 */
package com.zurich.prueba.services;

import com.zurich.prueba.entities.Poliza;
import com.zurich.prueba.repositories.PolizaRepository;
import com.zurich.prueba.repositories.PolizaTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PolizaService {

    @Autowired
    private PolizaRepository polizaRepository;

    @Autowired
    private PolizaTypeRepository polizaTypeRepository; 

    /**
     * Obtiene todas las pólizas.
     *
     * @return Array de polizas
     */
    public List<Poliza> getAllPolizas() {
        return polizaRepository.findAll();
    }

    /**
     * Obtiene una póliza por su ID.
     *
     * @param id
     * @return poliza || null
     */
    public Poliza getPolizaById(Long id) {
        return polizaRepository.findById(id).orElse(null);
    }

    /**
     * Crea una nueva póliza.
     *
     * @param poliza
     * @return poliza
     * @throws ResponseStatusException si el tipo de póliza no es válido.
     */
    public Poliza createPoliza(Poliza poliza) {
        // Validar que el tipo de póliza existe
        if (!polizaTypeRepository.existsById(poliza.getTypePoliza())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de póliza no válida");
        }
        return polizaRepository.save(poliza);
    }

     /**
     * Actualiza una póliza existente.
     *
     * @param id
     * @param poliza
     * @return poliza || null
     * @throws ResponseStatusException si el tipo de póliza no es válido.
     */
    public Poliza updatePoliza(Long id, Poliza poliza) {
        // Validando que el tipo de póliza existe
        if (!polizaTypeRepository.existsById(poliza.getTypePoliza())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de póliza no válido");
        }

        // Validando que la póliza existe
        if (!polizaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Póliza no encontrada");
        }

        poliza.setId(id);
        return polizaRepository.save(poliza);
    }

    /**
     * Elimina una póliza por su ID.
     *
     * @param id
     */
    public void deletePoliza(Long id) {
         // Validando que la póliza existe
        if (!polizaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Póliza no encontrada");
        }
        polizaRepository.deleteById(id);
    }

    /**
     * Filtrar polizas
     */
    public List<Poliza> filterPolizas(Integer tipo, Boolean estado, LocalDate fechaInicio, LocalDate fechaFin) {
        return polizaRepository.filtrarPolizas(tipo, estado, fechaInicio, fechaFin);
    }

    /**
     * Obtener polizas por id de cliente
     */
    public List<Poliza> getPolizasByClienteId(Long clienteId) {
        return polizaRepository.findByClienteId(clienteId);
    }

}