package com.garciajuanjo.RegistroUsuariosSpring.repository;

import com.garciajuanjo.RegistroUsuariosSpring.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userAppRepository")
public interface UserAppRepository extends JpaRepository<UserApp, Integer> {

    /*La clase JpaRepository podemos ir haciendo estos métodos y los reconoce sin tener que implementarlos.*/
    UserApp findByUsername(String username);
}
