/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author sofo9
 */
public class ActProducto {
    public static int RegiProducto(Producto producto)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "insert into MProducto (id_sabor, id_descuento, id_cantidad,"
                    + " id_tamano, id_presentacion, precio_producto)"
                    + "values (?,?,?,?,?,?)";

            ps = con.prepareStatement(q);

            ps.setInt(1, ActProducto.getIdSabor(producto.getSabor()));
            ps.setInt(2, ActProducto.getIdDescuento( (String)producto.getDescuento().get(0)));
            ps.setInt(3, ActProducto.getIdCantidad( producto.getCantidad()));
            ps.setInt(4, ActProducto.getIdTamano(producto.getTamano()));
            ps.setInt(5, ActProducto.getIdPresentacion(producto.getPresentacion()));
            ps.setFloat(6, producto.getPrecio());

            estatus = ps.executeUpdate();
            System.out.println("Registro del Producto Exitoso: "+producto.getSabor() + ", " + producto.getTamano()+ ", " + producto.getPresentacion());
            

            
        }catch(Exception e){
            System.out.println("Error al registrar al Producto");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }
    
    public static Producto getProducto (int id_producto) throws SQLException{
        Producto producto = new Producto();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from MProducto where id_producto = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_producto);
        
        rs = ps.executeQuery();
        while(rs.next()){
            producto.setId_producto(id_producto);
            producto.setSabor(ActProducto.getSabor(rs.getInt("id_sabor")));
            producto.setTamano(ActProducto.getTamano(rs.getInt("id_tamano")));
            producto.setPresentacion(ActProducto.getPresentacion(rs.getInt("id_presentacion")));
            producto.setPrecio(rs.getFloat("precio_producto"));
            
            ArrayList descuento = ActProducto.getDescuento(rs.getInt("id_descuento"));
            producto.setDescuento(descuento);
            
            ArrayList cantidad = ActProducto.getCantidad(rs.getInt("id_cantidad"));
            producto.setCantidad(cantidad);
            
        }
            System.out.println("Se ha consultado al Producto con el id: " + id_producto);
        
        }catch(Exception e){
            System.out.println("Error al consultar al Producto");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return producto;
        
        
        
    }
    
    public static int ActualizarProducto(int id_producto,Producto producto) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Update MProducto SET id_sabor = ?, id_descuento = ?, id_cantidad = ?"
                + ",  id_tamano = ?, id_presentacion = ?, precio_producto = ? WHERE id_producto = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, ActProducto.getIdSabor(producto.getSabor()));
        ps.setInt(2, ActProducto.getIdDescuento( (String)producto.getDescuento().get(0)));
        ps.setInt(3, ActProducto.getIdCantidad( producto.getCantidad()));
        ps.setInt(4, ActProducto.getIdTamano(producto.getTamano()));
        ps.setInt(5, ActProducto.getIdPresentacion(producto.getPresentacion()));
        ps.setFloat(6, producto.getPrecio());
        ps.setInt(7, id_producto);
        
        estatus = ps.executeUpdate();
        System.out.println("Producto con id: "+id_producto + " Actualizado");
        }catch(Exception e){
            System.out.println("Error al Actualizar al Producto");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
    }
    
    public static int BorrarProducto(int id_producto) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "DELETE FROM MProducto WHERE id_producto = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_producto);
        
        estatus = ps.executeUpdate();
        System.out.println("Producto con id: "+id_producto + " Eliminado");
        }catch(Exception e){
            System.out.println("Error al Eliminar al Producto");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
        
        
        
    }
    
    
    
    
    
    //CRUD de Sabor 
    public static int RegiSabor(String sabor) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Insert into csabor (tipo_sabor) values (?)";

           ps = con.prepareStatement(q);

           ps.setString(1, sabor);

           estatus = ps.executeUpdate();
            System.out.println("Se ha registrado el sabor: " + sabor);
        
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
    
   
    //CRUD de Descuento
    //ArrayList con tipo_descuento[0] y porcentaje_descuento[1]
    public static int RegiDescuento(ArrayList tipo_porcentaje) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Insert into cdescuento (tipo_descuento, porcentaje_descuento) values (?,?)";

           ps = con.prepareStatement(q);

           ps.setString(1, (String) tipo_porcentaje.get(0));
           ps.setInt(2, (Integer)tipo_porcentaje.get(1));

           estatus = ps.executeUpdate();
            System.out.println("Se ha registrado el Descuento: " + tipo_porcentaje.get(0));
        
        }catch(Exception e){
            System.out.println("Error al registrar el descuento");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
        
    }
    public static int ActualizarDescuento(int id_descuento, ArrayList tipo_porcentaje) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Update  cdescuento set tipo_descuento = ?, porcentaje_descuento = ? where id_descuento = ?";

           ps = con.prepareStatement(q);

           ps.setString(1, (String)tipo_porcentaje.get(0));
           ps.setInt(2, (Integer)tipo_porcentaje.get(1));
           ps.setInt(3, id_descuento);

           estatus = ps.executeUpdate();
            System.out.println("Se ha Actualizado el descuento con el Id: " + id_descuento);
        
        }catch(Exception e){
            System.out.println("Error al actualizar el descuento");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
    }
    public static int BorrarDescuento(int id_descuento) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Delete from cdescuento where id_descuento = ?";

           ps = con.prepareStatement(q);

           ps.setInt(1, id_descuento);

           estatus = ps.executeUpdate();
            System.out.println("Se ha eliminado el descuento con Id: " + id_descuento);
        
        }catch(Exception e){
            System.out.println("Error al eliminar el descuento");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
        
        
        
    }
    public static int getIdDescuento(String tipo_descuento) throws SQLException{
        int id_descuento = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select id_descuento from cdescuento where tipo_descuento = ?";

            ps = con.prepareStatement(q);

           ps.setString(1, tipo_descuento);

           rs = ps.executeQuery();
           while(rs.next()){
               id_descuento = rs.getInt("id_descuento");
           }
            System.out.println("Se ha consultado el id del descuento: " + tipo_descuento);
        
        }catch(Exception e){
            System.out.println("Error al consultar el id del descuento");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_descuento;
    }
    public static ArrayList getDescuento(int id_descuento) throws SQLException{
        ArrayList tipo_porcentaje = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from cdescuento where id_descuento = ?";
        
        ps = con.prepareStatement(q);
        
        ps.setInt(1, id_descuento);
        
        rs = ps.executeQuery();
        while(rs.next()){
            tipo_porcentaje.add(rs.getString("tipo_sabor"));
            tipo_porcentaje.add(rs.getInt("porcentaje_descuento"));
        }
            System.out.println("Se ha consultado el descuento con el id: " + id_descuento);
        
        }catch(Exception e){
            System.out.println("Error al consultar el descuento");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return tipo_porcentaje;
    }
    public static List<ArrayList> getAllDescuento() throws SQLException{
        List<ArrayList> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select * from cdescuento";

            ps = con.prepareStatement(q);

           rs = ps.executeQuery();
           while(rs.next()){
               ArrayList descuento = new ArrayList();
               descuento.add(rs.getString("tipo_descuento"));
               descuento.add(rs.getInt("porcentaje_descuento"));
               lista.add(descuento);
           }
            System.out.println("Se han consultado los descuentos");
        
        }catch(Exception e){
            System.out.println("Error al consultar los descuentos");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
    }
    
    //CRUD de Cantidad
    //ArrayList con valor_cantidad[0] y unidad_cantidad[1]
    public static int RegiCantidad(ArrayList valorYUnidad) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Insert into ccantidad (valor_cantidad, unidad_cantidad) values (?,?)";

           ps = con.prepareStatement(q);

           ps.setFloat(1, (Integer) valorYUnidad.get(0));
           ps.setString(2, (String)valorYUnidad.get(1));

           estatus = ps.executeUpdate();
            System.out.println("Se ha registrado la Cantidad: " + valorYUnidad.get(0) + ", " + valorYUnidad.get(1));
        
        }catch(Exception e){
            System.out.println("Error al registrar la Cantidad");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
        
    }
    public static int ActualizarCantidad(int id_cantidad, ArrayList valorYUnidad) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Update  ccantidad set valor_cantidad = ?, unidad_cantidad = ? where id_cantidad = ?";

           ps = con.prepareStatement(q);

           ps.setFloat(1, (Integer)valorYUnidad.get(0));
           ps.setString(2, (String)valorYUnidad.get(1));
           ps.setInt(3, id_cantidad);

           estatus = ps.executeUpdate();
            System.out.println("Se ha Actualizado la Cantidad con el Id: " + id_cantidad);
        
        }catch(Exception e){
            System.out.println("Error al actualizar la Cantidad");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
    }
    public static int BorrarCantidad(int id_cantidad) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Delete from ccantidad where id_cantidad = ?";

           ps = con.prepareStatement(q);

           ps.setInt(1, id_cantidad);

           estatus = ps.executeUpdate();
            System.out.println("Se ha eliminado la Cantidad con Id: " + id_cantidad);
        
        }catch(Exception e){
            System.out.println("Error al eliminar la Cantidad");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
        
        
        
    }
    public static int getIdCantidad(ArrayList valorYUnidad) throws SQLException{
        int id_cantidad = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select id_cantidad from ccantidad where valor_cantidad = ? and unidad_cantidad = ?";

            ps = con.prepareStatement(q);

            ps.setFloat(1, (Float) valorYUnidad.get(0));
           ps.setString(1, (String) valorYUnidad.get(1));
           

           rs = ps.executeQuery();
           while(rs.next()){
               id_cantidad = rs.getInt("id_cantidad");
           }
            System.out.println("Se ha consultado el id de la cantidad: " + valorYUnidad.get(0) + ", "+ valorYUnidad.get(1));
        
        }catch(Exception e){
            System.out.println("Error al consultar el id de la cantidad");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_cantidad;
    }
    public static ArrayList getCantidad(int id_cantidad) throws SQLException{
        ArrayList valorYUnidad = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from ccantidad where id_cantidad = ?";
        
        ps = con.prepareStatement(q);
        
        ps.setInt(1, id_cantidad);
        
        rs = ps.executeQuery();
        while(rs.next()){
            valorYUnidad.add(rs.getFloat("valor_cantidad"));
            valorYUnidad.add(rs.getString("unidad_cantidad"));
        }
            System.out.println("Se ha consultado la cantidad con el id: " + id_cantidad);
        
        }catch(Exception e){
            System.out.println("Error al consultar la cantidad");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return valorYUnidad;
    }
    public static List<ArrayList> getAllCantidad() throws SQLException{
        List<ArrayList> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select * from ccantidad";

            ps = con.prepareStatement(q);

           rs = ps.executeQuery();
           while(rs.next()){
               ArrayList cantidad = new ArrayList();
               cantidad.add(rs.getFloat("valor_cantidad"));
               cantidad.add(rs.getFloat("unidad_cantidad"));
               lista.add(cantidad);
           }
            System.out.println("Se han consultado las cantidades");
        
        }catch(Exception e){
            System.out.println("Error al consultar las cantidades");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
    }
    
    //CRUD de Tamaño
    public static int RegiTamano(String tipo_tamano) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Insert into ctamano (tipo_tamano) values (?)";

           ps = con.prepareStatement(q);

           ps.setString(1, tipo_tamano);

           estatus = ps.executeUpdate();
            System.out.println("Se ha registrado el tamaño: " + tipo_tamano);
        
        }catch(Exception e){
            System.out.println("Error al registrar el tamaño");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
        
    }
    public static int ActualizarTamano(int id_tamano, String tipo_tamano) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Update  ctamano set tipo_tamano = ? where id_tamano = ?";

           ps = con.prepareStatement(q);

           ps.setString(1, tipo_tamano);
           ps.setInt(2, id_tamano);

           estatus = ps.executeUpdate();
            System.out.println("Se ha Actualizado el tamaño con Id: " + id_tamano);
        
        }catch(Exception e){
            System.out.println("Error al actualizar el tamaño");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
    }
    public static int BorrarTamano(int id_tamano) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Delete from ctamano where id_tamano = ?";

           ps = con.prepareStatement(q);

           ps.setInt(1, id_tamano);

           estatus = ps.executeUpdate();
            System.out.println("Se ha eliminado el tamaño con Id: " + id_tamano);
        
        }catch(Exception e){
            System.out.println("Error al eliminar el tamaño");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
        
        
        
    }
    public static int getIdTamano(String tipo_tamano) throws SQLException{
        int id_tamano = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select id_tamano from ctamano where tipo_tamano = ?";

            ps = con.prepareStatement(q);

           ps.setString(1, tipo_tamano);

           rs = ps.executeQuery();
           while(rs.next()){
               id_tamano = rs.getInt("id_tamano");
           }
            System.out.println("Se ha consultado el id del tamaño: " + tipo_tamano);
        
        }catch(Exception e){
            System.out.println("Error al consultar el id del tamaño");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_tamano;
    }
    public static String getTamano(int id_tamano) throws SQLException{
        String tamano = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select tipo_tamano from ctamano where id_tamano = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_tamano);
        
        rs = ps.executeQuery();
        while(rs.next()){
            tamano = rs.getString("tipo_tamano");
        }
            System.out.println("Se ha consultado el tamaño con el id: " + id_tamano);
        
        }catch(Exception e){
            System.out.println("Error al consultar el tamaño");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return tamano;
    }
    public static List<String> getAllTamano() throws SQLException{
        List<String> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select tipo_tamano from ctamano";

            ps = con.prepareStatement(q);

           rs = ps.executeQuery();
           while(rs.next()){
               lista.add(rs.getString("tipo_tamano"));
           }
            System.out.println("Se han consultado los tamaños");
        
        }catch(Exception e){
            System.out.println("Error al consultar los tamaños");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
    }
    
    //CRUD de Presentación
    public static int RegiPresentacion(String tipo_presentacion) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Insert into cpresentacion (tipo_presentacion) values (?)";

           ps = con.prepareStatement(q);

           ps.setString(1, tipo_presentacion);

           estatus = ps.executeUpdate();
            System.out.println("Se ha registrado la presentacion: " + tipo_presentacion);
        
        }catch(Exception e){
            System.out.println("Error al registrar la presentacion");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
        
    }
    public static int ActualizarPresentacion(int id_presentacion, String tipo_presentacion) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Update  cpresentacion set tipo_presentacion = ? where id_presentacion = ?";

           ps = con.prepareStatement(q);

           ps.setString(1, tipo_presentacion);
           ps.setInt(2, id_presentacion);

           estatus = ps.executeUpdate();
            System.out.println("Se ha Actualizado la presentacion con Id: " + id_presentacion);
        
        }catch(Exception e){
            System.out.println("Error al actualizar la presentacion");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
    }
    public static int BorrarPresentacion(int id_presentacion) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Delete from cpresentacion where id_presentacion = ?";

           ps = con.prepareStatement(q);

           ps.setInt(1, id_presentacion);

           estatus = ps.executeUpdate();
            System.out.println("Se ha eliminado la presentacion con Id: " + id_presentacion);
        
        }catch(Exception e){
            System.out.println("Error al eliminar la presentacion");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return estatus;
        
        
        
    }
    public static int getIdPresentacion(String tipo_presentacion) throws SQLException{
        int id_presentacion = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select id_presentacion from cpresentacion where tipo_presentacion = ?";

            ps = con.prepareStatement(q);

           ps.setString(1, tipo_presentacion);

           rs = ps.executeQuery();
           while(rs.next()){
               id_presentacion = rs.getInt("id_presentacion");
           }
            System.out.println("Se ha consultado el id de la presentacion: " + tipo_presentacion);
        
        }catch(Exception e){
            System.out.println("Error al consultar el id de la presentacion");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_presentacion;
    }
    public static String getPresentacion(int id_presentacion) throws SQLException{
        String tipo_presentacion = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select tipo_presentacion from cpresentacion where id_presentacion = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_presentacion);
        
        rs = ps.executeQuery();
        while(rs.next()){
            tipo_presentacion = rs.getString("tipo_presentacion");
        }
            System.out.println("Se ha consultado la presentacion con el id: " + id_presentacion);
        
        }catch(Exception e){
            System.out.println("Error al consultar la presentacion");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return tipo_presentacion;
    }
    public static List<String> getAllPresentacion() throws SQLException{
        List<String> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select tipo_presentacion from cpresentacion";

            ps = con.prepareStatement(q);

           rs = ps.executeQuery();
           while(rs.next()){
               lista.add(rs.getString("tipo_presentacion"));
           }
            System.out.println("Se han consultado las presentacionwa");
        
        }catch(Exception e){
            System.out.println("Error al consultar las presentaciones");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
    }
    
}
