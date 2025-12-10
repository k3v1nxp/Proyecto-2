/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author kevin
 */
public class UsuarioDto {
    private final int id;
    private final String usuario;
    private final String contraseña;
    private final T_Rol rol;
    private final boolean activo;

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public T_Rol getRol() {
        return rol;
    }

    public boolean isActivo() {
        return activo;
    }

public UsuarioDto(int id, String usuario, String contraseña, T_Rol rol, boolean activo) {
        this.id = id;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
        this.activo = activo;
    }
}
