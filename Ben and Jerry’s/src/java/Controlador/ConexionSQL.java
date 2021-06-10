package Controlador;
import Modelo.*;

//Encargada de poder realizar la conexión con la BD
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
//Encargada de poder realizar las sentencias sql, creat, insert, delete, drop, update
import java.sql.Statement;
//Encrgada de poder realizar las consultas a la BD
import java.sql.SQLException;
import javax.servlet.ServletException;

public class ConexionSQL {
    
    public static Connection getConnection()
    throws ServletException, ClassNotFoundException, SQLException{
        
        String url = "jdbc:mysql://wcwimj6zu5aaddlj.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/qb9w2pzeh1k667p7";
        String userName = "c35th5wrxgf5hvqg";
        String password = "gbkg98qh51iinx0i";
        
        Connection con = null;
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, userName, password);
        System.out.println("Conexion exitosa con la BD");
        return con;
        
    }
    
    
    
    
    
    
   
    
    
}