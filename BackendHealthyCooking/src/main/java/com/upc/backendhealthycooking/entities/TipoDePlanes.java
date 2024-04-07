package com.upc.backendhealthycooking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoDePlanes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPlanes;
    @Column(length = 90,nullable = false)
    private String planPR;
    @Column(length = 90,nullable = false)
    private String planR;

}
