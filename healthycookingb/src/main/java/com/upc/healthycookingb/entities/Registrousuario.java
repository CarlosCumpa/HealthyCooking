package com.upc.healthycookingb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "registrousuario")
public class Registrousuario {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "fechanac", nullable = false)
    private LocalDate fechanac;

    @Column(name = "dni", nullable = false)
    private Integer dni;

    @Column(name = "telefono", nullable = false)
    private Integer telefono;

    @OneToMany(mappedBy = "registrousuario")
    private Set<Perfilusuario> perfilusuarios = new LinkedHashSet<>();

}