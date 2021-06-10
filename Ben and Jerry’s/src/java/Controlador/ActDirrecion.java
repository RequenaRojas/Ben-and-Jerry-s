package Controlador;

import Modelo.Dirrecion;
import Modelo.Tarjeta;
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
public class ActDirrecion {
    public static int RegiDirrecion(Dirrecion dirr) throws SQLException, ServletException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "insert into ddirrecion (calle_dirrec, num_ext_dirrec, num_int_dirrec,"
                + " id_alcaldia, id_colonia)"
                + "values (?,?,?,?,?)";
        
         ps = con.prepareStatement(q);
        
        ps.setString(1, dirr.getCalle_dirrec());
        ps.setInt(2, dirr.getNum_ext_dirrec());
        ps.setInt(3, dirr.getNum_int_dirrec());
        ps.setInt(4, ActDirrecion.getIdAlcaldia(dirr.getAlcaldia()));
        ps.setInt(5, ActDirrecion.getIdColonia(dirr.getColonia()));
        
        estatus = ps.executeUpdate();
        System.out.println("Registro Exitoso de la dirrecion");
        }catch(Exception e){
            System.out.println("Error al registrar la dirrecion");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
        
    
    
    }
    public static Dirrecion getDirrecion(int id_dirrec) throws SQLException{
        Dirrecion dirr = new Dirrecion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from ddireccion where id_dirrec = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_dirrec);
        
        rs = ps.executeQuery();
        while(rs.next()){
            dirr.setCalle_dirrec(rs.getString("calle_dirrec"));
            dirr.setNum_ext_dirrec(rs.getInt("num_ext_dirrec"));
            dirr.setNum_int_dirrec(rs.getInt("num_int_dirrec"));
            dirr.setAlcaldia(ActDirrecion.getAlcaldia(rs.getInt("id_alcaldia")));
            List<String> colonia = ActDirrecion.getColonia(rs.getInt("id_colonia"));
            dirr.setColonia(colonia.get(0));
            dirr.setCp_colonia(colonia.get(1));

        }
            System.out.println("Se ha consultado la dirrecion con el id: " + id_dirrec);
        
        }catch(Exception e){
            System.out.println("Error al consultar la dirrecion");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return dirr;
        
        
        
    }
    public static int ActualizarTarjeta(int id_dirrec,Dirrecion dirr) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Update ddirrecion SET calle_dirrec = ?, num_ext_dirrec = ?, num_int_dirrec = ?,"
                + " id_alcaldia = ?, id_colonia = ?  WHERE id_dirrec = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setString(1, dirr.getCalle_dirrec());
        ps.setInt(2, dirr.getNum_ext_dirrec());
        ps.setInt(3, dirr.getNum_int_dirrec());
        ps.setInt(4, ActDirrecion.getIdAlcaldia(dirr.getAlcaldia()));
        ps.setInt(5, ActDirrecion.getIdColonia(dirr.getColonia()));
        ps.setInt(6, id_dirrec);
        
        estatus = ps.executeUpdate();
        System.out.println("Dirrecion con id: "+id_dirrec + " Actualizada");
        }catch(Exception e){
            System.out.println("Error al Actualizar la Dirrecion");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
    }
    public static int BorrarTarjeta(int id_dirrec) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "DELETE FROM ddirrecion WHERE id_dirrec = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_dirrec);
        
        estatus = ps.executeUpdate();
        System.out.println("Dirrecion con id: "+id_dirrec + " Eliminada");
        }catch(Exception e){
            System.out.println("Error al Eliminar la Dirrecion");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
        
        
        
    }
    
    //CRUD de Catalagos tipos de banco y pago
    public static int getIdAlcaldia(String alcaldia) throws SQLException{
        int id_alcaldia = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select id_alcaldia from CAlcaldia where nombre_alcaldia = ?";

            ps = con.prepareStatement(q);

           ps.setString(1, alcaldia);

           rs = ps.executeQuery();
           while(rs.next()){
               id_alcaldia = rs.getInt("id_alcaldia");
           }
            System.out.println("Se ha consultado el id de la alcaldia: " + alcaldia);
        
        }catch(Exception e){
            System.out.println("Error al consultar el id de la alcaldia");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_alcaldia;
    }
    public static String getAlcaldia(int id_alcaldia) throws SQLException{
        String alcaldia = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select nombre_alcaldia from calcaldia where id_alcaldia = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_alcaldia);
        
        rs = ps.executeQuery();
        while(rs.next()){
            alcaldia = rs.getString("nombre_alcaldia");
        }
            System.out.println("Se ha consultado la alcaldia con el id: " + id_alcaldia);
        
        }catch(Exception e){
            System.out.println("Error al consultar la alcaldia");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return alcaldia;
    }
    public static int getIdColonia(String colonia) throws SQLException{
        int id_colonia = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select id_colonia from ccolonia where nombre_colonia = ?";

            ps = con.prepareStatement(q);

           ps.setString(1, colonia);

           rs = ps.executeQuery();
           while(rs.next()){
               id_colonia = rs.getInt("id_colonia");
           }
            System.out.println("Se ha consultado el id de la colonia: " + colonia);
        
        }catch(Exception e){
            System.out.println("Error al consultar el id de la colonia");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_colonia;
    }
    //Primero el nombre de la colonia [0] y despues el cp [1]
    public static List<String> getColonia(int id_colonia) throws SQLException{
        List<String> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from ccolonia where id_colonia = ?";
        
         ps = con.prepareStatement(q);
        
        ps.setInt(1, id_colonia);
        
        rs = ps.executeQuery();
        while(rs.next()){
            lista.add(rs.getString("nombre_colonia"));
            lista.add(rs.getString("cp_colonia"));
        }
            System.out.println("Se ha consultado la colonia con el id: " + id_colonia);
        
        }catch(Exception e){
            System.out.println("Error al consultar la colonia");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
    }
    
    public static List<String> getAllAcaldias() throws SQLException{
        List<String> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select nombre_alcaldia from calcaldia";

            ps = con.prepareStatement(q);

           rs = ps.executeQuery();
           while(rs.next()){
               lista.add(rs.getString("nombre_alcaldia"));
           }
            System.out.println("Se han consultado las alcaldias");
        
        }catch(Exception e){
            System.out.println("Error al consultar las alcaldias");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
    }
    public static List<List> getAllColonias() throws SQLException{
        List<List> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
            con = ConexionSQL.getConnection();   

           String q = "Select * from ccolonia";

            ps = con.prepareStatement(q);

           rs = ps.executeQuery();
           while(rs.next()){
               List<String> colonia = null;
               colonia.add(rs.getString("nombre_colonia"));
               colonia.add(rs.getString("cp_colonia"));
               lista.add(colonia);
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