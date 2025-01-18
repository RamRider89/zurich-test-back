package com.zurich.prueba.services;

import com.zurich.prueba.entities.Poliza;
import com.zurich.prueba.repositories.PolizaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolizaService {

    @Autowired
    private PolizaRepository polizaRepository;

    public List<Poliza> getAllPolizas() {
        return polizaRepository.findAll();
    }

    public Poliza getPolizaById(Long id) {
        return polizaRepository.findById(id).orElse(null);
    }

    public Poliza createPoliza(Poliza poliza) {
        return polizaRepository.save(poliza);
    }

    public Poliza updatePoliza(Long id, Poliza poliza) {
        poliza.setId(id);
        return polizaRepository.save(poliza);
    }

    public void deletePoliza(Long id) {
        polizaRepository.deleteById(id);
    }

}