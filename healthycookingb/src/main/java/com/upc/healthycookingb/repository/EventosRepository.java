package com.upc.healthycookingb.repository;

import com.upc.healthycookingb.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventosRepository extends JpaRepository<Evento, Integer> {

    List<Evento>findEventoByNombreeventoStartingWith(String prefix);
}
