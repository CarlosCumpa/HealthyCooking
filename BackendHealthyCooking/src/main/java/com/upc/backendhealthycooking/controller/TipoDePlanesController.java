package com.upc.backendhealthycooking.controller;


import com.upc.backendhealthycooking.dtos.TipoDePlanesDTO;
import com.upc.backendhealthycooking.entities.TipoDePlanes;
import com.upc.backendhealthycooking.services.TipoDePlanesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TipoDePlanesController {
    @Autowired
    private TipoDePlanesService tipoDePlanesService;
    @PostMapping("/plan")
    public ResponseEntity<TipoDePlanesDTO> save(@RequestBody TipoDePlanesDTO tipoDePlanesDTO){
        TipoDePlanes p;
        p = convertToEntity(tipoDePlanesDTO);
        tipoDePlanesDTO = convertToDto(tipoDePlanesService.save(p));
        return new ResponseEntity<TipoDePlanesDTO>(tipoDePlanesDTO, HttpStatus.OK);
    }

    @GetMapping("/planes")
    public ResponseEntity<List<TipoDePlanesDTO>>  list(){
        List<TipoDePlanesDTO> listDTO;
        listDTO = convertToListDto(tipoDePlanesService.list());
        return new ResponseEntity<List<TipoDePlanesDTO>>(listDTO, HttpStatus.OK);
    }

    @PutMapping("/plan")
    public ResponseEntity<TipoDePlanesDTO> update(@RequestBody TipoDePlanesDTO tipoDePlanesDTO) throws Exception {
        TipoDePlanes p;
        p = convertToEntity(tipoDePlanesDTO);
        tipoDePlanesDTO = convertToDto(tipoDePlanesService.update(p));
        return new ResponseEntity<TipoDePlanesDTO>(tipoDePlanesDTO, HttpStatus.OK);
    }

    @DeleteMapping("/plan/{id}")
    public ResponseEntity<TipoDePlanesDTO> delete(@PathVariable(value = "id") Long id) throws Exception{
        TipoDePlanes deletePlan = tipoDePlanesService.delete(id);
        return new ResponseEntity<TipoDePlanesDTO>(convertToDto(deletePlan), HttpStatus.OK);
    }

    @GetMapping("/planes/{idp}")
    public ResponseEntity<List<TipoDePlanesDTO>> listFirstId(@PathVariable(value = "idp") Long idPlanes){
        List<TipoDePlanes> planesBuscar = tipoDePlanesService.listFirstId(idPlanes);
        return new ResponseEntity<List<TipoDePlanesDTO>>(convertToListDto(planesBuscar), HttpStatus.OK);
    }
    private List<TipoDePlanesDTO> convertToListDto(List<TipoDePlanes> list) {
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private TipoDePlanesDTO convertToDto(TipoDePlanes tipoDePlanes) {

        ModelMapper modelMapper =  new ModelMapper();
        TipoDePlanesDTO p = modelMapper.map(tipoDePlanes, TipoDePlanesDTO.class);
        return p;
    }

    private TipoDePlanes convertToEntity(TipoDePlanesDTO tipoDePlanesDTO) {
        ModelMapper modelMapper= new ModelMapper();
        TipoDePlanes p = modelMapper.map(tipoDePlanesDTO, TipoDePlanes.class);
        return p;
    }


}
