package com.upc.healthycookingb.repository;

import com.upc.healthycookingb.entities.Tipoplane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoplaneRepository extends JpaRepository<Tipoplane,Integer> {
    List<Tipoplane> findTipoplaneByTipodeplanStartingWith(String prefix);
}
