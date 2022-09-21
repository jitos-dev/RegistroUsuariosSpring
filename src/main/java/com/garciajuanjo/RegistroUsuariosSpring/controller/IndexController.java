package com.garciajuanjo.RegistroUsuariosSpring.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.garciajuanjo.RegistroUsuariosSpring.constant.ViewsConstant.*;

@Controller
@RequestMapping("/")
public class IndexController {

    private static Log LOG = LogFactory.getLog(IndexController.class);

    @GetMapping("/")
    public String showIndexView(){
        LOG.info("VIEW: index.html");

        return INDEX_VIEW;
    }

    @GetMapping("/login")
    public String showLoginView(Model model,
                                @RequestParam(name = "error", required = false) boolean error){
        LOG.info("VIEW: login.html");

        model.addAttribute("error", error);

        return LOGGIN_VIEW;
    }
}
