package com.garciajuanjo.RegistroUsuariosSpring.converter;

import com.garciajuanjo.RegistroUsuariosSpring.component.DateComponent;
import com.garciajuanjo.RegistroUsuariosSpring.entity.UserApp;
import com.garciajuanjo.RegistroUsuariosSpring.model.UserAppModel;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component("userAppConverter")
public class UserAppConverter {

    //Inyectamos el componente para parsear las fechas que recogemos en el formulario de registro
    @Autowired
    @Qualifier("dateComponent")
    private DateComponent dateComponent;

    public UserApp userAppModelToUserApp(UserAppModel userAppModel) {
        UserApp userApp = new UserApp();
        userApp.setId(userAppModel.getId());
        userApp.setUsername(userAppModel.getUsername());
        userApp.setPassword(userAppModel.getPassword());
        userApp.setEnabled(userAppModel.isEnabled());
        userApp.setEmail(userAppModel.getEmail());
        userApp.setPhone(userAppModel.getPhone());
        userApp.setBirthDate(dateComponent.dateToString(userAppModel.getBirthDate()));
        userApp.setRoles(userAppModel.getUserRoles());

        return userApp;
    }

    @SneakyThrows
    public UserAppModel userAppToUserAppModel(UserApp userApp) {
        //Parseamos la fecha de registro y la pasamos a LocalDateTime
        LocalDateTime dischargeDate = userApp.getDischargeDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        UserAppModel userAppModel = new UserAppModel();
        userAppModel.setId(userApp.getId());
        userAppModel.setEmail(userApp.getEmail());
        userAppModel.setPhone(userApp.getPhone());
        userAppModel.setEnabled(userApp.isEnabled());
        userAppModel.setUsername(userApp.getUsername());
        userAppModel.setPassword(userApp.getPassword());
        userAppModel.setBirthDate(dateComponent.stringToDate(userApp.getBirthDate()));
        userAppModel.setDischargeDate(dischargeDate);
        userAppModel.setUserRoles(userApp.getRoles());

        return userAppModel;
    }
}
