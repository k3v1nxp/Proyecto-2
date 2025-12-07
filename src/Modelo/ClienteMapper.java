/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author UTN
 */
public class ClienteMapper {
        public ClienteDto toDto(Cliente cliente){
        return new ClienteDto(
            cliente.getId(),
            cliente.getNombre(),
            cliente.getMenbresia(),
            cliente.getFecha_nacimiento(),
            cliente.getNumero(),
            cliente.getFechaVencimientoMembresia()
        );
    }
    
    public Cliente toEntidad(ClienteDto dto){
        return new Cliente(dto.getId(),dto.getNombre(),dto.getMenbresia()
                ,dto.getFecha_nacimiento()
                ,dto.getNumero());
    }
}
