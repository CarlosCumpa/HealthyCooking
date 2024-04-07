package com.upc.backendhealthycooking.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDePlanesDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPlanes;
    @Column(length = 90,nullable = false)
    private String planPR;
    @Column(length = 90,nullable = false)
    private String planR;
}
