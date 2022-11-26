package com.garciajuanjo.RegistroUsuariosSpring.repository;

import com.garciajuanjo.RegistroUsuariosSpring.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Roles findByName(String name);
}
