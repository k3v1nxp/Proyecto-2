/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author UTN
 */
public class EntrenadorMapper {

    public EntrenadorDto toDto(Entrenador entrenador) {

        return new EntrenadorDto(entrenador.getId(),
                entrenador.getNombre(),
                entrenador.getContacto(),
                entrenador.getEspecialidad());
    }

    public Entrenador toEntidad(EntrenadorDto dto) {

        return new Entrenador(dto.getId(),
                dto.getNombre(),
                dto.getContacto(),
                dto.getEspecialidad());
    }
}
