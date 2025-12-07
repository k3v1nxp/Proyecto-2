/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author kevin
 */

public class Seguridad {
        
    public static String hashcode(String texto) {
        return String.valueOf(texto.hashCode());
    }
    public static boolean verificarContraseña(String contraseña, String hashGuardado) {
        String hashCalculado = hashcode(contraseña);
        return hashCalculado.equals(hashGuardado);
    }
    
}

