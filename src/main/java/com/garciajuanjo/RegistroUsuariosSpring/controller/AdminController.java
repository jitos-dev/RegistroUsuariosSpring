package com.garciajuanjo.RegistroUsuariosSpring.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.garciajuanjo.RegistroUsuariosSpring.constant.ViewsConstant.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_ADMIN')") //para que solo entre en el controlador esos roles
public class AdminController {

    private final Log LOGGER = LogFactory.getLog(AdminController.class);

    @GetMapping({"/panel", ""})
    public String showAdminPanel(){
        LOGGER.info("VIEW: admin.html");

        return ADMIN_PANEL_VIEW;
    }
}
