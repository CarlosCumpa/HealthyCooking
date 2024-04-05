package com.upc.backendhealthycooking.controller;

import com.upc.backendhealthycooking.dtos.EventoDTO;
import com.upc.backendhealthycooking.entities.Eventos;
import com.upc.backendhealthycooking.services.EventoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EventosController {
    @Autowired
    private EventoService eventoService;
    @PostMapping("/evento")
    public ResponseEntity<EventoDTO> save(@RequestBody EventoDTO eventoDTO){
        Eventos a;
        a = convertToEntity(eventoDTO);
        eventoDTO = convertToDto(eventoService.save(a));
        return new ResponseEntity<EventoDTO>(eventoDTO, HttpStatus.OK);
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<EventoDTO>>  list(){
        List<EventoDTO> listDTO;
        listDTO = convertToListDto(eventoService.list());
        return new ResponseEntity<List<EventoDTO>>(listDTO, HttpStatus.OK);
    }

    private List<EventoDTO> convertToListDto(List<Eventos> list) {
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private EventoDTO convertToDto(Eventos eventos) {

        ModelMapper modelMapper =  new ModelMapper();
        EventoDTO a = modelMapper.map(eventos, EventoDTO.class);
        return a;
    }


    private Eventos convertToEntity(EventoDTO eventoDTO) {
        ModelMapper modelMapper= new ModelMapper();
        Eventos a = modelMapper.map(eventoDTO, Eventos.class);
        return a;
    }


}
