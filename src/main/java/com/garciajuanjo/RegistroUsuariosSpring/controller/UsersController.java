package com.garciajuanjo.RegistroUsuariosSpring.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.garciajuanjo.RegistroUsuariosSpring.constant.ViewsConstant.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final Log LOGGER = LogFactory.getLog(UsersController.class);

    @GetMapping("/panel")
    public String showUsersPanel(){
        LOGGER.info("VIEW: users.html");

        return USERS_PANEL_VIEW;
    }
}
