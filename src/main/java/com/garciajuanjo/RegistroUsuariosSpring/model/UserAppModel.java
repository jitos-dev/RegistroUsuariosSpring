package com.garciajuanjo.RegistroUsuariosSpring.model;

import com.garciajuanjo.RegistroUsuariosSpring.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.garciajuanjo.RegistroUsuariosSpring.constant.MessagesConstant.*;
import static com.garciajuanjo.RegistroUsuariosSpring.constant.PatternConstant.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAppModel {

    private int id;

    //Letras myusculas y minúsculas sin tildes más números y la letra ñ
    @Pattern(regexp = PATTERN_USERNAME, message = MESSAGE_PATTERN_USERNAME)
    private String username;

    //Cualquier dígito y que tenga entre 4 y 8 caracteres
    @Pattern(regexp = PATTERN_PASSWORD, message = MESSAGE_PATTERN_PASSWORD)
    private String password;

    //Igual que la de arriba
    @Pattern(regexp = PATTERN_PASSWORD, message = MESSAGE_PATTERN_PASSWORD)
    private String password2;

    private boolean enabled;

    @NotBlank(message = MESSAGE_EMAIL_REQUIRED)
    @Email
    @Pattern(regexp = PATTERN_EMAIL, message = MESSAGE_PATTERN_EMAIL)
    private String email;

    @Pattern(regexp = PATTERN_PHONE, message = MESSAGE_PATTERN_PHONE)
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past //Esto es para que solo acepte fechas anteriores a la de hoy. No puedes registrar una fecha posterior.
    @NotNull(message = MESSAGE_PATTERN_BIRTH_DATE)
    private Date birthDate;

    private LocalDateTime dischargeDate;
    private Set<Roles> userRoles = new HashSet<>();
}
