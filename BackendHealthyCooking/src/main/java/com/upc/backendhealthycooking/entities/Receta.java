package com.upc.backendhealthycooking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 120,nullable = false)
    private String ingrediente;
    @Column(length = 120,nullable = false)
    private String preparacion;
    @Column(length = 120,nullable = false)
    private String objetivos;

}
