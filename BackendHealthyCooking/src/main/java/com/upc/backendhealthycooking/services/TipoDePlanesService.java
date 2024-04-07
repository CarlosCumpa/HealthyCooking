package com.upc.backendhealthycooking.services;


import com.upc.backendhealthycooking.entities.TipoDePlanes;
import com.upc.backendhealthycooking.repository.TipoDePlanesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDePlanesService {
    @Autowired
    private TipoDePlanesRepository tipoDePlanesRepository;

    public TipoDePlanes save(TipoDePlanes tipoDePlanes){return tipoDePlanesRepository.save(tipoDePlanes);}

    public List<TipoDePlanes> list(){return tipoDePlanesRepository.findAll();}


    public TipoDePlanes update(TipoDePlanes tipoDePlanes) throws Exception{ //se propaga la excepcion
        tipoDePlanesRepository.findById(tipoDePlanes.getIdPlanes()).orElseThrow(()-> new Exception("No se actualizó"));
        return tipoDePlanesRepository.save(tipoDePlanes);//actualiza si existe el author
    }

    public TipoDePlanes delete(Long id) throws Exception {
        TipoDePlanes tipoDePlanes;
        tipoDePlanes = tipoDePlanesRepository.findById(id).orElseThrow(()-> new Exception("No existe evento"));
        tipoDePlanesRepository.delete(tipoDePlanes);
        return tipoDePlanes;
    }

    public List<TipoDePlanes> listFirstId(Long idP){
        return tipoDePlanesRepository.findAllByIdPlanesEquals(idP);
    }

    public TipoDePlanes search(Long idPa) throws Exception {
        return tipoDePlanesRepository.findById(idPa).orElseThrow(()->new Exception("No existe"));
    }

}
