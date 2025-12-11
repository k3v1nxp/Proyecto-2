/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Daos.ClaseDAO;
import Daos.EntrenadorDAO;
import Modelo.ClaseDto;
import Modelo.EntrenadorDto;
import Util.Validaciones;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public class ControladorClase {
    
    private ClaseDAO claseDAO;
    private EntrenadorDAO entrenadorDAO;
    
    public ControladorClase() {
        claseDAO = new ClaseDAO();
        entrenadorDAO = new EntrenadorDAO();
    }
    
   
    public boolean agregarClase(String tipo, String idEntrenadorTexto, String horario,int capacidadMaxima, int capacidadActual) {
        
        
        if (!Validaciones.esTextoValido(tipo)) {
            JOptionPane.showMessageDialog(null, "El tipo de clase no puede estar vacío","Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!Validaciones.esTextoValido(horario)) {
            JOptionPane.showMessageDialog(null, "El horario no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
       //valida por id
        int idEntrenador;
        try {
            idEntrenador = Integer.parseInt(idEntrenadorTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID del entrenador debe ser un número válido","Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // valida que el entrenador existe
        EntrenadorDto entrenador = entrenadorDAO.buscarEntrenadorPorId(idEntrenador);
        if (entrenador == null) {
            JOptionPane.showMessageDialog(null, "No existe un entrenador con ID: " + idEntrenador, 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        

        if (!Validaciones.esNumeroPositivo(capacidadMaxima)) {
            JOptionPane.showMessageDialog(null, "La capacidad máxima debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (capacidadActual < 0) {
            JOptionPane.showMessageDialog(null, "La capacidad actual no puede ser negativa", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (capacidadActual > capacidadMaxima) {
            JOptionPane.showMessageDialog(null, "La capacidad actual no puede ser mayor que la máxima", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
 
        ClaseDto dto = new ClaseDto(0, tipo, idEntrenadorTexto, horario, capacidadMaxima, capacidadActual);
        
      
        try {
            claseDAO.agregar(dto);
            JOptionPane.showMessageDialog(null, "Clase agregada correctamente", 
                                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar clase: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    

    public boolean actualizarClase(int idClase, String tipo, String idEntrenadorTexto, String horario, 
                                   int capacidadMaxima, int capacidadActual) {
   
        if (!Validaciones.esTextoValido(tipo)) {
            JOptionPane.showMessageDialog(null, "El tipo de clase no puede estar vacío", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!Validaciones.esTextoValido(horario)) {
            JOptionPane.showMessageDialog(null, "El horario no puede estar vacío", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!Validaciones.esNumeroPositivo(idClase)) {
            JOptionPane.showMessageDialog(null, "El ID de la clase debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar ID de entrenador
        int idEntrenador;
        try {
            idEntrenador = Integer.parseInt(idEntrenadorTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID del entrenador debe ser un número válido", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // validar que el entrenador existe
        EntrenadorDto entrenador = entrenadorDAO.buscarEntrenadorPorId(idEntrenador);
        if (entrenador == null) {
            JOptionPane.showMessageDialog(null, "No existe un entrenador con ID: " + idEntrenador, 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar capacidades
        if (!Validaciones.esNumeroPositivo(capacidadMaxima)) {
            JOptionPane.showMessageDialog(null, "La capacidad máxima debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (capacidadActual < 0) {
            JOptionPane.showMessageDialog(null, "La capacidad actual no puede ser negativa", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (capacidadActual > capacidadMaxima) {
            JOptionPane.showMessageDialog(null, "La capacidad actual no puede ser mayor que la máxima", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        ClaseDto dto = new ClaseDto(idClase, tipo, idEntrenadorTexto, horario, capacidadMaxima, capacidadActual);
        
        try {
            claseDAO.actualizar(dto);
            JOptionPane.showMessageDialog(null, "Clase actualizada correctamente", 
                                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar clase: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
   
    public ClaseDto buscarClase(int idClase) {
        if (!Validaciones.esNumeroPositivo(idClase)) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        try {
            ClaseDto clase = claseDAO.buscar(idClase);
            if (clase == null) {
                JOptionPane.showMessageDialog(null, "No se encontró la clase con ID: " + idClase, 
                                            "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            return clase;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar clase: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    

    public boolean eliminarClase(int idClase) {
        if (!Validaciones.esNumeroPositivo(idClase)) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de eliminar la clase con ID: " + idClase + "?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                claseDAO.eliminar(idClase);
                JOptionPane.showMessageDialog(null, "Clase eliminada correctamente", 
                                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar clase: " + e.getMessage(), 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }
    

    public List<ClaseDto> obtenerTodasLasClases() {
        try {
            return claseDAO.obtenerTodo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener clases: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    

    public void llenarTablaClases(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        
        List<ClaseDto> clases = obtenerTodasLasClases();
        if (clases == null) {
            return;
        }
        
        for (ClaseDto clase : clases) {
            modelo.addRow(new Object[]{
                clase.getId_clase(),
                clase.getTipo(),
                clase.getidEntrenador(),
                clase.getHorario(),
                clase.getCapacidadMaxima(),
                clase.getCapacidadActual()
            });
        }
    }
    
    public String obtenerNombreEntrenador(int idEntrenador) {
        try {
            EntrenadorDto entrenador = entrenadorDAO.buscarEntrenadorPorId(idEntrenador);
            if (entrenador != null) {
                return entrenador.getNombre();
            }
        } catch (Exception e) {
           
        }
        return String.valueOf(idEntrenador);
    }
}

