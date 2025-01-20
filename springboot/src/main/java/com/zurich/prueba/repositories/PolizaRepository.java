/**
 * Repositorio: Poliza
 * Database: zurich_test_duarte
 * Table: polizas
 */
package com.zurich.prueba.repositories;

import com.zurich.prueba.entities.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Long> {

    @Query(value = "SELECT * FROM filtrar_polizas(:tipo, :estado, :fechaInicio, :fechaFin)", nativeQuery = true)
    List<Poliza> filtrarPolizas(
            @Param("tipo") Integer tipo,
            @Param("estado") Boolean estado,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );

    public List<Poliza> findByClienteId(Long clienteId);
}