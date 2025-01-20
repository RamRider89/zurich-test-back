package com.zurich.prueba.controllers;

import com.zurich.prueba.entities.Poliza;
import com.zurich.prueba.services.PolizaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/polizas")
@Tag(name = "Pólizas", description = "Controlador para la gestión de pólizas")
public class PolizaController {

    @Autowired
    private PolizaService polizaService;

    /**
     * GET all Polizas
     * Obtener todas
     * @return array
     */
    @Operation(summary = "Obtener todas las pólizas", description = "Retorna una lista con todas las pólizas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pólizas obtenida correctamente", 
                         content = { @Content(mediaType = "application/json", 
                                    schema = @Schema(implementation = Poliza.class)) }),
            @ApiResponse(responseCode = "404", description = "No se encontraron pólizas", content = @Content)
    })
    @GetMapping
    public List<Poliza> getAllPolizas() {
        return polizaService.getAllPolizas();
    }

    /**
     * GET Poliza
     * Obtener por id
     * @return array
     */
    @Operation(summary = "Obtener una póliza por ID", description = "Retorna una póliza por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Póliza encontrada", 
                         content = { @Content(mediaType = "application/json", 
                                    schema = @Schema(implementation = Poliza.class)) }),
            @ApiResponse(responseCode = "404", description = "Póliza no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Poliza> getPolizaById(@PathVariable Long id) {
        Poliza poliza = polizaService.getPolizaById(id);
        return poliza != null ? ResponseEntity.ok(poliza) : ResponseEntity.notFound().build();
    }

    /**
     * POST Poliza
     * Crear
     * @return 
     */
    @Operation(summary = "Crear una nueva póliza", description = "Crea una nueva póliza con la información dada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Póliza creada correctamente", 
                         content = { @Content(mediaType = "application/json", 
                                    schema = @Schema(implementation = Poliza.class)) }),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error en el servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Poliza> createPoliza(@RequestBody Poliza poliza) {

        try {
            // Ignorar el ID enviado en la solicitud
            poliza.setId(null);

            Poliza nuevaPoliza = polizaService.createPoliza(poliza);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPoliza);

        } catch (ResponseStatusException ex) {
            return ResponseEntity.badRequest().build();
        }

    }

    /**
     * PUT Poliza
     * Actualizar
     * @return 
     */
    @Operation(summary = "Actualizar una póliza", description = "Actualiza una póliza existente con la información dada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Póliza actualizada correctamente", 
                         content = { @Content(mediaType = "application/json", 
                                    schema = @Schema(implementation = Poliza.class)) }),
            @ApiResponse(responseCode = "404", description = "Póliza no encontrada", content = @Content),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error en el servidor", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Poliza> updatePoliza(
            @PathVariable Long id, 
            @RequestBody Poliza poliza
        ) {

            try {
                Poliza polizaActualizada = polizaService.updatePoliza(id, poliza);
                return polizaActualizada != null ? ResponseEntity.ok(polizaActualizada) : ResponseEntity.notFound().build();

            } catch (ResponseStatusException ex) {

                return ResponseEntity.badRequest().build();
            }
    }

    /**
     * DEL Poliza
     * Eliminar
     * @return 
     */
    @Operation(summary = "Eliminar una póliza", description = "Elimina una póliza por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Póliza eliminada correctamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Póliza no encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error en el servidor", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePoliza(@PathVariable Long id) {
        try {
            polizaService.deletePoliza(id);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());

        }
    }


    /**
     * GET Polizas
     * Filtro by tipo estado fechaInicio fechaFin
     * @return array
     */
    @Operation(summary = "Filtrar pólizas", description = "Retorna una lista de pólizas que cumplen con los filtros especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pólizas filtrada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Poliza.class))}),
            @ApiResponse(responseCode = "400", description = "Error en los parámetros de filtro", content = @Content)
    })
    @GetMapping("/filtrar")
    public List<Poliza> filterPolizas(
            @RequestParam(value = "tipo", required = false) Integer tipo,
            @RequestParam(value = "estado", required = false) Boolean estado,
            @RequestParam(value = "fechaInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
    ) {
        return polizaService.filterPolizas(tipo, estado, fechaInicio, fechaFin);
    }

    /**
     * GET Polizas
     * Filtro by idCliente
     * @return array
     */
     @Operation(summary = "Obtener pólizas por ID de cliente", 
              description = "Retorna una lista de pólizas asociadas al ID de cliente especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pólizas obtenida correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Poliza.class))}),
            @ApiResponse(responseCode = "404", description = "No se encontraron pólizas para el cliente", content = @Content)
    })
    @GetMapping("/cliente/{clienteId}")
    public List<Poliza> getPolizasByClienteId(@PathVariable Long clienteId) {
        return polizaService.getPolizasByClienteId(clienteId);
    }

    /**
     * Solicitud de cancelacion ficticia
     */
    @PostMapping("/{polizaId}/cancelar")
    public ResponseEntity<String> solicitarCancelacion(@PathVariable Long polizaId) {
        try {
            // Generar un folio ficticio y devolverlo como JSON
            String folio = generarFolioFicticio();
            return ResponseEntity.ok("{\"folio\": \"" + folio + "\"}"); 

        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

    // generar un folio ficticio de ejemplo
    private String generarFolioFicticio() {
        return UUID.randomUUID().toString();
    }
}