/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author UTN
 */
public class Clase {
    private int id_clase;
    private Tipos_de_Clases tipo;
    private Entrenador entrenador;

    public int getId_clase() {
        return id_clase;
    }

    public Tipos_de_Clases getTipo() {
        return tipo;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setId_clase(int id_clase) {
        this.id_clase = id_clase;
    }

    public void setTipo(Tipos_de_Clases tipo) {
        this.tipo = tipo;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Clase(int id_clase, Tipos_de_Clases tipo, Entrenador entrenador) {
        this.id_clase = id_clase;
        this.tipo = tipo;
        this.entrenador = entrenador;
    }
    
    
    
}
