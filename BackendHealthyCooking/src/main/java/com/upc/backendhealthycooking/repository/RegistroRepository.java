package com.upc.backendhealthycooking.repository;

import com.upc.backendhealthycooking.entities.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

    List<Registro> findRegistroByNameregistroStartingWith(String prefix);
}
