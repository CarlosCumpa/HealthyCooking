package com.upc.healthycookingd.dtos;


import com.upc.healthycookingd.entities.Objetivo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaDTO {

    private Integer id;
    private String descripcionreceta;
    private Objetivo objetivo;
}
