package com.upc.healthycookingd.repository;

import com.upc.healthycookingd.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventosRepository extends JpaRepository<Evento, Integer> {

    List<Evento>findEventoByNombreeventoStartingWith(String prefix);
}
