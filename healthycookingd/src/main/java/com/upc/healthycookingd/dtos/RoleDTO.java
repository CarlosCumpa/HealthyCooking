package com.upc.healthycookingd.dtos;

import com.upc.healthycookingd.entities.User;
import lombok.Data;

@Data
public class RoleDTO {

    private Long id;

    private String rol;

    private User user;

}
