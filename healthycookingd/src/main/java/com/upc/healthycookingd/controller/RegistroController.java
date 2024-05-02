package com.upc.healthycookingd.controller;
import com.upc.healthycookingd.dtos.ObjetivoDTO;
import com.upc.healthycookingd.dtos.RegistrousuarioDTO;
import com.upc.healthycookingd.entities.Objetivo;
import com.upc.healthycookingd.entities.Registrousuario;
import com.upc.healthycookingd.service.RegistroService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RegistroController {
    @Autowired
    private RegistroService registroService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/registro")
    public ResponseEntity<RegistrousuarioDTO> save(@RequestBody RegistrousuarioDTO registroDTO){
        Registrousuario a;
        a = convertToEntity(registroDTO);
        registroDTO = convertToDto(registroService.save(a));
        return new ResponseEntity<RegistrousuarioDTO>(registroDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('VISUALIZER')")
    @GetMapping("/registros")
    public ResponseEntity<List<RegistrousuarioDTO>> list(){
        List<RegistrousuarioDTO> listDTO;
        listDTO = convertToListDto(registroService.list());
        return new ResponseEntity<List<RegistrousuarioDTO>>(listDTO,HttpStatus.OK);
    }
    @PutMapping ("/registro")
    @Transactional
    public ResponseEntity<RegistrousuarioDTO> update(@RequestBody RegistrousuarioDTO registroDTO) throws Exception {
        Registrousuario a;
        a = convertToEntity(registroDTO);
        registroDTO = convertToDto(registroService.update(a));
        return new ResponseEntity<RegistrousuarioDTO>(registroDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('VISUALIZER')")
    @GetMapping("/registros/{prefijo}/")
    public ResponseEntity<List<RegistrousuarioDTO>>  obtenerRegistrosNombre(@PathVariable(value = "prefijo") String prefijo){
        List<Registrousuario> list = registroService.ObtenerRegistroNombre(prefijo);
        List<RegistrousuarioDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<RegistrousuarioDTO>>(listDto,HttpStatus.OK);
    }

    /*@DeleteMapping("/registro/{id}")
    @Transactional
    public ResponseEntity<RegistrousuarioDTO> delete(@PathVariable(value = "id")Integer id) throws Exception {
        Registrousuario deleteRegistro = registroService.delete(id);
        return new ResponseEntity<RegistrousuarioDTO>(convertToDto(deleteRegistro), HttpStatus.OK);
    }*/

    private List<RegistrousuarioDTO> convertToListDto(List<Registrousuario>list){
        return list.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    private RegistrousuarioDTO convertToDto(Registrousuario registro){
        ModelMapper modelMapper = new ModelMapper();
        RegistrousuarioDTO a = modelMapper.map(registro, RegistrousuarioDTO.class);
        return a;
    }

    private Registrousuario convertToEntity(RegistrousuarioDTO registroDTO){
        ModelMapper modelMapper = new ModelMapper();
        Registrousuario a = modelMapper.map(registroDTO, Registrousuario.class);
        return a;


    }

}