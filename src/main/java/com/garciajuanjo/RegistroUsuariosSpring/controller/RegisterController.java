package com.garciajuanjo.RegistroUsuariosSpring.controller;

import com.garciajuanjo.RegistroUsuariosSpring.entity.Roles;
import com.garciajuanjo.RegistroUsuariosSpring.model.UserAppModel;
import com.garciajuanjo.RegistroUsuariosSpring.repository.UserAppRepositoryQueryDSL;
import com.garciajuanjo.RegistroUsuariosSpring.service.UserAppService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.HashSet;
import java.util.Set;

import static com.garciajuanjo.RegistroUsuariosSpring.constant.ViewsConstant.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    //Log para logear la aplicación
    private static final Log LOGGER = LogFactory.getLog(RegisterController.class);

    //Repositorio de UserApp para trabajar con QueryDSL
    @Autowired
    @Qualifier("userAppRepositoryQueryDSL")
    private UserAppRepositoryQueryDSL userAppRepositoryQueryDSL;

    //Implementación del repositorio de UserApp para trabajar con JpaRepository
    @Autowired
    @Qualifier("userAppServiceImpl")
    private UserAppService userAppService;

    @GetMapping("/formRegister")
    public String showViewRegister(Model model) {
        LOGGER.info("VIEW: register.html");

        model.addAttribute("userAppModel", new UserAppModel());
        return REGISTER_VIEW;
    }

    @PostMapping("/addUser")
    public ModelAndView addUser(@ModelAttribute("userAppModel") @Valid UserAppModel userAppModel, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        LOGGER.info("USER_APP_MODEL: " + userAppModel);

        //Si el formulario tiene errores lo volvemos a mostrar con los mensajes de error que los tiene el bindingResult
        if (bindingResult.hasErrors()) {
            mav.setViewName(REGISTER_VIEW);
        } else {
            //Pasamos la vista del registro al ModelAndView por si hay errores que se vaya hay. Si luego no hay la cambiamos
            mav.setViewName(REGISTER_VIEW);

            //Comprobamos que no exista un usuario con ese nombre de usuario
            boolean exist = userAppRepositoryQueryDSL.existUserAppByUsername(userAppModel.getUsername());

            //Si existe el nombre de usuario volvemos al registro y sacamos el mensaje de error
            if (exist) {
                mav.addObject("existUsername", true);
                return mav;
            }

            exist = userAppRepositoryQueryDSL.existEmail(userAppModel.getEmail());

            //Si existe el nombre de usuario volvemos al registro y sacamos el mensaje de error
            if (exist) {
                mav.addObject("existEmail", true);
                return mav;
            }

            //Comprobamos que las contaseñas que ha introducido son iguales
            if (!userAppModel.getPassword().equals(userAppModel.getPassword2())) {
                mav.addObject("errorPassword", true);
                return mav;
            }

            //Si no esta registrado el email, ni el username, ni tiene fallos en el formulario lo guardamos en BBDD
            addRoleAdmin(userAppModel);
            UserAppModel uam = userAppService.addUserApp(userAppModel);

            /*Si llegamos aquí ya nos vamos a la vista de login bien con un mensaje de que se a guardado correctamente
            o con un mensaje de error al guarda los datos*/
            mav.setViewName(LOGGIN_VIEW);
            if (uam != null)
                mav.addObject("saveUser", true);
            else
                mav.addObject("errorSaveUser", true);

            //TODO si el registro esta correcto mandar un email para que lo valide el usuario y el enabled lo pongo a true
        }

        return mav;
    }

    /**
     * Añade el ROLE_ADMIN a un UserAppModel que pasamos por parámetro
     * @param uam UserAppModel al que añadimos el ROLE_ADMIN
     */
    private void addRoleAdmin(UserAppModel uam){
        Set<Roles> roles = new HashSet<>();
        roles.add(new Roles(1, "ROLE_ADMIN"));
        uam.setUserRoles(roles);
    }
}
