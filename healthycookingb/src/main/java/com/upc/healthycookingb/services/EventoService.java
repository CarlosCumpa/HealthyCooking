package com.upc.healthycookingb.services;

import com.upc.healthycookingb.entities.Evento;
import com.upc.healthycookingb.repository.EventosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventosRepository eventosRepository;
    @Transactional
    public Evento save(Evento eventos){return eventosRepository.save(eventos);}

    public List<Evento> list(){return eventosRepository.findAll();}

    @Transactional
    public Evento update(Evento eventos) throws Exception{ //se propaga la excepcion
        eventosRepository.findById(eventos.getId()).orElseThrow(()-> new Exception("No se actualizó"));
        return eventosRepository.save(eventos);//actualiza si existe el author
    }
    @Transactional
    public Evento delete(Integer id) throws Exception {
        Evento eventos;
        eventos = eventosRepository.findById(id).orElseThrow(()-> new Exception("No existe evento"));
        eventosRepository.delete(eventos);
        return eventos;
    }
    public List<Evento> listFirstEvent(String prefijo){
        return eventosRepository.findEventoByNombreeventoStartingWith(prefijo);
    }
    public Evento search(Integer id) throws Exception {
        return eventosRepository.findById(id).orElseThrow(()->new Exception("No existe"));
    }

}
