/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Daos.ClienteDAO;
import Modelo.ClienteDto;
import Modelo.TipoMembresia;
import Util.Validaciones;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public class ControladorCliente {
      private ClienteDAO clienteDAO;
    
    public ControladorCliente() {
        clienteDAO = new ClienteDAO();
    }
    
    public boolean agregarCliente(int id, String nombre, String membresiaTexto, String fechaNacimientoTexto, int numero, String fechaVencimientoTexto) {
        
        // Campos vacios
        if (!Validaciones.esTextoValido(nombre)) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", 
            "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!Validaciones.esNumeroPositivo(id)) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!Validaciones.esNumeroPositivo(numero)) {
            JOptionPane.showMessageDialog(null, "El número de contacto debe ser positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar y parsear fecha de nacimiento
        Date fechaNacimiento = Validaciones.parsearFecha(fechaNacimientoTexto);
        if (fechaNacimiento == null) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no es válida. Use formato dd/MM/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
       
        Date fechaVencimiento = null;
        if (fechaVencimientoTexto != null && !fechaVencimientoTexto.trim().isEmpty()) {
            fechaVencimiento = Validaciones.parsearFecha(fechaVencimientoTexto);
        }
        
        // Convierte de String a Enum
        TipoMembresia membresia;
        try {
            membresia = TipoMembresia.valueOf(membresiaTexto);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Tipo de membresía no válido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //crea 
        ClienteDto dto;
        if (fechaVencimiento != null) {
            dto = new ClienteDto(id, nombre, membresia, fechaNacimiento, numero, fechaVencimiento);
        } else {
            dto = new ClienteDto(id, nombre, membresia, fechaNacimiento, numero);
        }
        
        try {
            clienteDAO.agregar(dto);
            JOptionPane.showMessageDialog(null, "Cliente agregado correctamente", 
                                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar cliente: " + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean actualizarCliente(int id, String nombre, String membresiaTexto,String fechaNacimientoTexto, int numero, String fechaVencimientoTexto) {
        
        
        if (!Validaciones.esTextoValido(nombre)) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío","Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!Validaciones.esNumeroPositivo(numero)) {
            JOptionPane.showMessageDialog(null, "El número de contacto debe ser positivo","Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        Date fechaNacimiento = Validaciones.parsearFecha(fechaNacimientoTexto);
        if (fechaNacimiento == null) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no es válida. Use formato dd/MM/yyyy", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // cadenade texto fecha de vencimiento (puede ser null)
        Date fechaVencimiento = null;
        if (fechaVencimientoTexto != null && !fechaVencimientoTexto.trim().isEmpty()) {
            fechaVencimiento = Validaciones.parsearFecha(fechaVencimientoTexto);
        }
        
        // String a Enum
        TipoMembresia membresia;
        try {
            membresia = TipoMembresia.valueOf(membresiaTexto);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Tipo de membresía no válido", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Crear el DTO
        ClienteDto dto;
        if (fechaVencimiento != null) {
            dto = new ClienteDto(id, nombre, membresia, fechaNacimiento, numero, fechaVencimiento);
        } else {
            dto = new ClienteDto(id, nombre, membresia, fechaNacimiento, numero);
        }
        
        try {
            clienteDAO.actualizar(dto);
            JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente", 
                                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar cliente: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public ClienteDto buscarCliente(int id) {
        if (!Validaciones.esNumeroPositivo(id)) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo","Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        try {
            ClienteDto cliente = clienteDAO.buscar(id);
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "No se encontró el cliente con ID: " + id,"Información", JOptionPane.INFORMATION_MESSAGE);
            }
            return cliente;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar cliente: " + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public boolean eliminarCliente(int id) {
        if (!Validaciones.esNumeroPositivo(id)) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo","Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de eliminar el cliente con ID: " + id + "?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                clienteDAO.eliminar(id);
                JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente", 
                                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage(), 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }
    

    public List<ClienteDto> obtenerTodosLosClientes() {
        try {
            return clienteDAO.obtenerTodo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener clientes: " + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    

    public void buscarClientesConFiltros(String idTexto, String nombre, String fechaNacimientoTexto,String contactoTexto, String membresiaTexto,DefaultTableModel modelo) {
        modelo.setRowCount(0);
        
      
        List<ClienteDto> clientes = obtenerTodosLosClientes();
        if (clientes == null) {
            return;
        }
      
        for (ClienteDto cliente : clientes) {
            boolean coincide = true;
            
            // Filtro por ID
            if (idTexto != null && !idTexto.trim().isEmpty()) {
                try {
                    int idFiltro = Integer.parseInt(idTexto.trim());
                    if (cliente.getId() != idFiltro) {
                        coincide = false;
                    }
                } catch (NumberFormatException e) {
                    //valida es numero
                }
            }
            
            // Filtro por nombre
            if (nombre != null && !nombre.trim().isEmpty()) {
                if (!cliente.getNombre().toLowerCase().contains(nombre.toLowerCase().trim())) {
                    coincide = false;
                }
            }
            
            // Filtro por fecha de nacimiento
            if (fechaNacimientoTexto != null && !fechaNacimientoTexto.trim().isEmpty()) {
                Date fechaFiltro = Validaciones.parsearFecha(fechaNacimientoTexto);
                if (fechaFiltro != null) {
                    if (!cliente.getFecha_nacimiento().equals(fechaFiltro)) {
                        coincide = false;
                    }
                }
            }
            
            // Filtro por contacto
            if (contactoTexto != null && !contactoTexto.trim().isEmpty()) {
                try {
                    int contactoFiltro = Integer.parseInt(contactoTexto.trim());
                    if (cliente.getNumero() != contactoFiltro) {
                        coincide = false;
                    }
                } catch (NumberFormatException e) {
                    // Si no es un número válido, no filtrar por contacto
                }
            }
            
            // Filtro por membresía
            if (membresiaTexto != null && !membresiaTexto.trim().isEmpty()) {
                if (!cliente.getMenbresia().name().equalsIgnoreCase(membresiaTexto.trim())) {
                    coincide = false;
                }
            }
            
            // Si coincide con todos los filtros agregar a la tabla
            if (coincide) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                String fechaNac = sdf.format(cliente.getFecha_nacimiento());
                
                modelo.addRow(new Object[]{
                    cliente.getId(),
                    cliente.getNombre(),
                    fechaNac,
                    cliente.getNumero(),
                    cliente.getMenbresia().name()
                });
            }
        }
    }
    

    public void llenarTablaClientes(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        
        List<ClienteDto> clientes = obtenerTodosLosClientes();
        if (clientes == null) {
            return;
        }
        
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        
        for (ClienteDto cliente : clientes) {
            String fechaNac = sdf.format(cliente.getFecha_nacimiento());
            
            modelo.addRow(new Object[]{
                cliente.getId(),
                cliente.getNombre(),
                fechaNac,
                cliente.getNumero(),
                cliente.getMenbresia().name()
            });
        }
    }
}

