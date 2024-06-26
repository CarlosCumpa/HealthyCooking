package com.upc.healthycookingd.controller;
import com.upc.healthycookingd.dtos.EventoDTO;
import com.upc.healthycookingd.entities.Evento;
import com.upc.healthycookingd.service.EventoService;
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
public class EventoController {
    @Autowired
    public EventoService eventoService;
    Logger logger = LoggerFactory.getLogger(EventoController.class);
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/evento")
    public ResponseEntity<EventoDTO> save(@RequestBody EventoDTO eventoDTO){
        Evento evento;
        try {
            logger.debug("creando evento");
            evento = convertToEntity(eventoDTO);
            eventoDTO = convertToDto(eventoService.save(evento));
        }catch (Exception e){
            logger.error("Error de creacion", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);

        }
        return new ResponseEntity<EventoDTO>(eventoDTO,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/eventos")
    public ResponseEntity<List<EventoDTO>>  list(){
        List<EventoDTO> listDTO;
        listDTO = convertToListDto(eventoService.list());
        return new ResponseEntity<List<EventoDTO>>(listDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/evento")
    @Transactional
    public ResponseEntity<EventoDTO> update(@RequestBody EventoDTO eventoDetalle) {
        EventoDTO eventoDTO;
        Evento evento;
        try {
            evento = convertToEntity(eventoDetalle);
            logger.debug("Actualizando Producto");
            evento =eventoService.update(evento);
            logger.debug("Producto actualizado");
            eventoDTO =convertToDto(evento);
            return new ResponseEntity<EventoDTO>(eventoDTO,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error de actualización",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");

        }

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/evento/{id}")
    @Transactional
    public ResponseEntity<EventoDTO> delete(@PathVariable(value = "id") Integer id) {
        Evento evento;
        EventoDTO eventoDTO;
        try {
            evento = eventoService.delete(id);
            logger.debug("Elimiando objeto");
            eventoDTO = convertToDto(evento);
            return new ResponseEntity<EventoDTO>(eventoDTO,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error de eliminacion",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se pudo eliminar sorry");

        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/eventos/{id}")
    @Transactional
    public ResponseEntity<EventoDTO> buscar(@PathVariable(value = "id") Integer id){
        Evento evento;
        EventoDTO eventoDTO;
        try {
            logger.debug("Buscando entidad");
            evento = eventoService.search(id);
            eventoDTO = convertToDto(evento);
        }catch (Exception e ){
            logger.error("Error de Obtener Entidad");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mi mensaje");
        }
        return new ResponseEntity<EventoDTO>(eventoDTO, HttpStatus.OK);
    }

    private List<EventoDTO> convertToListDto(List<Evento> list) {
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private EventoDTO convertToDto(Evento eventos) {

        ModelMapper modelMapper =  new ModelMapper();
        EventoDTO a = modelMapper.map(eventos, EventoDTO.class);
        return a;
    }


    private Evento convertToEntity(EventoDTO eventoDTO) {
        ModelMapper modelMapper= new ModelMapper();
        Evento a = modelMapper.map(eventoDTO, Evento.class);
        return a;
    }
}
