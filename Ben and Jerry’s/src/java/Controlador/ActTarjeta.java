package Controlador;
import Modelo.Tarjeta;
import Modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author sofo9
 */
public class ActTarjeta {
    
    public static int RegiTarjeta(Tarjeta tar) throws SQLException, ServletException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "insert into MTarjeta (nombre_titular, num_tarjeta, fecha_exp,"
                + " cod_seguridad, id_tipobanco, id_tipopago)"
                + "values (?,?,?,?,?,?)";
        
         ps = con.prepareStatement(q);
        
        ps.setString(1, tar.getNombre_titular());
        ps.setInt(2, tar.getNum_tarjeta());
        ps.setDate(3, (Date) tar.getFecha_exp());
        ps.setInt(4, tar.getCod_seguridad());
        ps.setInt(5, ActTarjeta.getIdTipoBanco(tar.getTipobanco()));
        ps.setInt(6, ActTarjeta.getIdTipoPago(tar.getTipopago()));
        
        estatus = ps.executeUpdate();
        System.out.println("Registro Exitoso");
        }catch(Exception e){
            System.out.println("Error al registrar la tarjeta");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
        
    
    
    }
    public static Tarjeta getTarjeta(int id_tar) throws SQLException{
        Tarjeta tar = new Tarjeta();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from MTarjeta where id_tarjeta = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_tar);
        
        rs = ps.executeQuery();
        while(rs.next()){
            tar.setId_tarjeta(id_tar);
            tar.setNombre_titular(rs.getString("nombre_titular"));
            tar.setNum_tarjeta(rs.getInt("num_tarjeta"));
            tar.setFecha_exp(rs.getDate("fecha_exp"));
            tar.setCod_seguridad(rs.getInt("cod_seguridad"));
            tar.setTipobanco(ActTarjeta.getTipoBanco(rs.getInt("id_tipobanco")));
            tar.setTipopago(ActTarjeta.getTipoPago(rs.getInt("id_tipopago")));
        }
            System.out.println("Se ha consultado la tajerta con el id: " + id_tar);
        
        }catch(Exception e){
            System.out.println("Error al consultar la tarjeta");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return tar;
        
        
        
    }
    public static int ActualizarTarjeta(int id_tar,Tarjeta tar) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Update MTarjeta SET nombre_titular = ?, num_tarjeta = ?, fecha_exp = ?,"
                + " cod_seguridad = ?, id_tipobanco = ?, id_tipopago = ?  WHERE id_tar = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setString(1, tar.getNombre_titular());
        ps.setInt(2, tar.getNum_tarjeta());
        ps.setDate(3, (Date) tar.getFecha_exp());
        ps.setInt(4, tar.getCod_seguridad());
        ps.setInt(5, ActTarjeta.getIdTipoBanco(tar.getTipobanco()));
        ps.setInt(6, ActTarjeta.getIdTipoPago(tar.getTipopago()));
        ps.setInt(7, id_tar);
        
        estatus = ps.executeUpdate();
        System.out.println("Tarjeta con id: "+id_tar + " Actualizada");
        }catch(Exception e){
            System.out.println("Error al Actualizar la tarjeta");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
    }
    public static int BorrarTarjeta(int id_tar) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "DELETE FROM MTarjeta WHERE id_tar = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_tar);
        
        estatus = ps.executeUpdate();
        System.out.println("Tarjeta con id: "+id_tar + " Eliminada");
        }catch(Exception e){
            System.out.println("Error al Eliminar la tarjeta");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
        
        
        
    }
    
    //CRUD de Catalagos tipos de banco y pago
    public static int getIdTipoBanco(String tipobanco) throws SQLException{
        int id_tipobanco = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select id_tipobanco from CTipobanco where nombre_tipobanco = ?";

            ps = con.prepareStatement(q);

           ps.setString(1, tipobanco);

           rs = ps.executeQuery();
           while(rs.next()){
               id_tipobanco = rs.getInt("id_tipobanco");
           }
            System.out.println("Se ha consultado el id del tipo de banco: " + tipobanco);
        
        }catch(Exception e){
            System.out.println("Error al consultar el id del tipo de banco");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_tipobanco;
    }
    public static String getTipoBanco(int id_tipobanco) throws SQLException{
        String tipobanco = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select nombre_tipobanco from CTipobanco where id_tipobanco = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_tipobanco);
        
        rs = ps.executeQuery();
        while(rs.next()){
            tipobanco = rs.getString("nombre_tipobanco");
        }
            System.out.println("Se ha consultado el tipo de pago con el id: " + id_tipobanco);
        
        }catch(Exception e){
            System.out.println("Error al consultar el tipo de banco");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return tipobanco;
    }
    public static int getIdTipoPago(String tipopago) throws SQLException{
        int id_tipopago = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select id_tipopago from ctipoformadepago where tipo_pago = ?";

            ps = con.prepareStatement(q);

           ps.setString(1, tipopago);

           rs = ps.executeQuery();
           while(rs.next()){
               id_tipopago = rs.getInt("id_tipopago");
           }
            System.out.println("Se ha consultado el id del tipo de pago: " + tipopago);
        
        }catch(Exception e){
            System.out.println("Error al consultar el id del tipo de pago");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_tipopago;
    }
    public static String getTipoPago(int id_tipopago) throws SQLException{
        String tipo_pago = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select tipo_pago from ctipoformadepago where id_pago = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_tipopago);
        
        rs = ps.executeQuery();
        while(rs.next()){
            tipo_pago = rs.getString("tipo_pago");
        }
            System.out.println("Se ha consultado el tipo de pago con el id: " + id_tipopago);
        
        }catch(Exception e){
            System.out.println("Error al consultar el tipo de pago");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return tipo_pago;
    }
    public static List<String> getAllTipoPago() throws SQLException{
        List<String> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select tipo_pago from ctipoformadepago";

            ps = con.prepareStatement(q);

           rs = ps.executeQuery();
           while(rs.next()){
               lista.add(rs.getString("tipo_pago"));
           }
            System.out.println("Se han consultado los tipos de pago");
        
        }catch(Exception e){
            System.out.println("Error al consultar los tipos de pago");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
    }
    public static List<String> getAllTipoBanco() throws SQLException{
        List<String> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select nombre_tipobanco from ctipobanco";

            ps = con.prepareStatement(q);

           rs = ps.executeQuery();
           while(rs.next()){
               lista.add(rs.getString("nombre_tipobanco"));
           }
            System.out.println("Se han consultado los tipos de banco");
        
        }catch(Exception e){
            System.out.println("Error al consultar los tipos de banco");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
    }
    
    
    
}
