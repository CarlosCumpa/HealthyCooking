package com.upc.healthycookingb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tipopago", nullable = false, length = 90)
    private String tipopago;

    @OneToMany(mappedBy = "pago")
    private Set<Metododepago> metododepagos = new LinkedHashSet<>();

}