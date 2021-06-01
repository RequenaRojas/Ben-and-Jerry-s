
package Controlador;

import Modelo.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sofo9
 */
public class ActCompra {
    
    
    
    //CRUD MCompra
    public static int RegistrarCompra(Compra compra ) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
           con = ConexionSQL.getConnection();   

           String q = "Insert into MCompra (fecha_compra, total_compra, id_formapago, completado, id_usuario) "
                   + "values (?,?,?,?)";

           ps = con.prepareStatement(q);

           ps.setDate(1, (Date) compra.getFecha_compra());
           ps.setFloat(2, compra.getTotal_compra());
           ps.setInt(3, ActCompra.getIdFormatoPago(compra.getFormapago()));
           ps.setBoolean(4, compra.getCompletado());
           ps.setInt(5, compra.getId_usuario());
            
           estatus = ps.executeUpdate();
           System.out.println("Se ha registrado la compra En MCompra");
           ps.close();
           
           try{
               compra.getProductos().forEach(
                    (producto, cantidadYSubtotal) -> {
                        float cantidad = cantidadYSubtotal[0];
                        Producto prod = producto;
                        //necesito el Id de la compra        
               });
  
           }catch(Exception e){
               System.out.println("Error al registrar los Productos");
               System.out.println(e.getMessage()); 
           }
        
        }catch(Exception e){
            System.out.println("Error al registrar la compra");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
        
    }
    public static int ActualizarCompra(int id_compra, Compra compra) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Update  mcompra set fecha_compra = ?, total_compra = ?, id_formapago = ?, completado = ?"
                   + " where id_compra = ?";

           ps = con.prepareStatement(q);

           ps.setDate(1, (Date)compra.getFecha_compra());
           ps.setFloat(2, compra.getTotal_compra());
           ps.setInt(3, ActCompra.getIdFormatoPago(compra.getFormapago()));
           ps.setBoolean(4, compra.getCompletado());
           ps.setInt(5, compra.getId_compra());

           estatus = ps.executeUpdate();
            System.out.println("Se ha Actualizado el Mcompra: " + id_compra);
            ps.close();
            try{
                q = "Delete from dcompra where id_compra = ? ";
                
                ps = con.prepareStatement(q);
                ps.setInt(1, id_compra);
                ps.executeUpdate();
                
                compra.getProductos().forEach((producto, cantidadYSubtotal) -> {
                    Producto pro = producto;
                    float[] cantSubto = cantidadYSubtotal;
                    int cantidad = (int) Math.round(cantSubto[0]);
                    
                    try {
                        ActCompra.RegistrarProducto(id_compra, pro, cantidad);
                    } catch (SQLException ex) {
                        Logger.getLogger(ActCompra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                
                
                
                
                
                });
                
                
            }catch(Exception e){
                
            }finally{
                ps.close();
                rs.close();
                
            }
        
        }catch(Exception e){
            System.out.println("Error al actualizar el Mcompra");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
    }
    public static int getIdCompraActual(int id_usuario) throws SQLException{
        int id_compra = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select id_compra from mcompra where id_usuario = ? and completado = false";

            ps = con.prepareStatement(q);

           ps.setInt(1, id_usuario);

           rs = ps.executeQuery();
           while(rs.next()){
               id_compra = rs.getInt("id_compra");
           }
            System.out.println("Se ha consultado el id de la compra Actual con id_usuario: " + id_usuario);
        
        }catch(Exception e){
            System.out.println("Error al consultar el id de la compra");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_compra;
    }
    public static Compra getCompra(int id_compra) throws SQLException{
        Compra compra = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from mcompra where id_compra = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_compra);
        
        rs = ps.executeQuery();
        while(rs.next()){
            compra.setId_compra(id_compra);
            compra.setId_usuario(rs.getInt("id_usuario"));
            compra.setFecha_compra(rs.getDate("fecha_compra"));
            compra.setFormapago(ActCompra.getFormatoPago(rs.getInt("id_formapago")));
            compra.setCompletado(rs.getBoolean("completado"));
            compra.setProductos(ActCompra.getProductos(id_compra));
            compra.setTotal_compra(rs.getFloat("total_compra"));
        }
       
            System.out.println("Se ha consultado el sabor con el id: " + id_compra);
        
        }catch(Exception e){
            System.out.println("Error al consultar el sabor");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return compra;
    }
    public static List<Compra> getAllCompras(int id_usuario) throws SQLException{
        List<Compra> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select * from mcompra where id_usuario = ?";

            ps = con.prepareStatement(q);

           rs = ps.executeQuery();
           while(rs.next()){
               Compra compra = new Compra();
               compra.setId_compra(rs.getInt("id_compra"));
               compra.setFecha_compra(rs.getDate("fecha_compra"));
               compra.setFormapago(ActCompra.getFormatoPago(rs.getInt("id_formapago")));
               compra.setId_usuario(id_usuario);
               compra.setCompletado(rs.getBoolean("completado"));
               compra.setTotal_compra(rs.getFloat("total_compra"));
               
               compra.setProductos(ActCompra.getProductos(rs.getInt("id_compra")));
               
               lista.add(compra);
           }
            System.out.println("Se han consultado las compras");
        
        }catch(Exception e){
            System.out.println("Error al consultar las compras");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
    }
    
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
