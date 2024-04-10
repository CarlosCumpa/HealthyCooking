package com.upc.healthycookingb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "perfilusuario")
public class Perfilusuario {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "metododepago_id", nullable = false)
    private Metododepago metododepago;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "objetivos_id", nullable = false)
    private Objetivo objetivos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "registrousuario_id", nullable = false)
    private Registrousuario registrousuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipoplanes_id", nullable = false)
    private Tipoplane tipoplanes;

    @OneToMany(mappedBy = "perfilusuario")
    private Set<Review> reviews = new LinkedHashSet<>();

}