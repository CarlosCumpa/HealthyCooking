package com.upc.healthycookingd.controller;

import com.upc.healthycookingd.dtos.RecetaDTO;
import com.upc.healthycookingd.entities.Receta;
import com.upc.healthycookingd.service.RecetaService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
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
public class RecetaController {
    @Autowired
    public RecetaService recetaService;

    Logger logger = LoggerFactory.getLogger(EventoController.class);
    @Transactional
    @PostMapping("/receta")
    public ResponseEntity<RecetaDTO> save(@RequestBody RecetaDTO recetaDTO){
        Receta receta;
        try {
            logger.debug("creando receta");
            receta = convertToEntity(recetaDTO);
            recetaDTO = convertToDto(recetaService.save(receta));
        }catch (Exception e){
            logger.error("Error de creacion", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo crear, sorry", e);

        }
        return new ResponseEntity<RecetaDTO>(recetaDTO,HttpStatus.OK);
    }

    @PutMapping("/receta")
    @Transactional
    public ResponseEntity<RecetaDTO> update(@RequestBody RecetaDTO recetaDetalle) {
        RecetaDTO recetaDTO;
        Receta receta;
        try {
            receta = convertToEntity(recetaDetalle);
            logger.debug("Actualizando Producto");
            receta =recetaService.update(receta);
            logger.debug("Producto actualizado");
            recetaDTO =convertToDto(receta);
            return new ResponseEntity<RecetaDTO>(recetaDTO,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error de actualización",e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo actualizar, sorry");

        }

    }

    @GetMapping("/recetas")
    public ResponseEntity<List<RecetaDTO>>  list(){
        List<RecetaDTO> listDTO;
        listDTO = convertToListDto(recetaService.list());
        return new ResponseEntity<List<RecetaDTO>>(listDTO, HttpStatus.OK);
    }

    @GetMapping("/recetas/{prefijo}/objetivo")
    public ResponseEntity<List<RecetaDTO>>  obtenerObjetivosReceta(@PathVariable(value = "prefijo") String prefijo){
        List<Receta> list = recetaService.obtenerReportePorDescripcion(prefijo);
        List<RecetaDTO> listDto = convertToListDto(list);
        return new ResponseEntity<List<RecetaDTO>>(listDto,HttpStatus.OK);
    }

    @GetMapping("/recetas/{id_objetivo}")
    public ResponseEntity<List<RecetaDTO>> list(@PathVariable Integer id_objetivo){
        List<Receta> list;
        List<RecetaDTO> listDto;
        try{
            list = recetaService.listRecetaObjetivo(id_objetivo);
            listDto = convertToListDto(list);
        }catch (Exception e){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "No se pudo listar");
        }
        return new ResponseEntity<List<RecetaDTO>>(listDto,HttpStatus.OK);
    }

    private List<RecetaDTO> convertToListDto(List<Receta> list) {
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private RecetaDTO convertToDto(Receta receta) {

        ModelMapper modelMapper =  new ModelMapper();
        RecetaDTO a = modelMapper.map(receta, RecetaDTO.class);
        return a;
    }


    private Receta convertToEntity(RecetaDTO recetaDTO) {
        ModelMapper modelMapper= new ModelMapper();
        Receta a = modelMapper.map(recetaDTO, Receta.class);
        return a;
    }
}
