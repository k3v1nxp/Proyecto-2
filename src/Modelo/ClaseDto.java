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
    private String tipo;
    private String idEntrenador;;
    private String horario;
    private int capacidadMaxima;
    private int capacidadActual;

    public int getId_clase() {
        return id_clase;
    }

    public String getTipo() {
        return tipo;
    }

    public String getidEntrenador() {
        return idEntrenador;
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

    public ClaseDto(int id_clase, String tipo, String idEntrenador, String horario, int capacidadMaxima, int capacidadActual) {
        this.id_clase = id_clase;
        this.tipo = tipo;
        this.idEntrenador =idEntrenador;
        this.horario = horario;
        this.capacidadMaxima = capacidadMaxima;
        this.capacidadActual = capacidadActual;
    }


}
