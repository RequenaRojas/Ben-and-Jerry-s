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


public class Clasificacion  {
    int id_clas;
    
    String nom_tipoHela;
    int id_tipoHela;
    
    String tamaño;
    float gramos;
    int id_cant;
    
    String nom_pres;
    int id_pres;
    
    public void Clasificacion(){}
    
    public void Clasificacion(String nom_tipoHela, String tamaño, String nom_pres){
        this.id_tipoHela = id_tipoHela;
        this.tamaño = tamaño;
        this.nom_pres = nom_pres;
        
    }
    
    public void setAtributos(int id_clas, ConexionSQL sql)
     throws ServletException, IOException, SQLException{
        Clasificacion clas = new Clasificacion();
        
        clas = sql.buscarClasificacionBD(id_clas);
        
        this.nom_tipoHela = sql.buscarTipoHeladoBD(clas.getId_tipoHela());
        this.tamaño = sql.buscarCantidadBD(clas.getId_cant());
        this.gramos = sql.buscarCantidadGramosBD(clas.getId_cant());
        this.nom_pres = sql.buscarPresentacionBD(clas.getId_pres());
        
        
    }
    
    public int getId_clas(){
        return this.id_clas;
    }
    public String getNom_tipoHela(){
        return this.nom_tipoHela;
    }
    public int getId_tipoHela(){
        return this.id_tipoHela;
    }
    public String getTamaño(){
        return this.tamaño;
    }
    public float getGramos(){
        return this.gramos;
    }
    public int getId_cant(){
        return this.id_cant;
    }
    public String getNom_pres(){
        return this.nom_pres;
    }
    public int getId_pres(){
        return this.id_pres;
    }
    
    public void setNom_tipoHela(String nom_tipoHela){
        this.nom_tipoHela = nom_tipoHela;   
    }
    public void setId_tipoHela(int id_tipoHela){
        this.id_tipoHela = id_tipoHela;   
    }
    public void setTamaño(String tamaño){
        this.tamaño = tamaño;   
    }
    public void setGramos(float gramos){
        this.gramos = gramos;
        
    }
    public void setId_cant(int id_cant){
        this.id_cant = id_cant;   
    }
    public void setNom_pres(String nom_pres){
        this.nom_pres = nom_pres;   
    }
    public void setId_pres(int id_pres){
        this.id_pres = id_pres;
        
    }
    
    public void setId_clas(int id_clas){
        this.id_clas = id_clas;
        
    }
    
    
    

    
    
}
