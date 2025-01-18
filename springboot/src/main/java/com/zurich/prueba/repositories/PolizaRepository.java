/**
 * Repositorio: Poliza
 * Database: zurich_test_duarte
 * Table: polizas
 */
package com.zurich.prueba.repositories;

import com.zurich.prueba.entities.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Long> {
}