package com.upc.healthycookingd.Services;

import com.upc.healthycookingd.entities.Role;

import java.util.List;

public interface IRoleService {
    public Role insert(Role role);

    List<Role> list();
}
