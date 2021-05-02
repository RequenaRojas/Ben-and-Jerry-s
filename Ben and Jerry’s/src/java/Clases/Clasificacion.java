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


public class Clasificacion {
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
    
    public void setIDs(ConexionSQL sql)
     throws ServletException, IOException, SQLException{
        
        this.id_tipoHela = sql.buscarIdTipoHeladoBD(this.nom_tipoHela);
        this.id_cant = sql.buscarIdCantidadBD(this.tamaño);
        this.id_pres = sql.buscarIdCantidadBD(this.nom_pres);
        
    }
}
