package com.upc.healthycookingb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipoplanes")
public class Tipoplane {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tipodeplan", nullable = false, length = 100)
    private String tipodeplan;

    @OneToMany(mappedBy = "tipoplanes")
    private Set<Perfilusuario> perfilusuarios = new LinkedHashSet<>();

}