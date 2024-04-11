package com.upc.healthycookingb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recetas")
public class Receta {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ingredientes", nullable = false, length = 250)
    private String ingredientes;

    @Column(name = "preparacion", nullable = false, length = 250)
    private String preparacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "objetivos_id", nullable = false)
    private Objetivo objetivos;

}