package com.upc.healthycookingd.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "perfilusuario")
public class Perfilusuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registrousuario_id")
    private Registrousuario registrousuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "suscripciones_id")
    private Suscripcione suscripciones;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metododepago_id")
    private Metododepago metododepago;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "objetivos_id")
    private Objetivo objetivos;

}