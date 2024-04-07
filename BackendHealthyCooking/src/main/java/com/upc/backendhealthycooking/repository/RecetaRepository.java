package com.upc.backendhealthycooking.repository;

import com.upc.backendhealthycooking.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta>findRecetaByIngredienteStartingWith(String prefix);

}
