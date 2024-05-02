package com.upc.healthycookingd.repository;

import com.upc.healthycookingd.entities.Metododepago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetododepagoRepository extends JpaRepository<Metododepago, Integer> {
    List<Metododepago> findMetododepagoByIdEquals(Integer id);
}
