package com.upc.healthycookingd.service;


import com.upc.healthycookingd.entities.Metododepago;
import com.upc.healthycookingd.entities.Objetivo;
import com.upc.healthycookingd.repository.ObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetivoService {
    @Autowired
    private ObjetivoRepository objetivoRepository;

    public Objetivo save(Objetivo objetivo){
        return objetivoRepository.save(objetivo);
    }

    public List<Objetivo> list(){
        return objetivoRepository.findAll();
    }

    public List<Objetivo> obtenerMetodoPorDescripcion(String prefijo){
        return objetivoRepository.findObjetivoByDescripcionStartingWith(prefijo);
    }

    public Objetivo update(Objetivo objetivo) throws Exception{ //se propaga la excepcion
        objetivoRepository.findById(objetivo.getId()).orElseThrow(()-> new Exception("No se actualizó"));
        return objetivoRepository.save(objetivo);//actualiza si existe el objetivo
    }

}
