/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Daos.EntrenadorDAO;
import Modelo.EntrenadorDto;
import Modelo.TiposEspecialidades;
import Util.Validaciones;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author UTN
 */
public class Cont_Entrenador {
   private EntrenadorDAO entrenadorDAO;
    
    public Cont_Entrenador() {
        entrenadorDAO = new EntrenadorDAO();
    }
    
    // Método para agregar un entrenador
    public boolean agregarEntrenador(String nombre, String contacto, String especialidadTexto) {
        
        
        if (!Validaciones.esTextoValido(nombre)) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!Validaciones.esTextoValido(contacto)) {
            JOptionPane.showMessageDialog(null, "El contacto no puede estar vacío", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Convertir especialidad de String a Enum
        TiposEspecialidades especialidad;
        try {
            especialidad = TiposEspecialidades.valueOf(especialidadTexto.toUpperCase());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Especialidad no válida", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Crear el DTO (el ID se genera automáticamente en la BD)
        EntrenadorDto dto = new EntrenadorDto(0, nombre, contacto, especialidad);
        
        // Guardar en la base de datos
        try {
            entrenadorDAO.agregar(dto);
            JOptionPane.showMessageDialog(null, "Entrenador agregado correctamente", 
                                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar entrenador: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Método para actualizar un entrenador
    public boolean actualizarEntrenador(int id, String nombre, String contacto, String especialidadTexto) {
        
       
        if (!Validaciones.esTextoValido(nombre)) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!Validaciones.esTextoValido(contacto)) {
            JOptionPane.showMessageDialog(null, "El contacto no puede estar vacío", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!Validaciones.esNumeroPositivo(id)) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Convertir especialidad de String a Enum
        TiposEspecialidades especialidad;
        try {
            especialidad = TiposEspecialidades.valueOf(especialidadTexto.toUpperCase());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Especialidad no válida", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
               EntrenadorDto dto = new EntrenadorDto(id, nombre, contacto, especialidad);
        
        // Actualizar en la base de datos
        try {
            entrenadorDAO.actualizar(dto);
            JOptionPane.showMessageDialog(null, "Entrenador actualizado correctamente", 
                                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar entrenador: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Método para buscar un entrenador por ID
    public EntrenadorDto buscarEntrenador(int id) {
        if (!Validaciones.esNumeroPositivo(id)) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        try {
            EntrenadorDto entrenador = entrenadorDAO.buscarEntrenadorPorId(id);
            if (entrenador == null) {
                JOptionPane.showMessageDialog(null, "No se encontró el entrenador con ID: " + id, 
                                            "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            return entrenador;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar entrenador: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    // Método para eliminar un entrenador
    public boolean eliminarEntrenador(int id) {
        if (!Validaciones.esNumeroPositivo(id)) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de eliminar el entrenador con ID: " + id + "?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                entrenadorDAO.eliminar(id);
                JOptionPane.showMessageDialog(null, "Entrenador eliminado correctamente", 
                                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar entrenador: " + e.getMessage(), 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }
    
   
    public List<EntrenadorDto> obtenerTodosLosEntrenadores() {
        try {
            return entrenadorDAO.obtenerTodo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener entrenadores: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    
    public void llenarTablaEntrenadores(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        
        List<EntrenadorDto> entrenadores = obtenerTodosLosEntrenadores();
        if (entrenadores == null) {
            return;
        }
        
        for (EntrenadorDto entrenador : entrenadores) {
            modelo.addRow(new Object[]{
                entrenador.getId(),
                entrenador.getNombre(),
                entrenador.getContacto(),
                entrenador.getEspecialidad().name()
            });
        }
    }
    
   //da todos los entrenadores de la tabal
    public void buscarEntrenadoresConFiltros(String idTexto, String nombre, String contacto, 
                                             String especialidadTexto, DefaultTableModel modelo) {
        
        
        modelo.setRowCount(0);
        
        
        List<EntrenadorDto> entrenadores = obtenerTodosLosEntrenadores();
        if (entrenadores == null) {
            return;
        }
        
        // Aplicar filtros
        for (EntrenadorDto entrenador : entrenadores) {
            boolean coincide = true;
            
            // Filtro por ID
            if (idTexto != null && !idTexto.trim().isEmpty()) {
                try {
                    int idFiltro = Integer.parseInt(idTexto.trim());
                    if (entrenador.getId() != idFiltro) {
                        coincide = false;
                    }
                } catch (NumberFormatException e) {
                    
                }
            }
            
            // Filtro por nombre
            if (nombre != null && !nombre.trim().isEmpty()) {
                if (!entrenador.getNombre().toLowerCase().contains(nombre.toLowerCase().trim())) {
                    coincide = false;
                }
            }
            
            // Filtro por contacto
            if (contacto != null && !contacto.trim().isEmpty()) {
                if (!entrenador.getContacto().toLowerCase().contains(contacto.toLowerCase().trim())) {
                    coincide = false;
                }
            }
            
            // Filtro por especialidad
            if (especialidadTexto != null && !especialidadTexto.trim().isEmpty()) {
                if (!entrenador.getEspecialidad().name().equalsIgnoreCase(especialidadTexto.trim())) {
                    coincide = false;
                }
            }
            
            
            if (coincide) {
                modelo.addRow(new Object[]{
                    entrenador.getId(),
                    entrenador.getNombre(),
                    entrenador.getContacto(),
                    entrenador.getEspecialidad().name()
                });
            }
        }
    } 
}

