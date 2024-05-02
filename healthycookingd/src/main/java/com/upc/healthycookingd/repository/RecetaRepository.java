package com.upc.healthycookingd.repository;

import com.upc.healthycookingd.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Integer> {
    List<Receta> findRecetaByDescripcionrecetaStartingWith(String prefix);
    List<Receta> findByObjetivo_Id(Integer id);
}
