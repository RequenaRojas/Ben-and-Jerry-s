
package Controlador;

import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author sofo9
 */
public class ActCompra {
    
    
    
    
    
    
    
    //CRUD  de Pago, Listo
    public static int getIdFormatoPago(String tipo_formapago) throws SQLException{
        int id_formapago = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select id_formapago from cformadepago where tipo_formapago = ?";

            ps = con.prepareStatement(q);

           ps.setString(1, tipo_formapago);

           rs = ps.executeQuery();
           while(rs.next()){
               id_formapago = rs.getInt("id_formapago");
           }
            System.out.println("Se ha consultado el id del formato de pago: " + tipo_formapago);
        
        }catch(Exception e){
            System.out.println("Error al consultar el id del formato de pago");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_formapago;
    }
    public static String getFormatoPago(int id_formapago) throws SQLException{
        String tipo_formapago = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select tipo_formapago from cformadepago where id_formapago = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_formapago);
        
        rs = ps.executeQuery();
        while(rs.next()){
            tipo_formapago = rs.getString("tipo_formapago");
        }
            System.out.println("Se ha consultado el tipo de formaro con el id: " + id_formapago);
        
        }catch(Exception e){
            System.out.println("Error al consultar el formato de pago");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return tipo_formapago;
    }
    public static List<String> getAllFormatoPago() throws SQLException{
        List<String> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select tipo_formapago from cformadepago";

            ps = con.prepareStatement(q);

           rs = ps.executeQuery();
           while(rs.next()){
               lista.add(rs.getString("tipo_formapago"));
           }
            System.out.println("Se han consultado los formatos de pago");
        
        }catch(Exception e){
            System.out.println("Error al consultar los formatos de pago");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
    }
    
    //CRUD MCompra
    public static int RegistrarCompra(Compra compra ) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Insert into MCompra (tipo_sabor) values (?)";

           ps = con.prepareStatement(q);

           ps.setString(1, productos);

           estatus = ps.executeUpdate();
            System.out.println("Se ha registrado el sabor: " + productos);
        
        }catch(Exception e){
            System.out.println("Error al registrar el sabor");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
        
    }
    public static int ActualizarSabor(int id_sabor, String sabor) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Update  csabor set tipo_sabor = ? where id_sabor = ?";

           ps = con.prepareStatement(q);

           ps.setString(1, sabor);
           ps.setInt(2, id_sabor);

           estatus = ps.executeUpdate();
            System.out.println("Se ha Actualizado el sabor: " + id_sabor);
        
        }catch(Exception e){
            System.out.println("Error al actualizar el sabor");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
    }
    public static int BorrarSabor(int id_sabor) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Delete from csabor where id_sabor = ?";

           ps = con.prepareStatement(q);

           ps.setInt(1, id_sabor);

           estatus = ps.executeUpdate();
            System.out.println("Se ha eliminado el sabor con Id: " + id_sabor);
        
        }catch(Exception e){
            System.out.println("Error al eliminar el sabor");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
        
        
        
    }
    public static int getIdSabor(String sabor) throws SQLException{
        int id_sabor = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select id_sabor from csabor where tipo_sabor = ?";

            ps = con.prepareStatement(q);

           ps.setString(1, sabor);

           rs = ps.executeQuery();
           while(rs.next()){
               id_sabor = rs.getInt("id_sabor");
           }
            System.out.println("Se ha consultado el id del sabor: " + sabor);
        
        }catch(Exception e){
            System.out.println("Error al consultar el id del sabor");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_sabor;
    }
    public static String getSabor(int id_sabor) throws SQLException{
        String sabor = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select tipo_sabor from csabor where id_sabor = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_sabor);
        
        rs = ps.executeQuery();
        while(rs.next()){
            sabor = rs.getString("tipo_sabor");
        }
            System.out.println("Se ha consultado el sabor con el id: " + id_sabor);
        
        }catch(Exception e){
            System.out.println("Error al consultar el sabor");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return sabor;
    }
    public static List<String> getAllSabor() throws SQLException{
        List<String> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select tipo_sabor from csabor";

            ps = con.prepareStatement(q);

           rs = ps.executeQuery();
           while(rs.next()){
               lista.add(rs.getString("tipo_sabor"));
           }
            System.out.println("Se han consultado los sabores");
        
        }catch(Exception e){
            System.out.println("Error al consultar los sabores");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
    }
    
    
    
    
    //CRUD DCompra, Listo
    public static int RegistrarProducto(int id_compra, Producto producto, int cantidad) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Insert into dcompra (id_compra, id_producto, cantidad_dcompra, subtotal_dcompra)"
                   + " values (?, ? ,?, ?)";

           ps = con.prepareStatement(q);

           ps.setInt(1, id_compra);
           ps.setInt(2, producto.getId_producto());
           ps.setInt(3, cantidad);
           float subtotal = producto.getPrecio() * cantidad;
           ps.setFloat(4, subtotal);

           estatus = ps.executeUpdate();
            System.out.println("Se ha registrado el producto en DCompra: " + producto.getSabor());
        
        }catch(Exception e){
            System.out.println("Error al registrar el producto en dcompra");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
        
    }
    public static int BorrarProducto(int id_compra, int id_producto) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Delete from dcompra where id_compra = ? and id_producto = ?";

           ps = con.prepareStatement(q);

           ps.setInt(1, id_compra);
           ps.setInt(2, id_producto);

           estatus = ps.executeUpdate();
            System.out.println("Se ha eliminado el producto en dcompra con Id Compra: " + id_compra + " , Id Producto" + id_producto);
        
        }catch(Exception e){
            System.out.println("Error al eliminar el producto en dcompra");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
        
        
        
    }
    public static HashMap<Producto, float[]> getProductos(int id_compra) throws SQLException{
        HashMap<Producto, float[]> productos =  null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from dcompra where id_compra = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_compra);
        
        rs = ps.executeQuery();
        while(rs.next()){
            Producto producto = ActProducto.getProducto(rs.getInt("id_producto"));
            
            float[] cantidadYSubtotal = new float[2];
            cantidadYSubtotal[0] = rs.getInt("cantidad_dcompra");
            cantidadYSubtotal[1] =  rs.getFloat("subtotal_dcompra");
            
            productos.put(producto, cantidadYSubtotal);
          
        }
        System.out.println("Se ha consultado los productos con el id_compra: " + id_compra);
        
        }catch(Exception e){
            System.out.println("Error al consultar los productos");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return productos;
    }
    
}
