package com.garciajuanjo.RegistroUsuariosSpring.configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

        System.out.println(bCryptPasswordEncoder.encode("admin"));
    }
}
