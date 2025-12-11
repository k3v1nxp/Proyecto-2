/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Daos.ClienteDAO;
import Daos.PagoDAO;
import Modelo.ClienteDto;
import Modelo.PagoDto;
import Util.Validaciones;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author UTN
 */
public class Cont_Pago {
    private PagoDAO pagoDAO;
    private ClienteDAO clienteDAO;
    private static final double PORCENTAJE_IMPUESTO = 0.13; 
    
    public Cont_Pago() {
        pagoDAO = new PagoDAO();
        clienteDAO = new ClienteDAO();
    }
    
    // Método para buscar un cliente por ID y mostrar sus datos
    public ClienteDto buscarClienteParaPago(int idCliente) {
        if (!Validaciones.esNumeroPositivo(idCliente)) {
            JOptionPane.showMessageDialog(null, "El ID del cliente debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        try {
            ClienteDto cliente = clienteDAO.buscar(idCliente);
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "No se encontró el cliente con ID: " + idCliente, 
                                            "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            return cliente;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar cliente: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    
    public double[] calcularPago(int monto) {
        if (!Validaciones.esNumeroPositivo(monto)) {
            JOptionPane.showMessageDialog(null, "El monto debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        double subtotal = monto;
        double impuesto = subtotal * PORCENTAJE_IMPUESTO;
        double total = subtotal + impuesto;
        
        return new double[]{subtotal, impuesto, total};
    }
    
    // Método para realizar un pago
    public boolean realizarPago(int idCliente, int monto) {
        
       
        ClienteDto cliente = buscarClienteParaPago(idCliente);
        if (cliente == null) {
            return false;
        }
        
      
        if (!Validaciones.esNumeroPositivo(monto)) {
            JOptionPane.showMessageDialog(null, "El monto debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        double[] calculos = calcularPago(monto);
        if (calculos == null) {
            return false;
        }
        
        double subtotal = calculos[0];
        double impuesto = calculos[1];
        double total = calculos[2];
        
        
        Date fechaActual = new Date();
        PagoDto dto = new PagoDto(0, subtotal, impuesto, total, monto, idCliente, fechaActual);
        
       //esto si no lo entiendo jajaja
        try {
            pagoDAO.agregar(dto);
            JOptionPane.showMessageDialog(null, 
                "Pago realizado correctamente\n" +
                "Subtotal: $" + String.format("%.2f", subtotal) + "\n" +
                "Impuesto: $" + String.format("%.2f", impuesto) + "\n" +
                "Total: $" + String.format("%.2f", total), 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar el pago: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Método para actualizar un pago
    public boolean actualizarPago(int idPago, int idCliente, int monto) {
        
        // Validar que el cliente existe
        ClienteDto cliente = buscarClienteParaPago(idCliente);
        if (cliente == null) {
            return false;
        }
        
       
        if (!Validaciones.esNumeroPositivo(monto)) {
            JOptionPane.showMessageDialog(null, "El monto debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!Validaciones.esNumeroPositivo(idPago)) {
            JOptionPane.showMessageDialog(null, "El ID del pago debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        double[] calculos = calcularPago(monto);
        if (calculos == null) {
            return false;
        }
        
        double subtotal = calculos[0];
        double impuesto = calculos[1];
        double total = calculos[2];
        
               Date fechaActual = new Date();
        PagoDto dto = new PagoDto(idPago, subtotal, impuesto, total, monto, idCliente, fechaActual);
        
       
        try {
            pagoDAO.actualizar(dto);
            JOptionPane.showMessageDialog(null, "Pago actualizado correctamente", 
                                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el pago: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
   
    public PagoDto buscarPago(int idPago) {
        if (!Validaciones.esNumeroPositivo(idPago)) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        try {
            PagoDto pago = pagoDAO.buscar(idPago);
            if (pago == null) {
                JOptionPane.showMessageDialog(null, "No se encontró el pago con ID: " + idPago, 
                                            "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            return pago;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar pago: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    // Método para eliminar un pago
    public boolean eliminarPago(int idPago) {
        if (!Validaciones.esNumeroPositivo(idPago)) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de eliminar el pago con ID: " + idPago + "?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                pagoDAO.eliminar(idPago);
                JOptionPane.showMessageDialog(null, "Pago eliminado correctamente", 
                                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar pago: " + e.getMessage(), 
                                            "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }
    
    // Método para obtener todos los pagos
    public List<PagoDto> obtenerTodosLosPagos() {
        try {
            return pagoDAO.obtenerTodo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener pagos: " + e.getMessage(), 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    // Método para llenar la tabla con todos los pagos
    public void llenarTablaPagos(DefaultTableModel modelo) {
        modelo.setRowCount(0);
        
        List<PagoDto> pagos = obtenerTodosLosPagos();
        if (pagos == null) {
            return;
        }
        
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        
        for (PagoDto pago : pagos) {
            String fecha = sdf.format(pago.getFecha());
            
            modelo.addRow(new Object[]{
                pago.getId_pago(),
                pago.getIdCliente(),
                String.format("%.2f", pago.getSubtotal()),
                String.format("%.2f", pago.getImpuesto()),
                String.format("%.2f", pago.getTotal()),
                pago.getMonto(),
                fecha
            });
        }
    }
}

