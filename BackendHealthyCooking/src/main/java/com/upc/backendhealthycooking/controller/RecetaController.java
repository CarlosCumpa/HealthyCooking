package com.upc.backendhealthycooking.controller;

import com.upc.backendhealthycooking.dtos.RecetaDTO;
import com.upc.backendhealthycooking.entities.Receta;
import com.upc.backendhealthycooking.services.RecetaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @PostMapping("/receta")
    public ResponseEntity<RecetaDTO> save(@RequestBody RecetaDTO recetaDTO){
        Receta a;
        a = convertToEntity(recetaDTO);
        recetaDTO = convertToDto(recetaService.save(a));
        return new ResponseEntity<RecetaDTO>(recetaDTO, HttpStatus.OK);
    }

    @GetMapping("/recetas")
    public ResponseEntity<List<RecetaDTO>> list(){
        List<RecetaDTO> listDTO;
        listDTO = convertToListDto(recetaService.list());
        return new ResponseEntity<List<RecetaDTO>>(listDTO, HttpStatus.OK);
    }

    @PutMapping("/receta")
    public ResponseEntity<RecetaDTO> update(@RequestBody RecetaDTO recetaDTO) throws Exception{
        Receta a;
        a = convertToEntity(recetaDTO);
        recetaDTO = convertToDto(recetaService.update(a));
        return new ResponseEntity<RecetaDTO>(recetaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/receta/{id}")
    public ResponseEntity<RecetaDTO> delete(@PathVariable(value = "id")Long id) throws Exception{
        Receta deleteReceta = recetaService.delete(id);
        return new ResponseEntity<RecetaDTO>(convertToDto(deleteReceta), HttpStatus.OK);
    }

    @GetMapping("/recetas/{prefijo}")
    public ResponseEntity<List<RecetaDTO>> listFirstname(@PathVariable(value = "prefijo")String prefijo) {
        List<Receta> recetaBuscar = recetaService.listFirstName(prefijo);
        return new ResponseEntity<List<RecetaDTO>>(convertToListDto(recetaBuscar), HttpStatus.OK);
    }
        private List<RecetaDTO>convertToListDto(List<Receta>list){
            return list.stream().map(this::convertToDto).collect(Collectors.toList());
        }

        private RecetaDTO convertToDto(Receta receta){
            ModelMapper modelMapper = new ModelMapper();
            RecetaDTO a = modelMapper.map(receta, RecetaDTO.class);
            return a;
        }

        private Receta convertToEntity(RecetaDTO recetaDTO){
            ModelMapper modelMapper = new ModelMapper();
            Receta a = modelMapper.map(recetaDTO, Receta.class);
            return a;
        }

}
