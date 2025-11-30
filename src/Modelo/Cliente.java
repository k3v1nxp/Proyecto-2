/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author UTN
 */
public class Cliente {
   private int id; 
   private String nombre;
   private TipoMembresia menbresia;
   private Date fecha_nacimiento;
   private int Numero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public TipoMembresia getMenbresia() {
        return menbresia;
    }

    public void setMenbresia(TipoMembresia menbresia) {
        this.menbresia = menbresia;
    }

    public Cliente(int id, String nombre, TipoMembresia menbresia, Date fecha_nacimiento, int Numero) {
        this.id = id;
        this.nombre = nombre;
        this.menbresia = menbresia;
        this.fecha_nacimiento = fecha_nacimiento;
        this.Numero = Numero;
    }
   
}
