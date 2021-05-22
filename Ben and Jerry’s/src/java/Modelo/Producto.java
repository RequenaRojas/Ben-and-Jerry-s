package Modelo;

import java.util.HashMap;

/**
 *
 * @author sofo9
 */
public class Producto {
    int id_producto;
    String sabor;
    HashMap<String, Integer> descuento;
    HashMap<String, Integer> cantidad;
    String tamano;
    String presentacion;

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

    public HashMap<String, Integer> getDescuento() {
        return descuento;
    }

    public void setDescuento(HashMap<String, Integer> descuento) {
        this.descuento = descuento;
    }

    public HashMap<String, Integer> getCantidad() {
        return cantidad;
    }

    public void setCantidad(HashMap<String, Integer> cantidad) {
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
