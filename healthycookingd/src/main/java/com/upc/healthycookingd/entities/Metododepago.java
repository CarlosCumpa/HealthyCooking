package com.upc.healthycookingd.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "metododepago")
public class Metododepago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipotarjeta", nullable = false, length = 100)
    private String tipotarjeta;

    @Column(name = "numtarejta", nullable = false)
    private String numtarejta;

    @Column(name = "fechaven", nullable = false, length = 10)
    private String fechaven;

    @Column(name = "cvv", nullable = false)
    private Integer cvv;

}