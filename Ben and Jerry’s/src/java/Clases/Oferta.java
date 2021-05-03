package Clases;

import java.io.IOException;
import java.util.Date;


//Encargada de poder realizar la conexión con la BD
import java.sql.Connection;
import java.sql.DriverManager;
//Encargada de poder reaalizar las sentencias sql, creat, insert, delete, drop, update
import java.sql.Statement;
//Encrgada de poder realizar las consultas a la BD
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
public class Oferta {
    
    int id_ofer;
    String nom_ofer;
    int porc_ofer;
    int id_clas;
    int id_admi;
    public void Oferta(){}
    public void Oferta(String nom_ofer, int porc_ofer){
        this.nom_ofer = nom_ofer;
        this.porc_ofer = porc_ofer;
    }
    public void Oferta(int id_ofer, int id_clas,int id_admi){
        this.id_ofer = id_ofer;
        this.id_clas = id_clas;
        this.id_admi = id_admi;
        
    }
    
    public void setAtributos(int id_ofer, ConexionSQL sql)
     throws ServletException, IOException, SQLException{
        Oferta ofer = new Oferta();
        
        ofer = sql.buscarOferta(id_ofer);
        
        this.nom_ofer = ofer.getNom_ofer();
        this.porc_ofer = ofer.getPorc_ofer();
        this.id_clas = ofer.getId_clas();
        this.id_admi = ofer.getId_admi();
        
        

        
        
    }
    
    public void setId_ofer(int id_ofer){
        this.id_ofer = id_ofer;
    }
    public int getId_ofer(){
        return this.id_ofer;
    }
    public void setPor_ofer(int porc_ofer){
        this.porc_ofer = porc_ofer;
    }
    public int getPorc_ofer(){
        return this.porc_ofer;
    }
    public void setId_admi(int id_admi){
        this.id_admi = id_admi;
    }
    public int getId_admi(){
        return this.id_admi;
    }
    public void setNom_ofer(String nom_ofer){
        this.nom_ofer = nom_ofer;
    }
    public String getNom_ofer(){
        return this.nom_ofer;
    }
    public void setId_clas(int id_clas){
        this.id_clas = id_clas;
    }
    public int getId_clas(){
        return id_clas;
    }
    
}
