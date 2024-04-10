package com.upc.healthycookingb.controller;

import com.upc.healthycookingb.dtos.EventoDTO;
import com.upc.healthycookingb.entities.Evento;
import com.upc.healthycookingb.services.EventoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EventoController {
    @Autowired
    private EventoService eventoService;
    @PostMapping("/evento")
    public ResponseEntity<EventoDTO> save(@RequestBody EventoDTO eventoDTO){
        Evento a;
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

    @PutMapping("/evento")
    public ResponseEntity<EventoDTO> update(@RequestBody EventoDTO eventoDTO) throws Exception {
        Evento a;
        a = convertToEntity(eventoDTO);
        eventoDTO = convertToDto(eventoService.update(a));
        return new ResponseEntity<EventoDTO>(eventoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/evento/{id}")
    public ResponseEntity<EventoDTO> delete(@PathVariable(value = "id") Integer id) throws Exception{
        Evento deleteEvento = eventoService.delete(id);
        return new ResponseEntity<EventoDTO>(convertToDto(deleteEvento), HttpStatus.OK);
    }

    @GetMapping("/eventos/{prefijo}")
    public ResponseEntity<List<EventoDTO>> listFirstname(@PathVariable(value = "prefijo") String prefijo){
        List<Evento> eventosBuscar = eventoService.listFirstEvent(prefijo);
        return new ResponseEntity<List<EventoDTO>>(convertToListDto(eventosBuscar), HttpStatus.OK);
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
