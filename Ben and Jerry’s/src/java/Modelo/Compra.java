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
    int id_usuario;
    Date fecha_compra;
    float total_compra;
    String formapago;
    //Producto[0] y cantidadYSubtotal[1]
    //En  cantidadYSubtotal, cantidad[0] y subtotal[1]
    HashMap<Producto, float[]> productos;
    Boolean completado;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }
    
    

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

    public HashMap<Producto, float[]> getProductos() {
        return productos;
    }

    public void setProductos(HashMap<Producto, float[]> productos) {
        this.productos = productos;
    }
    
    
}
