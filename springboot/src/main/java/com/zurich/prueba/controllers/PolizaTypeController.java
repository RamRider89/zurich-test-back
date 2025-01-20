package com.zurich.prueba.controllers;

import com.zurich.prueba.entities.PolizaType;
import com.zurich.prueba.services.PolizaTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/poliza-types")
@Tag(name = "Tipos de Póliza", description = "Controlador para la gestión de tipos de póliza")
public class PolizaTypeController {

    @Autowired
    private PolizaTypeService polizaTypeService;

    @Operation(summary = "Obtener todos los tipos de póliza", description = "Retorna una lista con todos los tipos de póliza")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tipos de póliza obtenida correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PolizaType.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontraron tipos de póliza", content = @Content)
    })
    @GetMapping
    public List<PolizaType> getAllPolizaTypes() {
        return polizaTypeService.getAllPolizaTypes();
    }
}