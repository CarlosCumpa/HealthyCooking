package com.upc.healthycookingd.service;

import com.upc.healthycookingd.entities.Metododepago;
import com.upc.healthycookingd.entities.Registrousuario;
import com.upc.healthycookingd.repository.RegistroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    @Transactional
    public Registrousuario save(Registrousuario registro){return registroRepository.save(registro);}
    public List<Registrousuario> list(){return registroRepository.findAll();}

    @Transactional
    public Registrousuario update(Registrousuario registro) throws Exception{
        registroRepository.findById(registro.getId()).orElseThrow(()->new Exception("No se actualizó"));
        return registroRepository.save(registro);
    }
    @Transactional
    public Registrousuario delete(Integer id) throws Exception{
        Registrousuario registro;
        registro = registroRepository.findById(id).orElseThrow(()-> new Exception("No existe registro"));
        registroRepository.delete(registro);
        return registro;
    }
    public List<Registrousuario> ObtenerRegistroNombre (String prefijo){
        return registroRepository.findRegistrousuarioByNombreStartingWith(prefijo);
    }

}

