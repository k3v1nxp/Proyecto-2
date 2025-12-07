/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Modelo;

/**
 *
 * @author UTN
 */
public enum Tipos_de_Clases {
    CARDIO("Cardiovascular"),
    PESA("Pesa"),
    CROSSFIT("Crossfit"),
    ZUMBA("Zumba");
    
    private final String nombre;
    
    private Tipos_de_Clases(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
}
