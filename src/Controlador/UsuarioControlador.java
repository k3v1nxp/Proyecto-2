/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Daos.UsuarioDAO;
import Modelo.T_Rol;
import Modelo.UsuarioDto;
import Util.Seguridad;
import Util.Validaciones;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public class UsuarioControlador {
        
    private UsuarioDAO usuarioDAO;
    
    public UsuarioControlador() {
        usuarioDAO = new UsuarioDAO();
    }
    
    public UsuarioDto validarLogin(String usuario, String contraseña) {
        
        if (!Validaciones.esTextoValido(usuario)) {
            JOptionPane.showMessageDialog(null, "El usuario no puede estar vacío", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        if (!Validaciones.esTextoValido(contraseña)) {
            JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
     
        try {
            List<UsuarioDto> usuarios = usuarioDAO.obtenerTodo();
            
            for (UsuarioDto u : usuarios) {
                if (u.getUsuario().equals(usuario)) {
                    // Verificar si el usuario está activo
                    if (!u.isActivo()) {
                        JOptionPane.showMessageDialog(null, "El usuario está desactivado", 
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                    
                    // Verificar la contraseña (comparar hash)
                    if (Seguridad.verificarContraseña(contraseña, u.getContraseña())) {
                        JOptionPane.showMessageDialog(null, "Login exitoso. Bienvenido " + usuario, 
                                                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        return u;
                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta", 
                                                    "Error", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                }
            }
            
            JOptionPane.showMessageDialog(null, "Usuario no encontrado", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al validar login: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public boolean agregarUsuario(String usuario, String contraseña, String rolTexto) {
        
        // Validar que los campos no estén vacíos
        if (!Validaciones.esTextoValido(usuario)) {
            JOptionPane.showMessageDialog(null, "El usuario no puede estar vacío", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!Validaciones.esTextoValido(contraseña)) {
            JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //valididacion extra de contrasema no dio tiempo para util
        if (contraseña.length() < 6) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 6 caracteres","Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
      // Convertir rol de String a Enum
        T_Rol rol;
        try {
            rol = T_Rol.valueOf(rolTexto.toUpperCase());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Rol no válido. Use: ADMINISTRADOR o ENTRENADOR", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Verificar que el usuario no exista ya
        List<UsuarioDto> usuarios = usuarioDAO.obtenerTodo();
        for (UsuarioDto u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                JOptionPane.showMessageDialog(null, "El usuario ya existe", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        // Encribtar la contraseña
        String contraseñaEncriptada = Seguridad.hashcode(contraseña);
        
        // crea el DTO (el ID se genera automáticamente en la BD)
        UsuarioDto dto = new UsuarioDto(0, usuario, contraseñaEncriptada, rol, true);
        
        // Guardar en la base de datos
        try {
            usuarioDAO.agregar(dto);
            JOptionPane.showMessageDialog(null, "Usuario agregado correctamente", 
                                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar usuario: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
   
    public boolean actualizarUsuario(int id, String usuario, String contraseña, String rolTexto, boolean activo) {
        
        // Validar que los campos no estén vacíos
        if (!Validaciones.esTextoValido(usuario)) {
            JOptionPane.showMessageDialog(null, "El usuario no puede estar vacío", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!Validaciones.esNumeroPositivo(id)) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Convertir rol de String a Enum
        T_Rol rol;
        try {
            rol = T_Rol.valueOf(rolTexto.toUpperCase());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Rol no válido. Use: ADMINISTRADOR o ENTRENADOR", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Encriptar la contraseña si se proporciona
        String contraseñaEncriptada = null;
        if (contraseña != null && !contraseña.trim().isEmpty()) {
            if (contraseña.length() < 6) {
                JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 6 caracteres", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            contraseñaEncriptada = Seguridad.hashcode(contraseña);
        } else {
            // Si no se proporciona nueva contraseña
            UsuarioDto usuarioActual = usuarioDAO.buscar(id);
            if (usuarioActual != null) {
                contraseñaEncriptada = usuarioActual.getContraseña();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el usuario con ID: " + id,"Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        // Crear el DTO
        UsuarioDto dto = new UsuarioDto(id, usuario, contraseñaEncriptada, rol, activo);
        
        // Actualizar en la base de datos
        try {
            usuarioDAO.actualizar(dto);
            JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente", 
                                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    

    public UsuarioDto buscarUsuario(int id) {
        if (!Validaciones.esNumeroPositivo(id)) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        try {
            UsuarioDto usuario = usuarioDAO.buscar(id);
            if (usuario == null) {
                JOptionPane.showMessageDialog(null, "No se encontró el usuario con ID: " + id, 
                                            "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            return usuario;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar usuario: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public boolean eliminarUsuario(int id) {
        if (!Validaciones.esNumeroPositivo(id)) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de eliminar el usuario con ID: " + id + "?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                usuarioDAO.eliminar(id);
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente", 
                                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e.getMessage(), 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }
    
   
    public List<UsuarioDto> obtenerTodosLosUsuarios() {
        try {
            return usuarioDAO.obtenerTodo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener usuarios: " + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public void llenarTablaUsuarios(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        
        List<UsuarioDto> usuarios = obtenerTodosLosUsuarios();
        if (usuarios == null) {
            return;
        }
        
        for (UsuarioDto usuario : usuarios) {
            modelo.addRow(new Object[]{
                usuario.getId(),
                usuario.getUsuario(),
                usuario.getRol().name(),
                usuario.isActivo() ? "Activo" : "Inactivo"
            });
        }
    }
    
    public boolean cambiarEstadoUsuario(int id, boolean activo) {
        UsuarioDto usuario = buscarUsuario(id);
        if (usuario == null) {
            return false;
        }
        
        return actualizarUsuario(id, usuario.getUsuario(), null, usuario.getRol().name(), activo);
    }

}
