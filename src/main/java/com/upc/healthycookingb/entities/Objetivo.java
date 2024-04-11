package com.upc.healthycookingb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "objetivos")
public class Objetivo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "descripcion", nullable = false, length = 150)
    private String descripcion;

    @Column(name = "periodo", nullable = false, length = 100)
    private String periodo;

    @OneToMany(mappedBy = "objetivos")
    private Set<Perfilusuario> perfilusuarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "objetivos")
    private Set<Receta> recetas = new LinkedHashSet<>();

}