package com.upc.backendhealthycooking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Eventos {
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
