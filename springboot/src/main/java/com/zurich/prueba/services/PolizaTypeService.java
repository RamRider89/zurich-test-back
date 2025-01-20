package com.zurich.prueba.services;

import com.zurich.prueba.entities.PolizaType;
import com.zurich.prueba.repositories.PolizaTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolizaTypeService {

    @Autowired
    private PolizaTypeRepository polizaTypeRepository;

    /**
     * Obeter todos los tipos de polizas
     */
    public List<PolizaType> getAllPolizaTypes() {
        return polizaTypeRepository.findAll();
    }

}