package com.upc.healthycookingd.repository;

import com.upc.healthycookingd.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
