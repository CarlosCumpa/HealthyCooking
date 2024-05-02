package com.upc.healthycookingd.repository;

import com.upc.healthycookingd.entities.Calificaciones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalificacionesRepository extends JpaRepository<Calificaciones, Integer> {

    List<Calificaciones> findCalificacionesByCalificacionEquals(Integer id);
    List<Calificaciones> findByReceta_Id(Integer id);
}
