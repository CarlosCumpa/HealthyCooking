package com.upc.healthycookingd.dtos;

import com.upc.healthycookingd.entities.Metododepago;
import com.upc.healthycookingd.entities.Objetivo;
import com.upc.healthycookingd.entities.Registrousuario;
import com.upc.healthycookingd.entities.Suscripcione;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilusuarioDTO {
    private Integer id;
    private Registrousuario registrousuario;
    private Suscripcione suscripciones;
    private Metododepago metododepago;
    private Objetivo objetivos;
}
