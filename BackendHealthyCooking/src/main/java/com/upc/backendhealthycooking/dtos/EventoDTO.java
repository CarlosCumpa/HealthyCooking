package com.upc.backendhealthycooking.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 80,nullable = false)
    private String nombreEvento;
    private LocalDate fecha;
    private LocalTime hora;
    @Column(length = 100, nullable = false)
    private String ubicacion;
    @Column(length = 120,nullable = false)
    private String descripcion;

}
