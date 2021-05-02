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
    
    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    public void Usuario(){
    }
    static public void crearUsuarioBD(String nom_usu, String apelPat_usu, String apelMat_usu, String fechNaci_usu, 
                        String domi_usu, String telePart, String teleCelu, Connection con, Statement set, ResultSet rs)
    throws ServletException, IOException, SQLException{
         
        String q = "insert into telefono (telePart,teleCelu)"
                +" values ('"+telePart+"', '"+teleCelu+"')";

        set.executeUpdate(q);

        String g = "SELECT '"+telePart+"' FROM telefono";

        set = con.createStatement();
        rs = set.executeQuery(g);

        //Consigo el id de la tabla telefono 
        int id_tele = 0;
        while(rs.next()){
                if(telePart == rs.getString("telePart_usu") | teleCelu == rs.getString("teleCelu_usu")){
                    id_tele = rs.getInt("id_tele");
                    break;
                }
        }
        set.close();
        rs.close();
    


        String p = "insert into Usuario (nom_usu, apelPat_usu, apelMat_usu, fechNaci_usu, domi_usu,id_teleCelu )"
                    + "values ('"+nom_usu+"','"+apelPat_usu+"','"+apelMat_usu+"', '"+fechNaci_usu+"','"+domi_usu+"', "+id_tele+" )";

        set.executeUpdate(p);
        
        set.close();
        rs.close();
        
    }
    
    
    public void seleccionarUsuario(String nom_usu, String apelPat_usu, String apelMat_usu, String fechNaci_usu, 
                        String domi_usu, String telePart, String teleCelu, Connection con, Statement set, ResultSet rs){
        
        setNom(nom_usu);
        this.setApelPat_usu(apelPat_usu);
        this.setApelMat_usu(apelMat_usu);
        this.setFechNaci_usu(fechNaci_usu);
        this.setDomi_usu(domi_usu);
        this.setTelaPart(telePart);
        this.setTeleCelu(teleCelu);
        
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
    public void setTelaPart(String telaPart ){
        this.telePart = telaPart;
        
    }
    public String getTelaPart(){
        return this.apelMat_usu;
    }
    public void setTeleCelu(String teleCelu ){
        this.teleCelu = teleCelu;
        
    }
    public String getTeleCelu(){
        return this.teleCelu;
    }
    
    //Metodos get de ids
    
    
    
    
    
    
    
}
