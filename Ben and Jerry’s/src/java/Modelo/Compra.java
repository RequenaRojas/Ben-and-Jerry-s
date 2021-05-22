package Modelo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author sofo9
 */
public class Compra {
    int id_compra;
    Date fecha_compra;
    float total_compra;
    String formapago;
    HashMap<Producto, int[]> productos;

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public float getTotal_compra() {
        return total_compra;
    }

    public void setTotal_compra(float total_compra) {
        this.total_compra = total_compra;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public HashMap<Producto, int[]> getProductos() {
        return productos;
    }

    public void setProductos(HashMap<Producto, int[]> productos) {
        this.productos = productos;
    }
    
    
}
