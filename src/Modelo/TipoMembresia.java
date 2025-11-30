/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Modelo;

/**
 *
 * @author UTN
 */
public enum TipoMembresia {
    Basica("Semanal"),
    Estandar("15 dias"),
    Premiun("Mensual");
    
     private final String tipoMembresia;

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    private TipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }
     
     
}
