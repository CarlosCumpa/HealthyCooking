package com.upc.healthycookingb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "comentarios", nullable = false, length = 300)
    private String comentarios;

    @Column(name = "valoracion", nullable = false)
    private Integer valoracion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "perfilusuario_id", nullable = false)
    private Perfilusuario perfilusuario;

}