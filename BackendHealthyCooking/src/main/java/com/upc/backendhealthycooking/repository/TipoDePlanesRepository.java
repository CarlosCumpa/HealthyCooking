package com.upc.backendhealthycooking.repository;

import com.upc.backendhealthycooking.entities.TipoDePlanes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoDePlanesRepository extends JpaRepository<TipoDePlanes,Long> {
    List<TipoDePlanes>findAllByIdPlanesEquals(Long idPlans);
}
