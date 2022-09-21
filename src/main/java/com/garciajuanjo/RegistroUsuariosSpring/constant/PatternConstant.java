package com.garciajuanjo.RegistroUsuariosSpring.constant;

public class PatternConstant {

    public static final String PATTERN_USERNAME = "[a-z A-Z1-9Ññ]{4,10}";
    public static final String PATTERN_PASSWORD = ".{4,8}";
    public static final String PATTERN_EMAIL = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$";
    public static final String PATTERN_PHONE = "(\\+34|0034|34)?(6|7)([0-9]){8}";
}
