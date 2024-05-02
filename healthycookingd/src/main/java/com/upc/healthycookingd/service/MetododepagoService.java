package com.upc.healthycookingd.service;

import com.upc.healthycookingd.entities.Metododepago;
import com.upc.healthycookingd.entities.Receta;
import com.upc.healthycookingd.repository.MetododepagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetododepagoService {
    @Autowired
    private MetododepagoRepository metododepagoRepository;

    public Metododepago save(Metododepago metododepago){return metododepagoRepository.save(metododepago);}

    public List<Metododepago> list(){return metododepagoRepository.findAll();}

    public List<Metododepago> obtenerMetodoPorid(Integer id){
        return metododepagoRepository.findMetododepagoByIdEquals(id);
    }


    public Metododepago update(Metododepago metododepago) throws Exception{ //se propaga la excepcion
        metododepagoRepository.findById(metododepago.getId()).orElseThrow(()-> new Exception("No se actualizó"));
        return metododepagoRepository.save(metododepago);//actualiza si existe el metodo de pago
    }

    public Metododepago delete(Integer id) throws Exception {
        Metododepago metododepago;
        metododepago = metododepagoRepository.findById(id).orElseThrow(()-> new Exception("No existe suscripcion"));
        metododepagoRepository.delete(metododepago);
        return metododepago;
    }
}
