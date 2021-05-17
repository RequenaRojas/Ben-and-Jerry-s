
package Controlador;

import Modelo.Telefono;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;

public class ActTelefono {
    
    public static int RegiTelefono(Telefono tel)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = ConexionSQL.getConnection();
        
        String q = "insert into telefono (telePart, teleCelu)"
                + " values (?, ?)";
        
        PreparedStatement ps = con.prepareStatement(q);
        
        ps.setString(1, tel.getTelePart());
        ps.setString(2, tel.getTeleCelu());
        
        estatus = ps.executeUpdate();
        System.out.println("Registro Exitoso");
        con.close();
        
        return estatus;
    
    }
    
    
    
    public static int getID(Telefono tel)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int id = 0;
        Connection con = ConexionSQL.getConnection();
        String g = "SELECT * FROM telefono";

        PreparedStatement ps = con.prepareStatement(g);
        
        ResultSet rs = ps.executeQuery(g);

        
        int id_tele = 0;
        while(rs.next()){
                if(tel.getTelePart().equalsIgnoreCase(rs.getString("telePart")) == true
                    && tel.getTeleCelu().equalsIgnoreCase(rs.getString("teleCelu")) == true){
                    id_tele = rs.getInt("id_tele");
                    System.out.println("Se encontro correctamente el id del telefono");
                    return id_tele;
                }
                
        }

        System.out.println("No se pudó encontrar correctamente el id del telefono");
        
        return id_tele;
    }
    /*
    public String buscarTelePartBD(int id_tele)
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM telefono";

        set = con.createStatement();
        rs = set.executeQuery(g);
        
        String telePart = null;

        while(rs.next()){
                if(id_tele == rs.getInt("id_tele")){
                    telePart = rs.getString("telePart");
                }
        }
        return telePart;
        
    }
    
    public String buscarTeleCeluBD(int id_tele)
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM telefono";

        set = con.createStatement();
        rs = set.executeQuery(g);
        
        String telePart = null;

        while(rs.next()){
                if(id_tele == rs.getInt("id_tele")){
                    telePart = rs.getString("teleCelu");
                }
        }
        return telePart;
        
    }
    
        
        
        
        
    }
    */
}
