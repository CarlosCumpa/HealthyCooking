package com.upc.healthycookingd.service;

import com.upc.healthycookingd.entities.Objetivo;
import com.upc.healthycookingd.entities.Receta;
import com.upc.healthycookingd.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecetaService {
    @Autowired
    private RecetaRepository recetaRepository;

    @Transactional
    public Receta save(Receta receta){
        return recetaRepository.save(receta);
    }

    public List<Receta> list(){
        return recetaRepository.findAll();
    }

    public List<Receta> obtenerReportePorDescripcion(String prefijo){
        return recetaRepository.findRecetaByDescripcionrecetaStartingWith(prefijo);
    }

    public Receta update(Receta receta) throws Exception{
        recetaRepository.findById(receta.getId()).orElseThrow(()-> new Exception("No se actualizó"));
        return recetaRepository.save(receta);
    }

    public List<Receta> listRecetaObjetivo(Integer id){
        return recetaRepository.findByObjetivo_Id(id);
    }
}
