package com.upc.healthycookingd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.upc.healthycookingd.entities.Role;
import com.upc.healthycookingd.Services.IRoleService;


@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private IRoleService rService;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return new ResponseEntity<>(rService.insert(role), HttpStatus.OK);
    }
}
