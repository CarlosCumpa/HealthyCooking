package com.upc.healthycookingb.controller;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.hibernate.Hibernate;
import com.upc.healthycookingb.dtos.TipoplaneDTO;
import com.upc.healthycookingb.entities.Tipoplane;
import com.upc.healthycookingb.services.TipoplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TipoplaneController {
    @Autowired
    private TipoplaneService tipoplaneService;

    @PostMapping("/plan")
    @Transactional
    public ResponseEntity<TipoplaneDTO> save(@RequestBody TipoplaneDTO tipoDePlanesDTO){
        Tipoplane p;
        p = convertToEntity(tipoDePlanesDTO);
        tipoDePlanesDTO = convertToDto1(tipoplaneService.save(p));
        return new ResponseEntity<TipoplaneDTO>(tipoDePlanesDTO, HttpStatus.OK);
    }

    @GetMapping("/planes")
    public ResponseEntity<List<TipoplaneDTO>>  list(){
        List<TipoplaneDTO> listDTO;
        listDTO = convertToListDto(tipoplaneService.list());
        return new ResponseEntity<List<TipoplaneDTO>>(listDTO, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/plan")
    public ResponseEntity<TipoplaneDTO> update(@RequestBody TipoplaneDTO tipoDePlanesDTO) throws Exception {
        Tipoplane p;
        p = convertToEntity(tipoDePlanesDTO);
        tipoDePlanesDTO = convertToDto1(tipoplaneService.update(p));
        return new ResponseEntity<TipoplaneDTO>(tipoDePlanesDTO, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/plan/{id}")
    public ResponseEntity<TipoplaneDTO> delete(@PathVariable(value = "id") Integer id) throws Exception{
        Tipoplane deletePlan = tipoplaneService.delete(id);
        return new ResponseEntity<>(convertToDto1(deletePlan), HttpStatus.OK);
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
