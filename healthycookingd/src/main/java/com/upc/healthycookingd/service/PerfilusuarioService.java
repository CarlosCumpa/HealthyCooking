package com.upc.healthycookingd.service;

import com.upc.healthycookingd.entities.Perfilusuario;
import com.upc.healthycookingd.repository.PerfilusuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerfilusuarioService {
    @Autowired
    private PerfilusuarioRepository perfilusuarioRepository;

    @Transactional
    public Perfilusuario save(Perfilusuario perfilusuario){
        return perfilusuarioRepository.save(perfilusuario);
    }

    public List<Perfilusuario> list() {
        return perfilusuarioRepository.findAll();
    }
}
