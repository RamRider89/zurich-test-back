/**
 * Repositorio: PolizaType
 * Database: zurich_test_duarte
 * Table: poliza_types
 */
package com.zurich.prueba.repositories;

import com.zurich.prueba.entities.PolizaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolizaTypeRepository extends JpaRepository<PolizaType, Integer> {
}