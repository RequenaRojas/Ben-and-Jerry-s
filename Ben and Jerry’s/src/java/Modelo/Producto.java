package Modelo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sofo9
 */
public class Producto {
    private int id_producto;
    private String sabor;
    private ArrayList descuento;
    private ArrayList cantidad;
    private String tamano;
    private String presentacion;
    Float precio;

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public ArrayList getDescuento() {
        return descuento;
    }

    public void setDescuento(ArrayList descuento) {
        this.descuento = descuento;
    }

    public ArrayList getCantidad() {
        return cantidad;
    }

    public void setCantidad(ArrayList cantidad) {
        this.cantidad = cantidad;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }
    
    
}
