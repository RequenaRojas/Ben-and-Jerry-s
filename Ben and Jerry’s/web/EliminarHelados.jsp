<%-- 
    Document   : ModificarHelados
    Created on : 2/05/2021, 11:55:51 AM
    Author     : sofo9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Helados</title>
    </head>
    <body>
<% 
//Variables de conexion
    Connection con = null;
    Statement set = null;
    ResultSet rs = null;

//Variables del Helado
    int id_hela = Integer.parseInt(request.getParameter("id_hela"));
    Helado hela = new Helado();
   

try{
     ConexionSQL sql = new ConexionSQL();
        
        sql.init(con, set);
        
        con = sql.getCon();
        set = sql.getSet();
        
        
        System.out.println("Se ha conectado con la BD");
    
    

        try{
            
            hela = sql.buscarHelado(id_hela);
            hela.setAtributos(hela.getId_clas(), sql);
            
%>
                <h1>Se ha borrado el Helado con las siguientes Carcatristicas: </h1>


                    <br>
                    <br>
                    
                <label>Nombre: <%=hela.getNom_hela() %></label>
                <br>
                <br>
                <label>Tamaño: <%=hela.getTamaño() %></label>
                <br>
                <br>
                <label>Tipo de Helado <%=hela.getNom_tipoHela() %></label>
                <br>
                <br>
                <label>Presentacion: <%=hela.getNom_pres()  %></label>
                <br>
                <br>
                <label>Precio: $<%=hela.getPrec_hela()  %></label>
                <br>
                <br>
                <label>Id del Helado es: <%= hela.getId_hela() %></label>
                
<%
        
                sql.eliminarHelado(id_hela);
            }catch(Exception e){
            System.out.println("No se ha podido modificar el Helado");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
        
%>
            <h1>No se ha borrado el Helado, hubo un error</h1>
<%
            }
    }catch(Exception e){
        System.out.println("Error al conectar con la BD");
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
%>
        <h1>Hubo un error al Conectarse con la BD</h1>
    
<%}%>
  
    </body>
</html>
<%try{
        con.close();
    }catch(Exception e){
        super.destroy();
    } %>