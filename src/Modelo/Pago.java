/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author UTN
 */
public class Pago {
    private int id_pago;
    private double subtotal;
    private double impuesto;
    private double total;
    private int monto;
    private Cliente cliente;
    private Date fecha;

    public int getId_pago() {
        return id_pago;
    }

    public int getMonto() {
        return monto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    private void calcularTotal() {
        this.total = subtotal + impuesto;
    }

    public Pago(int id_pago, double subtotal, double impuesto, Cliente cliente, Date fecha) {
        this.id_pago = id_pago;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.cliente = cliente;
        this.fecha = fecha;
        calcularTotal();
    }
    
}
