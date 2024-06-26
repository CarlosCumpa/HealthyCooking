package com.upc.healthycookingd.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {

    private Integer id;
    private String nombreevento;
    private LocalDate fecha;
    private LocalTime hora;
    private String ubicacion;
    private String descripcion;
}
