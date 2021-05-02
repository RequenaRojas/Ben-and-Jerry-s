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



public class Usuario {
    int id_usu;
    String nom_usu;
    String apelPat_usu;
    String apelMat_usu;
    String fechNaci_usu;
    String domi_usu;
    String telePart;
    String teleCelu;
    int id_tele;
    
    
    public void Usuario(){}
    public void Usuario(String nom_usu, String apelPat_usu, String apelMat_usu, String fechNaci_usu, 
                        String domi_usu, String telePart, String teleCelu){
        
        this.nom_usu = nom_usu;
        this.apelPat_usu = apelPat_usu;
        this.apelMat_usu = apelMat_usu;
        this.fechNaci_usu = fechNaci_usu;
        this.domi_usu = domi_usu;
        this.telePart = telePart;
        this.teleCelu = teleCelu;
        
    }
    
    
    //Metodos getYSet  
    public void setNom(String nom_usu ){
        this.nom_usu = nom_usu;
        
    }
    public String getNom(){
        return this.nom_usu;
    }
    public void setApelPat_usu(String apelPat_usu ){
        this.apelPat_usu = apelPat_usu;
        
    }
    public String getApelPat_usu(){
        return this.apelPat_usu;
    }
    public void setApelMat_usu(String apelMat_usu ){
        this.apelMat_usu = apelMat_usu;
        
    }
    public String getApelMat_usu(){
        return this.apelMat_usu;
    }
    public void setFechNaci_usu(String fechNaci_usu ){
        this.fechNaci_usu = fechNaci_usu;
        
    }
    public String getfechNaci_usu(){
        return this.fechNaci_usu;
    }
    public void setDomi_usu(String domi_usu ){
        this.domi_usu = domi_usu;
        
    }
    public String getDomi_usu(){
        return this.domi_usu;
    }
    public void setTelePart(String telaPart ){
        this.telePart = telaPart;
        
    }
    public String getTelePart(){
        return this.telePart;
    }
    public void setTeleCelu(String teleCelu ){
        this.teleCelu = teleCelu;
        
    }
    public String getTeleCelu(){
        return this.teleCelu;
    }
    
    public void setId(int id){
        this.id_usu = id;
    }
    public void setId_tele(int id){
        this.id_tele = id;
    }
    
    //Necesito conectarme a la BD
    public int getId(ConexionSQL sql)
    throws ServletException, IOException, SQLException{
        return sql.buscarIdUsuarioBD(this.nom_usu, this.apelPat_usu, this.apelMat_usu);
    }
    
    public int getId_tele(ConexionSQL sql)
    throws ServletException, IOException, SQLException{
        int id_tele = sql.buscarIDTelefonoBD(this.telePart);
        return id_tele;
  }
    
    
    
    
    
    
    
    
    
}
