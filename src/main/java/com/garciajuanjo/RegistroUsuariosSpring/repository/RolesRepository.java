package com.garciajuanjo.RegistroUsuariosSpring.repository;

import com.garciajuanjo.RegistroUsuariosSpring.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
