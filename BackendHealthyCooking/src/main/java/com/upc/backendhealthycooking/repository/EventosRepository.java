package com.upc.backendhealthycooking.repository;

import com.upc.backendhealthycooking.entities.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventosRepository extends JpaRepository<Eventos,Long> {

    List<Eventos>findEventosByNombreEventoStartingWith(String prefix);

}
