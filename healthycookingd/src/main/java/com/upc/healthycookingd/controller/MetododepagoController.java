package com.upc.healthycookingd.controller;

import com.upc.healthycookingd.dtos.EventoDTO;
import com.upc.healthycookingd.dtos.MetododepagoDTO;
import com.upc.healthycookingd.dtos.RecetaDTO;
import com.upc.healthycookingd.dtos.SuscripcioneDTO;
import com.upc.healthycookingd.entities.Evento;
import com.upc.healthycookingd.entities.Metododepago;
import com.upc.healthycookingd.entities.Receta;
import com.upc.healthycookingd.entities.Suscripcione;
import com.upc.healthycookingd.service.MetododepagoService;
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
public class MetododepagoController {
    @Autowired
    public MetododepagoService metododepagoService;

    Logger logger = LoggerFactory.getLogger(EventoController.class);
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/metodopago")
    public ResponseEntity<MetododepagoDTO> save(@RequestBody MetododepagoDTO metododepagoDTO){
        Metododepago metododepago;
        try {
            logger.debug("creando metodo de pago");
            metododepago = convertToEntity(metododepagoDTO);
            metododepagoDTO = convertToDto(metododepagoService.save(metododepago));
        }catch (Exception e){
            logger.error("Error de creacion", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);

        }
        return new ResponseEntity<MetododepagoDTO>(metododepagoDTO,HttpStatus.OK);
    }

    @GetMapping("/metodopagos")
    public ResponseEntity<List<MetododepagoDTO>>  list(){
        List<MetododepagoDTO> listDTO;
        listDTO = convertToListDto(metododepagoService.list());
        return new ResponseEntity<List<MetododepagoDTO>>(listDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('MODIFIER')")
    @PutMapping("/metodopago")
    @Transactional
    public ResponseEntity<MetododepagoDTO> update(@RequestBody MetododepagoDTO metodopagoDetalle) {
        MetododepagoDTO metododepagoDTO;
        Metododepago metododepago;
        try {
            metododepago = convertToEntity(metodopagoDetalle);
            logger.debug("Actualizando Producto");
            metododepago =metododepagoService.update(metododepago);
            logger.debug("Producto actualizado");
            metododepagoDTO =convertToDto(metododepago);
            return new ResponseEntity<MetododepagoDTO>(metododepagoDTO,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error de actualización",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");

        }

    }

    @GetMapping("/metodopagos/{id}")
    public ResponseEntity<List<MetododepagoDTO>> ObtenerMetodospago(@PathVariable(value = "id") Integer id){
        List<Metododepago> list = metododepagoService.obtenerMetodoPorid(id);
        List<MetododepagoDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<MetododepagoDTO>>(listDto,HttpStatus.OK);
    }

   /* @DeleteMapping("/metodopago/{id}")
    @Transactional
    public ResponseEntity<MetododepagoDTO> delete(@PathVariable(value = "id") Integer id) {
        Metododepago metododepago;
        MetododepagoDTO metododepagoDTO;
        try {
            metododepago = metododepagoService.delete(id);
            logger.debug("Elimiando objeto");
            metododepagoDTO = convertToDto(metododepago);
            return new ResponseEntity<MetododepagoDTO>(metododepagoDTO,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error de eliminacion",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se pudo eliminar sorry");

        }
    }*/

    private List<MetododepagoDTO> convertToListDto(List<Metododepago> list) {
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private MetododepagoDTO convertToDto(Metododepago metododepago) {

        ModelMapper modelMapper =  new ModelMapper();
        MetododepagoDTO a = modelMapper.map(metododepago, MetododepagoDTO.class);
        return a;
    }


    private Metododepago convertToEntity(MetododepagoDTO metododepagoDTO) {
        ModelMapper modelMapper= new ModelMapper();
        Metododepago a = modelMapper.map(metododepagoDTO, Metododepago.class);
        return a;
    }
}
