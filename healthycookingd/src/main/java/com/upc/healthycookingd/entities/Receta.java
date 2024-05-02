package com.upc.healthycookingd.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recetas")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descripcionreceta", nullable = false, length = 100)
    private String descripcionreceta;

    @ManyToOne
    @JoinColumn(name = "objetivo_id")
    private Objetivo objetivo;
}