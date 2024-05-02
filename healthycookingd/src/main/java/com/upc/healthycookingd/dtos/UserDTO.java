package com.upc.healthycookingd.dtos;

import com.upc.healthycookingd.entities.Role;

import java.util.List;

public class UserDTO {


    private Long id;

    private String username;

    private String password;
    private Boolean enabled;

    private List<Role> roles;

}
