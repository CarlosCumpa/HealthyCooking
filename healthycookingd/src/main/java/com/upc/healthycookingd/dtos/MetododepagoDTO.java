package com.upc.healthycookingd.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetododepagoDTO {
    private Integer id;
    private String tipotarjeta;
    private String numtarejta;
    private String fechaven;
    private Integer cvv;
}
