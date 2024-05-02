package com.upc.healthycookingd.service;

import com.upc.healthycookingd.Services.IRoleService;
import com.upc.healthycookingd.entities.Role;
import com.upc.healthycookingd.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository rR;

    @Transactional
    @Override
    public Role insert(Role role) {
        return rR.save(role);
    }

    @Override
    public List<Role> list() {
        // TODO Auto-generated method stub
        return rR.findAll();
    }
}
