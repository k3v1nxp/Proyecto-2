/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Mapper.IMapper;

/**
 *
 * @author kevin
 */
public class ClaseMapper implements IMapper<Clase,ClaseDto> {
     private String idEntrenador;

    @Override
    public ClaseDto ToDTO(Clase ent) {
        return new ClaseDto(
            ent.getId_clase(),
            ent.getTipo().name(),
            String.valueOf(ent.getEntrenador().getId()),
            ent.getHorario(),
            ent.getCapacidadMaxima(),
            ent.getCapacidadActual());
        }

    @Override
    public Clase ToEntidad(ClaseDto dto) {
       Entrenador entrenador = new Entrenador();
       entrenador.setId(Integer.parseInt(dto.getidEntrenador()));

        return new Clase(
            dto.getId_clase(),
            Tipos_de_Clases.valueOf(dto.getTipo().toUpperCase()),
            entrenador,
            dto.getHorario(),
            dto.getCapacidadMaxima(),
            dto.getCapacidadActual()
        );
    }
}

