package com.upc.backendhealthycooking.services;

import com.upc.backendhealthycooking.entities.Eventos;
import com.upc.backendhealthycooking.repository.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventoService {

    @Autowired
    private EventosRepository eventosRepository;
    public Eventos save(Eventos eventos){return eventosRepository.save(eventos);}

    public List<Eventos> list(){return eventosRepository.findAll();}

    public Eventos update(Eventos eventos) throws Exception{ //se propaga la excepcion
        eventosRepository.findById(eventos.getId()).orElseThrow(()-> new Exception("No se actualizó"));
        return eventosRepository.save(eventos);//actualiza si existe el author
    }
    public Eventos delete(Long id) throws Exception {
        Eventos eventos;
        eventos = eventosRepository.findById(id).orElseThrow(()-> new Exception("No existe evento"));
        eventosRepository.delete(eventos);
        return eventos;
    }
    public List<Eventos> listFirstName(String prefijo){
        return eventosRepository.findEventosByNombreEventoStartingWith(prefijo);
    }
    public Eventos search(Long id) throws Exception {
        return eventosRepository.findById(id).orElseThrow(()->new Exception("No existe"));
    }



}
