/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author UTN
 */
public class Entrenador {
   private int id;
   private String nombre;
   private String contacto;
   private TiposEspecialidades especialidad;

    public int getId() { return id;}
    public String getNombre() {return nombre;}
    public String getContacto() {return contacto;}
    public TiposEspecialidades getEspecialidad() {  return especialidad;}

    public void setId(int id) {this.id = id;}
    public void setNombre(String nombre) { this.nombre = nombre;}
    public void setContacto(String contacto) {this.contacto = contacto;}
    public void setEspecialidad(TiposEspecialidades especialidad) {  this.especialidad = especialidad;}
      
    public Entrenador() {}
    public Entrenador(int id, String nombre, String contacto, TiposEspecialidades especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.especialidad = especialidad;
    }
   
   
}
