package com.upc.healthycookingb.services;

import com.upc.healthycookingb.entities.Tipoplane;
import com.upc.healthycookingb.repository.TipoplaneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoplaneService {

    @Autowired
    private TipoplaneRepository tipoplaneRepository;



    public Tipoplane save(Tipoplane tipoDePlanes){return tipoplaneRepository.save(tipoDePlanes);}

    public List<Tipoplane> list(){return tipoplaneRepository.findAll();}




    public Tipoplane update(Tipoplane tipoDePlanes) throws Exception{ //se propaga la excepcion
        tipoplaneRepository.findById(tipoDePlanes.getId()).orElseThrow(()-> new Exception("No se actualizó"));
        return tipoplaneRepository.save(tipoDePlanes);//actualiza si existe el author
    }



    public Tipoplane delete(Integer id) throws Exception {
        Tipoplane tipoDePlanes;
        tipoDePlanes = tipoplaneRepository.findById(id).orElseThrow(()-> new Exception("No existe evento"));
        tipoplaneRepository.delete(tipoDePlanes);
        return tipoDePlanes;
    }

    public List<Tipoplane> listFirstPlan(String prefijo){
        return tipoplaneRepository.findTipoplaneByTipodeplanStartingWith(prefijo);
    }

    public Tipoplane search(Integer idP) throws Exception {
        return tipoplaneRepository.findById(idP).orElseThrow(()->new Exception("No existe"));
    }
}
