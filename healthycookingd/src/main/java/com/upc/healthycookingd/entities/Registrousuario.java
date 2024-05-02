package com.upc.healthycookingd.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registrousuario")
public class Registrousuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 40)
    private String apellido;

    @Column(name = "fechanac", nullable = false)
    private LocalDate fechanac;

    @Column(name = "dni", nullable = false)
    private Integer dni;

    @Column(name = "telefono", nullable = false)
    private Integer telefono;


}