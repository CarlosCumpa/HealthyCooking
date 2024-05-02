package com.upc.healthycookingd.controller;

import com.upc.healthycookingd.dtos.RegistrousuarioDTO;
import com.upc.healthycookingd.dtos.SuscripcioneDTO;
import com.upc.healthycookingd.entities.Registrousuario;
import com.upc.healthycookingd.entities.Suscripcione;
import com.upc.healthycookingd.service.SuscripcioneService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SuscripcioneController {
    @Autowired
    public SuscripcioneService suscripcioneService;

    Logger logger = LoggerFactory.getLogger(EventoController.class);
    /*@Transactional
    @PostMapping("/suscripcion")
    public ResponseEntity<SuscripcioneDTO> save(@RequestBody SuscripcioneDTO suscripcioneDTO){
        Suscripcione suscripcione;
        try {
            logger.debug("creando suscripcion");
            suscripcione = convertToEntity(suscripcioneDTO);
            suscripcioneDTO = convertToDto(suscripcioneService.save(suscripcione));
        }catch (Exception e){
            logger.error("Error de creacion", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);

        }
        return new ResponseEntity<SuscripcioneDTO>(suscripcioneDTO,HttpStatus.OK);
    }*/

    @GetMapping("/suscripciones")
    public ResponseEntity<List<SuscripcioneDTO>>  list(){
        List<SuscripcioneDTO> listDTO;
        listDTO = convertToListDto(suscripcioneService.list());
        return new ResponseEntity<List<SuscripcioneDTO>>(listDTO, HttpStatus.OK);
    }

    @PutMapping("/suscripcion")
    @Transactional
    public ResponseEntity<SuscripcioneDTO> update(@RequestBody SuscripcioneDTO suscripcionDetalle) {
        SuscripcioneDTO suscripcioneDTO;
        Suscripcione suscripcione;
        try {
            suscripcione = convertToEntity(suscripcionDetalle);
            logger.debug("Actualizando Producto");
            suscripcione =suscripcioneService.update(suscripcione);
            logger.debug("Producto actualizado");
            suscripcioneDTO =convertToDto(suscripcione);
            return new ResponseEntity<SuscripcioneDTO>(suscripcioneDTO,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error de actualización",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");

        }

    }

    @GetMapping("/suscripciones/{prefijo}/")
    public ResponseEntity<List<SuscripcioneDTO>>  obtenerSuscripType(@PathVariable(value = "prefijo") String prefijo){
        List<Suscripcione> list = suscripcioneService.ObtenerSuscripcionTipo(prefijo);
        List<SuscripcioneDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<SuscripcioneDTO>>(listDto,HttpStatus.OK);
    }

   /* @DeleteMapping("/suscripcion/{id}")
    @Transactional
    public ResponseEntity<SuscripcioneDTO> delete(@PathVariable(value = "id") Integer id) {
        Suscripcione suscripcione;
        SuscripcioneDTO suscripcioneDTO;
        try {
            suscripcione = suscripcioneService.delete(id);
            logger.debug("Elimiando objeto");
            suscripcioneDTO = convertToDto(suscripcione);
            return new ResponseEntity<SuscripcioneDTO>(suscripcioneDTO,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error de eliminacion",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se pudo eliminar sorry");

        }
    }*/

    private List<SuscripcioneDTO> convertToListDto(List<Suscripcione> list) {
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private SuscripcioneDTO convertToDto(Suscripcione suscripcione) {

        ModelMapper modelMapper =  new ModelMapper();
        SuscripcioneDTO a = modelMapper.map(suscripcione, SuscripcioneDTO.class);
        return a;
    }


    private Suscripcione convertToEntity(SuscripcioneDTO suscripcioneDTO) {
        ModelMapper modelMapper= new ModelMapper();
        Suscripcione a = modelMapper.map(suscripcioneDTO, Suscripcione.class);
        return a;
    }
}
