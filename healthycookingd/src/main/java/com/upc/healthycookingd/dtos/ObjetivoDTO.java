package com.upc.healthycookingd.dtos;

import com.upc.healthycookingd.entities.Receta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjetivoDTO {
    private Integer id;
    private String descripcion;
    private String periodo;

}
