package com.upc.healthycookingd.controller;

import com.upc.healthycookingd.dtos.CalificacionesDTO;
import com.upc.healthycookingd.dtos.RecetaDTO;
import com.upc.healthycookingd.entities.Calificaciones;
import com.upc.healthycookingd.entities.Receta;
import com.upc.healthycookingd.service.CalificacionesService;
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
public class CalificacionesController {
    @Autowired
    public CalificacionesService calificacionesService;

    Logger logger = LoggerFactory.getLogger(EventoController.class);
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/calificacion")
    public ResponseEntity<CalificacionesDTO> save(@RequestBody CalificacionesDTO calificacionesDTO){
        Calificaciones calificaciones;
        try {
            logger.debug("creando calificacion");
            calificaciones = convertToEntity(calificacionesDTO);
            calificacionesDTO = convertToDto(calificacionesService.save(calificaciones));
        }catch (Exception e){
            logger.error("Error de creacion", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);

        }
        return new ResponseEntity<CalificacionesDTO>(calificacionesDTO,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/calificaciones")
    public ResponseEntity<List<CalificacionesDTO>>  list(){
        List<CalificacionesDTO> listDTO;
        listDTO = convertToListDto(calificacionesService.list());
        return new ResponseEntity<List<CalificacionesDTO>>(listDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/calificaciones/{id}/recetass")
    public ResponseEntity<List<CalificacionesDTO>>  obtenerCalificaReceta(@PathVariable(value = "id") Integer id){
        List<Calificaciones> list = calificacionesService.obtenerReportePorCalificacion(id);
        List<CalificacionesDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<CalificacionesDTO>>(listDto,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/calificaciones/{id_receta}")
    public ResponseEntity<List<CalificacionesDTO>> list(@PathVariable Integer id_receta){
        List<Calificaciones> list;
        List<CalificacionesDTO> listDto;
        try{
            list = calificacionesService.listcalificaReceta(id_receta);
            listDto = convertToListDto(list);
        }catch (Exception e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo listar");
        }
        return new ResponseEntity<List<CalificacionesDTO>>(listDto,HttpStatus.OK);
    }


    private List<CalificacionesDTO> convertToListDto(List<Calificaciones> list) {
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private CalificacionesDTO convertToDto(Calificaciones calificaciones) {

        ModelMapper modelMapper =  new ModelMapper();
        CalificacionesDTO a = modelMapper.map(calificaciones, CalificacionesDTO.class);
        return a;
    }


    private Calificaciones convertToEntity(CalificacionesDTO calificacionesDTO) {
        ModelMapper modelMapper= new ModelMapper();
        Calificaciones a = modelMapper.map(calificacionesDTO, Calificaciones.class);
        return a;
    }
}
