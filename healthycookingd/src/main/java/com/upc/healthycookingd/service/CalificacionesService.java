package com.upc.healthycookingd.service;


import com.upc.healthycookingd.entities.Calificaciones;
import com.upc.healthycookingd.repository.CalificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CalificacionesService {
    @Autowired
    private CalificacionesRepository calificacionesRepository;

    @Transactional
    public Calificaciones save(Calificaciones calificaciones){
        return calificacionesRepository.save(calificaciones);
    }

    public List<Calificaciones> list(){
        return calificacionesRepository.findAll();
    }

    public List<Calificaciones> obtenerReportePorCalificacion(Integer id){
        return calificacionesRepository.findCalificacionesByCalificacionEquals(id);
    }

    public List<Calificaciones> listcalificaReceta(Integer id){
        return calificacionesRepository.findByReceta_Id(id);
    }
}
