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
public class UsuarioMapper implements IMapper<Usuario, UsuarioDto> {

    @Override
    public UsuarioDto ToDTO(Usuario ent) {
      return new UsuarioDto(
            ent.getId(),
            ent.getUsuario(),
            ent.getContraseña(),
            ent.getRol(),
            ent.isActivo()
        );
    }  
    

    @Override
    public Usuario ToEntidad(UsuarioDto dto) {
         return new Usuario(
            dto.getId(),
            dto.getUsuario(),
            dto.getContraseña(),
            dto.getRol(),
            dto.isActivo()
        );
    }
}

   

