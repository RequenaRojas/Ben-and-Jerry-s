package Clases;

//Encargada de poder realizar la conexión con la BD
import java.sql.Connection;
import java.sql.DriverManager;
//Encargada de poder reaalizar las sentencias sql, creat, insert, delete, drop, update
import java.sql.Statement;
//Encrgada de poder realizar las consultas a la BD
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class ConexionSQL {
    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    public void ConexionSQL(){
        
    }

    /**
     *
     * @param con
     * @param set
     * @param rs
     */
    static void init(Connection con,Statement set,ResultSet rs) throws ServletException, ClassNotFoundException, SQLException{
        
        
        Class.forName("com.mysql.jdbc.Driver");
        
        String url = "jdbc:mysql://c35th5wrxgf5hvqg:gbkg98qh51iinx0i@wcwimj6zu5aaddlj.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/qb9w2pzeh1k667p7\n" +
                     "://localhost/registro4iv7";
                    //driver:gestorBD:puerto//DirecciónIP/nombredelaBD
                    //jdbc:postgresql://THE_HOST/THE_DATABASE
        
        String userName = "c35th5wrxgf5hvqg";
        String password = "gbkg98qh51iinx0i";
        
        
           
        Class.forName("com.mysql.jdbc.Driver");

        /* 
            A veces el Driver ya maneja por defecto el puerto de comunicación, es por ello que puede
            mandar un error, en ese caso

            url = "jdbc:mysql://localhost/registro4iv7";
        */

        con = DriverManager.getConnection(url, userName, password);
        set = con.createStatement();
        
    }
    
    public Connection getCon(){
        return con;
    }
    public Statement getSet(){
        return set;
    }
    public ResultSet getRs(){
        return rs;
    }
    
    
    
    
}