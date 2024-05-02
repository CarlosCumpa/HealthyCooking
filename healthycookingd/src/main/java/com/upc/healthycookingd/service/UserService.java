package com.upc.healthycookingd.service;

import com.upc.healthycookingd.Services.IUserService;
import com.upc.healthycookingd.entities.Role;
import com.upc.healthycookingd.entities.User;
import com.upc.healthycookingd.repository.RoleRepository;
import com.upc.healthycookingd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository uR;
    @Autowired
    private RoleRepository rR;

    @Transactional
    @Override
    public Integer insert(User user) {
        int rpta = uR.buscarUsername(user.getUsername());
        if (rpta == 0) {
            uR.save(user);
        }
        return rpta;
    }
    public Integer buscarUser(String username){
        int rpta = uR.buscarUsername(username);
        return rpta;
    }
    @Transactional
    @Override
    public void insertUser(User user) {
        uR.save(user);
    }

    @Override
    public List<User> list() {
        // TODO Auto-generated method stub
        return uR.findAll();
    }

    /**
     * @param user_id De un usuario existente
     * @param rol_id  De un usuario existente
     * @return 1 exito
     */
    @Override
    public Integer insertUserRol(Long user_id, Long rol_id) {
        Integer result = 0;
        uR.insertUserRol(user_id, rol_id);
        return 1;
    }

    @Transactional
    public Integer insertUserRol2(Long user_id, Long rol_id) {
        Integer result = 0;
        User user = uR.findById(user_id).get();
        Role role = rR.findById(rol_id).get();
        user.getRoles().add(role);
        uR.save(user);
        rR.save(role);
        return 1;
    }



}
