package com.upc.backendhealthycooking.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 80,nullable = false)
    private String nameregistro;
    @Column(length = 80,nullable = false)
    private String apellido;

    private LocalDate fechanacimiento;
    @Column(length = 8, nullable = false)
    private String dni;
    @Column(length = 20,nullable = false)
    private String telefono;
}
