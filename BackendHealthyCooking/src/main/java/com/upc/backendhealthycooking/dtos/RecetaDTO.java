package com.upc.backendhealthycooking.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetaDTO {

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
