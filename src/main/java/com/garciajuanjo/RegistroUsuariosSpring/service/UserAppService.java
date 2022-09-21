package com.garciajuanjo.RegistroUsuariosSpring.service;

import com.garciajuanjo.RegistroUsuariosSpring.entity.UserApp;
import com.garciajuanjo.RegistroUsuariosSpring.model.UserAppModel;

public interface UserAppService {

    UserAppModel addUserApp(UserAppModel userAppModel);

    UserApp findByUsername(String username);
}
