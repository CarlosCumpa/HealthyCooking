package com.upc.healthycookingd.controller;

import com.upc.healthycookingd.dtos.EventoDTO;
import com.upc.healthycookingd.dtos.PerfilusuarioDTO;
import com.upc.healthycookingd.entities.Evento;
import com.upc.healthycookingd.entities.Perfilusuario;
import com.upc.healthycookingd.service.PerfilusuarioService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PerfilusuarioController {
    @Autowired
    public PerfilusuarioService perfilusuarioService;


    Logger logger = LoggerFactory.getLogger(EventoController.class);
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/perfil")
    public ResponseEntity<PerfilusuarioDTO> save(@RequestBody PerfilusuarioDTO perfilusuarioDTO){
        Perfilusuario perfilusuario;
        try {
            logger.debug("creando perfil");
            perfilusuario = convertToEntity(perfilusuarioDTO);
            perfilusuarioDTO = convertToDto(perfilusuarioService.save(perfilusuario));
        }catch (Exception e){
            logger.error("Error de creacion", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);

        }
        return new ResponseEntity<PerfilusuarioDTO>(perfilusuarioDTO,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/perfiles")
    public ResponseEntity<List<PerfilusuarioDTO>>  list(){
        List<PerfilusuarioDTO> listDTO;
        listDTO = convertToListDto(perfilusuarioService.list());
        return new ResponseEntity<List<PerfilusuarioDTO>>(listDTO, HttpStatus.OK);
    }

    private List<PerfilusuarioDTO> convertToListDto(List<Perfilusuario> list) {
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private PerfilusuarioDTO convertToDto(Perfilusuario perfilusuario) {

        ModelMapper modelMapper =  new ModelMapper();
        PerfilusuarioDTO a = modelMapper.map(perfilusuario, PerfilusuarioDTO.class);
        return a;
    }


    private Perfilusuario convertToEntity(PerfilusuarioDTO perfilusuarioDTO) {
        ModelMapper modelMapper= new ModelMapper();
        Perfilusuario a = modelMapper.map(perfilusuarioDTO, Perfilusuario.class);
        return a;
    }
}
