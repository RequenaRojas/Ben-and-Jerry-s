package Clases;

import Clases.Clasificacion;
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




public class Helado extends Clasificacion{
    int id_hela;
    String nom_hela;
    float prec_hela;
    
    public void Helado(){}
    public void Helado(String nom_hela, float prec_hela, int id_clas){
        this.nom_hela = nom_hela;
        this.prec_hela = prec_hela;
        this.id_clas = id_clas;
    }
    
    
    
    public String getNom_hela(){
        return this.nom_hela;
    }
    public void setNom_hela(String nom_hela){
        this.nom_hela = nom_hela;
    }
    
    public float getPrec_hela(){
        return this.prec_hela;
    }
    public void setPrec_hela(float prec_hela){
        this.prec_hela = prec_hela;
    }
    
     public int getId_hela(){
        return this.id_hela;
    }
    public void setId_hela(int id_hela){
        this.id_hela = id_hela;
    }
    
    
    
}
