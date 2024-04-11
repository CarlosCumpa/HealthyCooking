package com.upc.healthycookingb.dtos;

import com.upc.healthycookingb.entities.Perfilusuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoplaneDTO {

    private Integer id;
    private String tipodeplan;
    private Set<Perfilusuario> perfilusuarios;

}
