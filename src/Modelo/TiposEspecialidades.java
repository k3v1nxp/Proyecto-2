/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Modelo;

/**
 *
 * @author UTN
 */
public enum TiposEspecialidades {
    FUERZA_Y_ACONDICIONAMIENTO("Fuerza"),


    Entrenamiento_cardiovascular("Cardio"),

    Entrenamientos_cuerpo_mente("Yoga"),

    Especialidades_deportivas("Deportistas"),

    bienestar_y_salud("Salud"),

    clases_grupales("Clases");
    
    private String Especialidad;

    public String getEspecialidad() {
        return Especialidad;
    }

    private TiposEspecialidades(String Especialidad) {
        this.Especialidad = Especialidad;
    }
    
}
