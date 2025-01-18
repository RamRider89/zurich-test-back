package com.zurich.prueba.controllers;

import com.zurich.prueba.entities.Cliente;
import com.zurich.prueba.services.ClienteService;
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

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Controlador para la gestión de clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * GET all Clientes
     * Obtener todos los clientes
     * @return array
     */
    @Operation(summary = "Obtener todos los clientes", description = "Retorna una lista con todos los clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida correctamente", 
                         content = { @Content(mediaType = "application/json", 
                                    schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "404", description = "No se encontraron clientes", content = @Content)
    })
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    /**
     * GET Cliente
     * Obtener por id
     * @return array
     */
    @Operation(summary = "Obtener un cliente por ID", description = "Retorna un cliente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado", 
                         content = { @Content(mediaType = "application/json", 
                                    schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    /**
     * POST Cliente
     * Crear
     * @return 
     */
    @Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente con la información proporcionada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado correctamente", 
                         content = { @Content(mediaType = "application/json", 
                                    schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error en el servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {

        // Ignorar el ID enviado en la solicitud
        cliente.setId(null);

        Cliente nuevoCliente = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    /**
     * PUT Cliente
     * Actualizar
     * @return 
     */
    @Operation(summary = "Actualizar un cliente", description = "Actualiza un cliente existente con la información proporcionada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente", 
                         content = { @Content(mediaType = "application/json", 
                                    schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error en el servidor", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteActualizado = clienteService.updateCliente(id, cliente);
        return clienteActualizado != null ? ResponseEntity.ok(clienteActualizado) : ResponseEntity.notFound().build();
    }

    /**
     * DEL Cliente
     * Eliminar
     * @return 
     */
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado correctamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error en el servidor", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

}