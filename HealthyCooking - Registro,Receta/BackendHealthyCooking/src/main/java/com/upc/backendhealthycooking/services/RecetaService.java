package com.upc.backendhealthycooking.services;

import com.upc.backendhealthycooking.entities.Receta;
import com.upc.backendhealthycooking.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService {

    @Autowired

    private RecetaRepository recetaRepository;
    public Receta save(Receta receta){ return recetaRepository.save(receta);}
    public List<Receta> list(){return recetaRepository.findAll();}
    public Receta update(Receta receta) throws Exception{
        recetaRepository.findById(receta.getId()).orElseThrow(()-> new Exception("No se actualizó"));
        return recetaRepository.save(receta);
    }

    public Receta delete(Long id) throws Exception{
        Receta receta;
        receta = recetaRepository.findById(id).orElseThrow(()-> new Exception("No existe evento"));
        recetaRepository.delete(receta);
        return receta;
    }

    public List<Receta> listFirstName(String prefijo){
        return recetaRepository.findRecetaByIngredienteStartingWith(prefijo);
    }

    public Receta search(Long id) throws Exception{
        return recetaRepository.findById(id).orElseThrow(()-> new Exception("No existe"));
    }
}
