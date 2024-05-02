package com.upc.healthycookingd.controller;

import com.upc.healthycookingd.dtos.ObjetivoDTO;
import com.upc.healthycookingd.entities.Objetivo;
import com.upc.healthycookingd.service.ObjetivoService;
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
public class ObjetivoController {
    @Autowired
    public ObjetivoService objetivoService;

    Logger logger = LoggerFactory.getLogger(EventoController.class);
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/objetivo")
    public ResponseEntity<ObjetivoDTO> save(@RequestBody ObjetivoDTO objetivoDTO){
        Objetivo objetivo;
        try {
            logger.debug("creando receta");
            objetivo = convertToEntity(objetivoDTO);
            objetivoDTO = convertToDto(objetivoService.save(objetivo));
        }catch (Exception e){
            logger.error("Error de creacion", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);

        }
        return new ResponseEntity<ObjetivoDTO>(objetivoDTO,HttpStatus.OK);
    }

   @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/objetivos")
    public ResponseEntity<List<ObjetivoDTO>>  list(){
        List<ObjetivoDTO> listDTO;
        listDTO = convertToListDto(objetivoService.list());
        return new ResponseEntity<List<ObjetivoDTO>>(listDTO, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/objetivo")
    @Transactional
    public ResponseEntity<ObjetivoDTO> update(@RequestBody ObjetivoDTO objetivoDetalle) {
        ObjetivoDTO objetivoDTO;
        Objetivo objetivo;
        try {
            objetivo = convertToEntity(objetivoDetalle);
            logger.debug("Actualizando Producto");
            objetivo = objetivoService.update(objetivo);
            logger.debug("Producto actualizado");
            objetivoDTO = convertToDto(objetivo);
            return new ResponseEntity<ObjetivoDTO>(objetivoDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error de actualización", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");

        }

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/objetivos/{prefijo}/")
    public ResponseEntity<List<ObjetivoDTO>>  obtenerObjetivosDesc(@PathVariable(value = "prefijo") String prefijo){
        List<Objetivo> list = objetivoService.obtenerMetodoPorDescripcion(prefijo);
        List<ObjetivoDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<ObjetivoDTO>>(listDto,HttpStatus.OK);
    }


        private List<ObjetivoDTO> convertToListDto(List<Objetivo> list) {
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ObjetivoDTO convertToDto(Objetivo objetivo) {

        ModelMapper modelMapper =  new ModelMapper();
        ObjetivoDTO a = modelMapper.map(objetivo, ObjetivoDTO.class);
        return a;
    }


    private Objetivo convertToEntity(ObjetivoDTO objetivoDTO) {
        ModelMapper modelMapper= new ModelMapper();
        Objetivo a = modelMapper.map(objetivoDTO, Objetivo.class);
        return a;
    }
}
