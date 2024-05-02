package com.upc.healthycookingd.repository;

import com.upc.healthycookingd.entities.Registrousuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroRepository extends JpaRepository<Registrousuario, Integer> {
    List<Registrousuario> findRegistrousuarioByNombreStartingWith(String prefix);
}
