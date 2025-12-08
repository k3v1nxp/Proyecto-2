/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author kevin
 */
public class ClaseMapper {
    public ClaseDto toDto(Clase clase){
        return new ClaseDto(clase.getId_clase(),clase.getTipo(),clase.getEntrenador(),
        clase.getHorario(),clase.getCapacidadMaxima(),clase.getCapacidadActual());
        
    }
    
    public Clase toEntidad(ClaseDto dto){
        return new Clase(dto.getId_clase(),dto.getTipo(),dto.getEntrenador(),
        dto.getHorario(),dto.getCapacidadMaxima(),dto.getCapacidadActual());
    }
}

