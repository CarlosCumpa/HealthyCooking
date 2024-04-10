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
@Table(name = "metododepago")
public class Metododepago {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "numtarjeta", nullable = false)
    private Integer numtarjeta;

    @Column(name = "fechaven", nullable = false)
    private LocalDate fechaven;

    @Column(name = "cvv", nullable = false)
    private Integer cvv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pago_id", nullable = false)
    private Pago pago;

    @OneToMany(mappedBy = "metododepago")
    private Set<Perfilusuario> perfilusuarios = new LinkedHashSet<>();

}