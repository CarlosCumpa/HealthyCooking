package com.upc.healthycookingd.service;


import com.upc.healthycookingd.entities.Registrousuario;
import com.upc.healthycookingd.entities.Suscripcione;
import com.upc.healthycookingd.repository.SuscripcioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuscripcioneService {
    @Autowired
    private SuscripcioneRepository suscripcioneRepository;

    public Suscripcione save(Suscripcione suscripcione){return suscripcioneRepository.save(suscripcione);}

    public List<Suscripcione> list(){return suscripcioneRepository.findAll();}


    public Suscripcione update(Suscripcione suscripcione) throws Exception{ //se propaga la excepcion
        suscripcioneRepository.findById(suscripcione.getId()).orElseThrow(()-> new Exception("No se actualizó"));
        return suscripcioneRepository.save(suscripcione);//actualiza si existe la suscripción
    }

    public Suscripcione delete(Integer id) throws Exception {
        Suscripcione suscripcione;
        suscripcione = suscripcioneRepository.findById(id).orElseThrow(()-> new Exception("No existe suscripcion"));
        suscripcioneRepository.delete(suscripcione);
        return suscripcione;
    }

    public List<Suscripcione> ObtenerSuscripcionTipo (String prefijo){
        return suscripcioneRepository.findSuscripcioneByTiposuscripcionStartingWith(prefijo);
    }
}
