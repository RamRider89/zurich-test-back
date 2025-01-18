/**
 * Entidad: PolizaType
 * Database: zurich_test_duarte
 * Table: poliza_types
 */
package com.zurich.prueba.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "poliza_types")
@Data
public class PolizaType {

    /** relacion con la tabla polizas by id - type_poliza 
     * id secuencial
    */
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

}