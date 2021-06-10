package Modelo;

import java.util.Date;

/**
 *
 * @author sofo9
 */
public class Tarjeta {
    int id_tarjeta;
    String nombre_titular;
    int num_tarjeta;
    Date fecha_exp;
    int cod_seguridad;
    String tipobanco;
    String tipopago;

    public int getId_tarjeta() {
        return id_tarjeta;
    }

    public void setId_tarjeta(int id_tarjeta) {
        this.id_tarjeta = id_tarjeta;
    }

    public String getNombre_titular() {
        return nombre_titular;
    }

    public void setNombre_titular(String nombre_titular) {
        this.nombre_titular = nombre_titular;
    }

    public int getNum_tarjeta() {
        return num_tarjeta;
    }

    public void setNum_tarjeta(int num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public Date getFecha_exp() {
        return fecha_exp;
    }

    public void setFecha_exp(Date fecha_exp) {
        this.fecha_exp = fecha_exp;
    }

    public int getCod_seguridad() {
        return cod_seguridad;
    }

    public void setCod_seguridad(int cod_seguridad) {
        this.cod_seguridad = cod_seguridad;
    }

    public String getTipobanco() {
        return tipobanco;
    }

    public void setTipobanco(String tipobanco) {
        this.tipobanco = tipobanco;
    }

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }
    
    
    
    
}