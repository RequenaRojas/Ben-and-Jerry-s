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
import javax.servlet.ServletException;



public class Usuario {
    int id_usu;
    String nom_usu;
    String apelPat_usu;
    String apelMat_usu;
    Date fechNaci_usu;
    String domi_usu;
    String telaPart;
    String teleCelu;
    int id_tele;
    
    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    
    public void Usuario(String nom_usu, String apelPat_usu, String apelMat_usu, Date fechNaci_usu, 
                        String domi_usu, String telePart, String teleCelu, Connection con, Statement set, ResultSet rs)
    throws ServletException, IOException{
        try{
            
            String q = "insert into telefono (telePart,teleCelu)"
                    +" values ('"+telePart+"', '"+teleCelu+"')";
        
            set.executeUpdate(q);
            
            String g = "SELECT '"+telePart+"' FROM telefono";
            
            set = con.createStatement();
            rs = set.executeQuery(g);
            
            //Consigo el id de la tabla telefono que creee para 
            int id_tele;
            while(rs.next()){
                    if(telePart == rs.getString("telePart_usu") | teleCelu == rs.getString("teleCelu_usu")){
                        id_tele = rs.getInt("id_tele");
                    }
                }
            
            
            String p = "insert into Usuario (nom_usu, apelPat_usu, apelMat_usu, fechNaci_usu, domi_usu,teleCelu )"
                        + "values ('"+nom_usu+"','"+apelPat_usu+"','"+apelMat_usu+"', '"+fechNaci_usu+"','"+domi_usu+"', "+telePart+", '"+teleCelu+"' )";
                
                set.executeUpdate(p);
        }catch(Exception e){
            
            
        }
        
        
        
    }
}
