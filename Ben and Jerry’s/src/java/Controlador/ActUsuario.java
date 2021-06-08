package Controlador;

import Modelo.Dirrecion;
import Modelo.Tarjeta;
import Modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;


public class ActUsuario {
    
    public static int RegiUsuario(Usuario usu)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "insert into MUsuario (nom_usuario, appat_usuario, apemat_usuario,"
                    + " fechNaci_usuario, tel_usuario, cel_usuario, user_usuario, pass_usuario, "
                    + "id_dirrec_usuario, id_tarjeta, privilegio )"
                    + "values (?,?,?,?,?,?,?)";

            ps = con.prepareStatement(q);

            ps.setString(1, usu.getNom_usuario());
            ps.setString(2, usu.getApelPat_usurio());
            ps.setString(3, usu.getApelMat_usurio());
            ps.setDate(4, (Date) usu.getFechNaci_usurio());
            ps.setInt(5, usu.getTel_usuario());
            ps.setInt(6, usu.getCel_usuario());
            ps.setString(7, usu.getUser_usuario());
            ps.setString(8, usu.getPass_usuario());
            ps.setInt(9, usu.getDirrec_usuario().getId_dirrec());
            ps.setInt(10, usu.getTarjeta_usuario().getId_tarjeta());
            ps.setInt(11, usu.getPrivilegio());

            estatus = ps.executeUpdate();
            System.out.println("Registro del Usuario Exitoso: "+usu.getNom_usuario());
            

            
        }catch(Exception e){
            System.out.println("Error al registrar al Usuario");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }
    
    public static Usuario getUsuario (int id_usuario) throws SQLException{
        Usuario usu = new Usuario();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from MUsuario where id_usuario = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_usuario);
        
        rs = ps.executeQuery();
        while(rs.next()){
            usu.setId_usuario(id_usuario);
            usu.setNom_usuario(rs.getString("nombre_usuario"));
            usu.setApelPat_usurio(rs.getString("apppat_usuario"));
            usu.setApelMat_usurio(rs.getString("apmat_usuario"));
            usu.setFechNaci_usurio(rs.getDate("fecnaci_usuario"));
            usu.setTel_usuario(rs.getInt("tel_usuario"));
            usu.setCel_usuario(rs.getInt("cel_usuario"));
            usu.setUser_usuario(rs.getString("user_usuario"));
            usu.setPass_usuario(rs.getString("pass_usuario"));
            usu.setPrivilegio(rs.getInt("privilegio"));
            
            Dirrecion dir = ActDirrecion.getDirrecion(rs.getInt("id_dirrec_usuario"));
            usu.setDirrec_usuario(dir);
            
            Tarjeta tar = ActTarjeta.getTarjeta(rs.getInt("id_tarjeta"));
            usu.setTarjeta_usuario(tar);
            
        }
            System.out.println("Se ha consultado al usuario con el id: " + id_usuario);
        
        }catch(Exception e){
            System.out.println("Error al consultar al usuario");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return usu;
        
        
        
    }
    
    public static int ActualizarUsuario(int id_usuario,Usuario usu) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Update musuario SET nombre_usuario = ?, apppat_usuario = ?, apmat_usuario = ?"
                + ",  fecnaci_usuario = ?, tel_usuario = ?, cel_usuario = ?, user_usuario = ?,"
                + " pass_usuario = ?, id_dirrec_usuario = ?, id_tarjeta = ?, privilegio = ?  WHERE id_usuario = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setString(1, usu.getNom_usuario());
        ps.setString(2, usu.getApelPat_usurio());
        ps.setString(3, usu.getApelMat_usurio());
        ps.setDate(4, (Date) usu.getFechNaci_usurio());
        ps.setInt(5,usu.getTel_usuario());
        ps.setInt(6, usu.getCel_usuario());
        ps.setString(7, usu.getUser_usuario());
        ps.setString(8, usu.getPass_usuario());
        ps.setInt(9, usu.getDirrec_usuario().getId_dirrec());
        ps.setInt(10, usu.getTarjeta_usuario().getId_tarjeta());
        ps.setInt(11, usu.getPrivilegio());
        ps.setInt(12, id_usuario);
        
        estatus = ps.executeUpdate();
        System.out.println("Usuario con id: "+id_usuario + " Actualizado");
        }catch(Exception e){
            System.out.println("Error al Actualizar al Usuario");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
    }
    
    public static int BorrarUsuario(int id_usuario) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "DELETE FROM Musuario WHERE id_usuario = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_usuario);
        
        estatus = ps.executeUpdate();
        System.out.println("Usuario con id: "+id_usuario + " Eliminado");
        }catch(Exception e){
            System.out.println("Error al Eliminar al Usuario");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
             
    }
       
}