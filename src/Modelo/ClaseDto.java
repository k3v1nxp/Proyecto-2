/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author kevin
 */
public class ClaseDto {
    private int id_clase;
    private Tipos_de_Clases tipo;
    private Entrenador entrenador;
    private String horario;
    private int capacidadMaxima;
    private int capacidadActual;

    public int getId_clase() {
        return id_clase;
    }

    public Tipos_de_Clases getTipo() {
        return tipo;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public String getHorario() {
        return horario;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public ClaseDto(int id_clase, Tipos_de_Clases tipo, Entrenador entrenador, String horario, int capacidadMaxima, int capacidadActual) {
        this.id_clase = id_clase;
        this.tipo = tipo;
        this.entrenador = entrenador;
        this.horario = horario;
        this.capacidadMaxima = capacidadMaxima;
        this.capacidadActual = capacidadActual;
    }
}
