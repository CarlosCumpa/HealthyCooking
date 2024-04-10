package com.upc.healthycookingb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombreevento", nullable = false, length = 100)
    private String nombreevento;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Column(name = "ubicacion", nullable = false, length = 100)
    private String ubicacion;

    @Column(name = "descripcion", nullable = false, length = 250)
    private String descripcion;

}