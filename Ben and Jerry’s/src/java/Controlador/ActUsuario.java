
package Controlador;

import Modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;


public class ActUsuario {
    
    public static int RegiUsuario(Usuario usu)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        
        Connection con = ConexionSQL.getConnection();
        
        String q = "insert into Usuario (nom_usu, apelPat_usu, apelMat_usu, fechNaci_usu, domi_usu,id_tele )"
                + "values (?,?,?,?,?,?)";
        
        PreparedStatement ps = con.prepareStatement(q);
        
        ps.setString(1, usu.getNom());
        ps.setString(2, usu.getApelPat_usu());
        ps.setString(3, usu.getApelMat_usu());
        ps.setString(4, usu.getfechNaci_usu());
        ps.setString(5, usu.getDomi_usu());
        ps.setInt(6, usu.getId_tele());
        
        estatus = ps.executeUpdate();
        System.out.println("Registro Exitoso");
        con.close();
        
        return estatus;
        

        
            
    }
    
    
    
    /*
    public int buscarIdUsuarioBD(String nombre, String apelPat, String apelMat)
    throws ServletException, IOException, SQLException{
        String g = "SELECT * FROM usuario";

        set = con.createStatement();
        rs = set.executeQuery(g);

        
        int id_usu = 0;
        while(rs.next()){
                if(nombre.equalsIgnoreCase(rs.getString("nom_usu")) == true && 
                    apelPat.equalsIgnoreCase(rs.getString("apelPat_usu")) == true &&
                    apelMat.equalsIgnoreCase(rs.getString("apelMat_usu")) == true ){
                        id_usu = rs.getInt("id_usu");
                        return id_usu;
                }
                
        }
        return id_usu;
                
    }
    
    public Usuario buscarUsuarioBD(int id_usu)
    throws ServletException, IOException, SQLException{
        Usuario usu = new Usuario();
        
        String g = "SELECT * FROM Usuario";

        set = con.createStatement();
        rs = set.executeQuery(g);
        
        
        
        while(rs.next()){
                if(id_usu == rs.getInt("id_usu")){
                    usu.setNom(rs.getString("nom_usu"));
                    usu.setApelPat_usu(rs.getString("apelPat_usu"));
                    usu.setApelMat_usu(rs.getString("apelMat_usu"));
                    usu.setFechNaci_usu(rs.getString("fechNaci_usu"));
                    usu.setDomi_usu(rs.getString("domi_usu"));
                    usu.setId(rs.getInt("id_usu"));
                    usu.setId_tele(rs.getInt("id_tele"));
                    
                   int id_tele = rs.getInt("id_tele");
                    
                    usu.setTelePart(this.buscarTelePartBD(id_tele));
                    usu.setTeleCelu(this.buscarTeleCeluBD(id_tele));
                    return usu;
                }
                else{
                    usu = null;
                }
        }
        return usu;
    }
    
    public void editarUsuario(int id_usu, Usuario usu)
     throws ServletException, IOException, SQLException{
        
        
        String p =  "Update telefono set telePart = '"+ usu.getTelePart()+"', teleCelu = '"+usu.getTeleCelu()+"' where id_tele = "+ 
                    usu.getId_tele()+" ";
        
        set.executeUpdate(p);
        
         String q = "update Usuario set nom_usu='"+usu.nom_usu+"', apelPat_usu='"+usu.getApelPat_usu()+"', apelMat_usu='"+usu.getApelMat_usu()+"'"
                        + ", fechNaci_usu='"+usu.getfechNaci_usu()+"', domi_usu='"+usu.getDomi_usu()+"' where id_usu = "+id_usu+"  ";
        
        set.executeUpdate(q);
    }
    
    public void eliminarUsuario(int id_usu)
    throws ServletException, IOException, SQLException{
        
        Usuario usu = new Usuario();
        usu = this.buscarUsuarioBD(id_usu);
        
        String g = "delete from Telefono where id_tele = "+usu.getId_tele();
        set.executeUpdate(g);
        
        
        System.out.println("Despues de executeUpdate");
        
        String q = "delete from Usuario where id_usu = "+id_usu;
                
        set.executeUpdate(q);
        System.out.println("Registro eliminado con exito");
    }
    */
     
    
}
