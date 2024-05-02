package com.upc.healthycookingd.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrousuarioDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDate fechanac;
    private Integer dni;
    private Integer telefono;
}
