package com.upc.backendhealthycooking.controller;


import com.upc.backendhealthycooking.dtos.RegistroDTO;
import com.upc.backendhealthycooking.entities.Registro;
import com.upc.backendhealthycooking.services.RegistroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RegistroController {

    @Autowired
    private RegistroService registroService;
    @PostMapping("/registro")
    public ResponseEntity<RegistroDTO> save(@RequestBody RegistroDTO registroDTO){
        Registro a;
        a = convertToEntity(registroDTO);
        registroDTO = convertToDto(registroService.save(a));
        return new ResponseEntity<RegistroDTO>(registroDTO, HttpStatus.OK);
    }

    @GetMapping("/registros")
    public ResponseEntity<List<RegistroDTO>> list(){
        List<RegistroDTO> listDTO;
        listDTO = convertToListDto(registroService.list());
        return new ResponseEntity<List<RegistroDTO>>(listDTO,HttpStatus.OK);
    }
    @PutMapping ("/registro")
    public ResponseEntity<RegistroDTO> update(@RequestBody RegistroDTO registroDTO) throws Exception {
        Registro a;
        a = convertToEntity(registroDTO);
        registroDTO = convertToDto(registroService.update(a));
        return new ResponseEntity<RegistroDTO>(registroDTO, HttpStatus.OK);
    }
    @DeleteMapping("/registro/{id}")
    public ResponseEntity<RegistroDTO> delete(@PathVariable(value = "id")Long id) throws Exception {
        Registro deleteRegistro = registroService.delete(id);
        return new ResponseEntity<RegistroDTO>(convertToDto(deleteRegistro), HttpStatus.OK);
    }
    @GetMapping("/registros/{prefijo}")
    public ResponseEntity<List<RegistroDTO>> listFirstname(@PathVariable(value = "prefijo")String prefijo){
        List<Registro> registroBuscar = registroService.listFirstName(prefijo);
        return new ResponseEntity<List<RegistroDTO>>(convertToListDto(registroBuscar),HttpStatus.OK);
    }


    private List<RegistroDTO> convertToListDto(List<Registro>list){
        return list.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    private RegistroDTO convertToDto(Registro registro){
        ModelMapper modelMapper = new ModelMapper();
        RegistroDTO a = modelMapper.map(registro, RegistroDTO.class);
        return a;
    }

    private Registro convertToEntity(RegistroDTO registroDTO){
        ModelMapper modelMapper = new ModelMapper();
        Registro a = modelMapper.map(registroDTO, Registro.class);
        return a;


    }


}
