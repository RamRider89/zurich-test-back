package com.zurich.prueba.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {

    /*
     * id secuencial - clientes_id_seq
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientes_id_seq")
    @SequenceGenerator(name = "clientes_id_seq", sequenceName = "clientes_id_seq", allocationSize = 1)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // Marcar el campo id como solo lectura en Swagger UI
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

}