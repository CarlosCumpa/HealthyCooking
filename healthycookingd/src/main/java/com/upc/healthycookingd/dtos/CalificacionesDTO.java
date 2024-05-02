package com.upc.healthycookingd.dtos;

import com.upc.healthycookingd.entities.Receta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalificacionesDTO {

    private Integer id;
    private Integer calificacion;
    private Receta receta;
}
