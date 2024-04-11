package com.upc.healthycookingb.controller;

import com.upc.healthycookingb.dtos.EventoDTO;
import com.upc.healthycookingb.entities.Evento;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import com.upc.healthycookingb.dtos.TipoplaneDTO;
import com.upc.healthycookingb.entities.Tipoplane;
import com.upc.healthycookingb.services.TipoplaneService;
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
public class TipoplaneController {
    @Autowired
    public TipoplaneService tipoplaneService;


    Logger logger = LoggerFactory.getLogger(TipoplaneController.class);
    @Transactional
    @PostMapping("/plan")
    public ResponseEntity<TipoplaneDTO> save(@RequestBody TipoplaneDTO tipoplaneDTO){
        Tipoplane tipoplane;
        try {
            logger.debug("creando evento");
            tipoplane = convertToEntity(tipoplaneDTO);
            tipoplaneDTO = convertToDto1(tipoplaneService.save(tipoplane));
        }catch (Exception e){
            logger.error("Error de creacion", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);

        }
        return new ResponseEntity<TipoplaneDTO>(tipoplaneDTO,HttpStatus.OK);
    }

    @GetMapping("/planes")
    public ResponseEntity<List<TipoplaneDTO>>  list(){
        List<TipoplaneDTO> listDTO;
        listDTO = convertToListDto(tipoplaneService.list());
        return new ResponseEntity<List<TipoplaneDTO>>(listDTO, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/plan")
    public ResponseEntity<TipoplaneDTO> update(@RequestBody TipoplaneDTO planDetalle) {
        TipoplaneDTO tipoplaneDTO;
        Tipoplane tipoplane;
        try {
            tipoplane = convertToEntity(planDetalle);
            logger.debug("Actualizando Producto");
            tipoplane = tipoplaneService.update(tipoplane);
            logger.debug("Producto actualizado");
            tipoplaneDTO =convertToDto1(tipoplane);
            return new ResponseEntity<TipoplaneDTO>(tipoplaneDTO,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error de actualización",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");
        }
    }

    @Transactional
    @DeleteMapping("/plan/{id}")
    public ResponseEntity<TipoplaneDTO> delete(@PathVariable(value = "id") Integer id) {
        TipoplaneDTO tipoplaneDTO;
        Tipoplane tipoplane;
        try {
            tipoplane = tipoplaneService.delete(id);
            logger.debug("Elimiando objeto");
            tipoplaneDTO = convertToDto1(tipoplane);
            return new ResponseEntity<TipoplaneDTO>(tipoplaneDTO,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error de eliminacion",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se pudo eliminar sorry");

        }
    }

    @GetMapping("/planes/{prefijo}")
    public ResponseEntity<List<TipoplaneDTO>> listFirstId(@PathVariable(value = "prefijo") String prefijo){
        List<Tipoplane> planesBuscar = tipoplaneService.listFirstPlan(prefijo);
        return new ResponseEntity<List<TipoplaneDTO>>(convertToListDto(planesBuscar), HttpStatus.OK);
    }
    private List<TipoplaneDTO> convertToListDto(List<Tipoplane> list) {
        return list.stream().map(this::convertToDto1).collect(Collectors.toList());
    }

    private TipoplaneDTO convertToDto1(Tipoplane tipoplane) {

        ModelMapper modelMapper =  new ModelMapper();
        TipoplaneDTO a = modelMapper.map(tipoplane, TipoplaneDTO.class);
        return a;
    }


    private Tipoplane convertToEntity(TipoplaneDTO tipoplaneDTO) {
        ModelMapper modelMapper= new ModelMapper();
        Tipoplane a = modelMapper.map(tipoplaneDTO, Tipoplane.class);
        return a;
    }
}
