/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author kevin
 */
public class PagoDto {
    private final int id_pago;
    private final double subtotal;
    private final double impuesto;
    private final double total;
    private final int monto;
    private final int idCliente;
    private final Date fecha;

    public int getId_pago() {
        return id_pago;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public double getTotal() {
        return total;
    }

    public int getMonto() {
        return monto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public PagoDto(int id_pago, double subtotal, double impuesto, double total, int monto, int idCliente, Date fecha) {
        this.id_pago = id_pago;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
        this.monto = monto;
        this.idCliente = idCliente;
        this.fecha = fecha;
    }
    
}
