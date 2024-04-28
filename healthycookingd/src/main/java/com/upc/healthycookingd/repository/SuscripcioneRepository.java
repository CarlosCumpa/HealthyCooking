package com.upc.healthycookingd.repository;

import com.upc.healthycookingd.entities.Suscripcione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuscripcioneRepository extends JpaRepository<Suscripcione, Integer> {
    List<Suscripcione> findSuscripcioneByTiposuscripcionStartingWith(String prefix);
}
