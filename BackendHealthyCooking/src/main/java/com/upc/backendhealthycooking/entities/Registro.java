package com.upc.backendhealthycooking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Registro {
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
