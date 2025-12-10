/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Modelo;

/**
 *
 * @author UTN
 */
public enum T_Rol {
    ENTRENADOR("entrenador"),
    ADMINISTRADOR("administrador");
    
    private String rol;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    private T_Rol(String rol) {
        this.rol = rol;
    }
    
    
    
    
}
