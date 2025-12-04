/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author UTN
 */
public class EntrenadorDto {
    private final int id;
   private final String nombre;
   private final String contacto;
   private final TiposEspecialidades especialidad;

    public int getId() { return id;}
    public String getNombre() {return nombre;}
    public String getContacto() {return contacto;}
    public TiposEspecialidades getEspecialidad() {  return especialidad;}

    public EntrenadorDto(int id, String nombre, String contacto, TiposEspecialidades especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.especialidad = especialidad;
    }
    
    
}
