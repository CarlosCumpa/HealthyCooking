package com.upc.backendhealthycooking.services;

import com.upc.backendhealthycooking.entities.Registro;
import com.upc.backendhealthycooking.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegistroService {
@Autowired
    private RegistroRepository registroRepository;
    public Registro save(Registro registro){return registroRepository.save(registro);}
    public List<Registro> list(){return registroRepository.findAll();}
    public Registro update(Registro registro) throws Exception{
        registroRepository.findById(registro.getId()).orElseThrow(()->new Exception("No se actualizó"));
        return registroRepository.save(registro);
    }
    public Registro delete(Long id) throws Exception{
        Registro registro;
        registro = registroRepository.findById(id).orElseThrow(()-> new Exception("No existe registro"));
        registroRepository.delete(registro);
        return registro;
    }

    public List<Registro> listFirstName(String prefijo ){
        return registroRepository.findRegistroByNameregistroStartingWith(prefijo);
    }
    public Registro search(Long id) throws Exception {
        return registroRepository.findById(id).orElseThrow(()->new Exception("No existe"));
    }


}
