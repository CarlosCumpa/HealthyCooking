package com.upc.healthycookingd.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuscripcioneDTO {
    private Integer id;
    private String tiposuscripcion;
}
