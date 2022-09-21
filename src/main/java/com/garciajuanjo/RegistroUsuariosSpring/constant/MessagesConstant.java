package com.garciajuanjo.RegistroUsuariosSpring.constant;

public class MessagesConstant {

    //Mensajes de los pattern de UserAppModel
    public static final String MESSAGE_PATTERN_USERNAME = "El nombre de usuario es obligatorio. Solo puede contener letras y números, entre 4 y 10 caracteres";
    public static final String MESSAGE_PATTERN_PASSWORD = "El campo contraseña es obligatorio y tiene que tener entre 4 y 8 caracteres";
    public static final String MESSAGE_PATTERN_EMAIL = "El formato de email no es correcto";
    public static final String MESSAGE_PATTERN_PHONE = "El campo teléfono es obligatorio, el formato debe ser: +34, 0034, 34 más 9 dígitos";
    public static final String MESSAGE_PATTERN_BIRTH_DATE = "La fecha de nacimiento es obligatoria";

    //Otros mensajes
    public static final String MESSAGE_EMAIL_REQUIRED = "El campo email es obligatorio";
    public static final String MESSAGE_USSERNAME_REQUIRED = "El campo nombre de usuario es obligatorio";
    public static final String MESSAGE_PHONE_REQUIRED = "El campo telefono es obligatorio";
    public static final String MESSAGE_BIRTH_DATE_REQUIRED = "La fecha de nacimiento es obligatoria";
    public static final String MESSAGE_USSERNAME_NOT_BLANK = "El campo nombre de usuario no puede estar vacio";
}
