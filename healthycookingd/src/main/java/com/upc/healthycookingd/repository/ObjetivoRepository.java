package com.upc.healthycookingd.repository;

import com.upc.healthycookingd.entities.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer> {
    List<Objetivo> findObjetivoByDescripcionStartingWith(String prefix);
}
