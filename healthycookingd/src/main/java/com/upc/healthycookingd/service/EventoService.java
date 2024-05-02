package com.upc.healthycookingd.service;
import com.upc.healthycookingd.entities.Evento;
import com.upc.healthycookingd.repository.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventosRepository eventosRepository;

    public Evento save(Evento eventos){return eventosRepository.save(eventos);}

    public List<Evento> list(){return eventosRepository.findAll();}


    public Evento update(Evento eventos) throws Exception{ //se propaga la excepcion
        eventosRepository.findById(eventos.getId()).orElseThrow(()-> new Exception("No se actualizó"));
        return eventosRepository.save(eventos);//actualiza si existe el author
    }

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