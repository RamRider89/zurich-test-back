/**
 * Entidad: Poliza
 * Database: zurich_test_duarte
 * Table: polizas
 */
package com.zurich.prueba.entities;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "polizas")
@Data
public class Poliza {

    /*
     * id secuencial - polizas_id_seq
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "polizas_id_seq")
    @SequenceGenerator(name = "polizas_id_seq", sequenceName = "polizas_id_seq", allocationSize = 1)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // Marcar el campo id como solo lectura en Swagger UI
    private Long id;

    /** relacion con la tabla clientes by id */
    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;

    /** relacion con la tabla poliza_types by id */
    @Column(name = "type_poliza", nullable = false)
    private Integer typePoliza;

    @Column(name = "date_start", nullable = false)
    private LocalDate dateStart;

    @Column(name = "date_expiration", nullable = false)
    private LocalDate dateExpiration;

    @Column(nullable = false)
    private Long monto;

    /** @true: activa
     * @false: inactiva 
     * */
    @Column(nullable = false)
    private Boolean status;

}