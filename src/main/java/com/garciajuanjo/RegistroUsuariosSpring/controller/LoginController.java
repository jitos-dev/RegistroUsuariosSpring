package com.garciajuanjo.RegistroUsuariosSpring.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import static com.garciajuanjo.RegistroUsuariosSpring.constant.ViewsConstant.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Log LOG = LogFactory.getLog(LoginController.class);

    @GetMapping("/loginsuccess")
    public String loginCheck() {
        LOG.info("VIEW: loginsuccess");

        return INDEX_VIEW;
    }
}
