package com.garciajuanjo.RegistroUsuariosSpring.component;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component("dateComponent")
public class DateComponent {

    /**
     * Este método es para pasar una fecha (de tipo Date) a un String con el formato especificado en el Pattern. Si
     * no se pone el pattern el formato que obtenemos es yyyy-MM-dd. Si no ponemos el .format() lo que obtenemos es
     * un LocalDate que podemos manejar para fechas con un montón de opciones.
     * @param date fecha que convertimos en String
     * @return String con la fecha en formato dd-MM-yyyy
     */
    public String dateToString(Date date) {

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public Date stringToDate(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return sdf.parse(str);
    }

}