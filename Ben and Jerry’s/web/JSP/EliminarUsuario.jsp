<%-- 
    Document   : EliminarUsuario
    Created on : 2/05/2021, 03:25:30 AM
    Author     : sofo9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Usuario</title>
    </head>
    <body>
        
<% 
    //Variables de conexion
    Connection con = null;
    Statement set = null;
    ResultSet rs = null;
    
    //Variables del Usuario
    int id_usu = Integer.parseInt(request.getParameter("id_usu"));
    try{
        Usuario usu1 = new Usuario();
        ConexionSQL sql = new ConexionSQL();
        
       sql.init(con, set);
        
        
        usu1 = sql.buscarUsuarioBD(id_usu);
        
        sql.init(con, set);
        
        con = sql.getCon();
        set = sql.getSet();
        
        try{
         
            
        
%>
                <h1>Se ha eliminado el Usuario con las Siguientes Caracteristicas: </h1>

                <br>
                <br>
                <label>Nombre: <%=usu1.getNom()%> <%=usu1.getApelPat_usu()%> <%=usu1.getApelMat_usu()%> </label>
                <br>
                <br>
                <label>Fecha De Nacimiento: <%=usu1.getfechNaci_usu()%></label>
                <br>
                <br>
                <label>Domicilio: <%=usu1.getDomi_usu()%></label>
                <br>
                <br>
                <label>Telefono Partícular: <%=usu1.getTelePart()%></label>
                <br>
                <br>
                <label>Telefono Celular: <%=usu1.getTeleCelu()%></label>
               



<%
        
        sql.eliminarUsuario(id_usu);
        }catch(Exception e){
            System.out.println("No se ha podido Eliminar el Usuario");
 %>
                <h1>El Usuario no se ha podido Eliminar, Asegurese de Escribir un ID Existente</h1>
<%   
        }
        
    
    }catch(Exception e){
        System.out.println("No se ha podido Conectar con la Base de Datos");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
 %>
                <h1>No se ha podido Conectar con la Base de Datos</h1>
<%   
        
        
    }



%>
    </body>
</html>
