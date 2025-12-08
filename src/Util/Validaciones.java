/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author kevin
 */
public class Validaciones {
    
    
     // Valida un texto no está vacío
    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
    
    // Valida numero positivo
    public static boolean esNumeroPositivo(int numero) {
        return numero > 0;
    }
    
    
    //  Valida numeros decimal positivos
    public static boolean esNumeroPositivo(double numero) {
        return numero > 0;
    }
    
    // Valida fecha (formato dd/MM/yyyy)
    public static Date parsearFecha(String fechaTexto) {
        if (fechaTexto == null || fechaTexto.trim().isEmpty()) {
            return null;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(fechaTexto);
        } catch (ParseException e) {
            return null;
        }
    }
    
    
    //Valida si un email tiene formato con @ y .
    
    public static boolean esEmailValido(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }
    
    
     // Valida si el telfono son solo numero y que sean 8 digitos
    public static boolean esTelefonoValido(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return false;
        }
        return telefono.matches("\\d{8}");
    }
}
