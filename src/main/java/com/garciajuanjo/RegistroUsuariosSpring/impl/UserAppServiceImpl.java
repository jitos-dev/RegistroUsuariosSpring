package com.garciajuanjo.RegistroUsuariosSpring.impl;

import com.garciajuanjo.RegistroUsuariosSpring.converter.UserAppConverter;
import com.garciajuanjo.RegistroUsuariosSpring.entity.UserApp;
import com.garciajuanjo.RegistroUsuariosSpring.model.UserAppModel;
import com.garciajuanjo.RegistroUsuariosSpring.repository.UserAppRepository;
import com.garciajuanjo.RegistroUsuariosSpring.service.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service("userAppServiceImpl")
public class UserAppServiceImpl implements UserAppService {

    //Repositorio para trabajar con JpaRepository
    @Autowired
    @Qualifier("userAppRepository")
    private UserAppRepository userAppRepository;

    //Componente para parsear de UserApp a UserAppModel y viceversa
    @Autowired
    @Qualifier("userAppConverter")
    private UserAppConverter userAppConverter;

    /**
     * Método para añadir un usuario que se registra en la página. Recibimos un UserAppModel por lo que
     * lo tenemos que parsear a UserApp para poder guardarlo. Si no ha guardado en la BBDD userApp no es null
     * y lo parseamos a UserAppModel para devolverlo. En caso contrario devolvemos null
     * @param userAppModel que viene del formulario de registro
     * @return UserAppModel del usuario que se acaba de registrar
     */
    @Override
    public UserAppModel addUserApp(UserAppModel userAppModel) {
        //Codificamos la contraseña antes de guardarla en la BBDD
        userAppModel.setPassword(new BCryptPasswordEncoder(10).encode(userAppModel.getPassword()));

        //Convertimos el UserAppModel en UserApp para guardarlo
        UserApp userApp = userAppRepository.save(userAppConverter.userAppModelToUserApp(userAppModel));

        //Si se ha guardado lo volvemos a convertir a UserAppModel y lo devolvemos
        if (userApp != null)
            return userAppConverter.userAppToUserAppModel(userApp);
        else
            return null;
    }

    /**
     * Devuelve un objeto UserApp que busca en la BBDD por el username
     * @param username nombre de usuario
     * @return objeto de la entidad UserApp
     */
    @Override
    public UserApp findByUsername(String username) {
        return userAppRepository.findByUsername(username);
    }
}
