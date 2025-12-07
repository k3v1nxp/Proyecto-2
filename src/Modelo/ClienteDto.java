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
public class ClienteDto {
   private final int id; 
   private final String nombre;
   private final TipoMembresia menbresia;
   private final Date fecha_nacimiento;
   private final int numero;
   private final Date fechaVencimientoMembresia;
  
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoMembresia getMenbresia() {
        return menbresia;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public Date getFechaVencimientoMembresia() {
        return fechaVencimientoMembresia;
    }

    public int getNumero() {
        return Numero;
    }

    public ClienteDto(int id, String nombre, TipoMembresia membresia, Date fecha_nacimiento, int numero) {
        this.id = id;
        this.nombre = nombre;
        this.menbresia = membresia;
        this.fecha_nacimiento = fecha_nacimiento;
        this.numero = numero;
        this.fechaVencimientoMembresia = null;
    }
    
    public ClienteDto(int id, String nombre, TipoMembresia membresia, Date fecha_nacimiento, int numero, Date fechaVencimientoMembresia) {
        this.id = id;
        this.nombre = nombre;
        this.menbresia = membresia;
        this.fecha_nacimiento = fecha_nacimiento;
        this.numero = numero;
        this.fechaVencimientoMembresia = fechaVencimientoMembresia;
    }
    
}
