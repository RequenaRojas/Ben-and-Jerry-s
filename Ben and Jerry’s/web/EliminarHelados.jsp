<%-- 
    Document   : EliminarHelados
    Created on : 2/05/2021, 11:56:18 AM
    Author     : sofo9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
<%
     //Variables de conexion
    Connection con = null;
    Statement set = null;
    ResultSet rs = null;
    
    try{
         ConexionSQL sql = new ConexionSQL();
        
        sql.init(con, set);
        
        con = sql.getCon();
        set = sql.getSet();
        
        
        System.out.println("Se ha conectado con la BD");
        try{
            String tamaño = (String)request.getParameter("tamaño");
            System.out.println(tamaño);
        }catch(Exception e){
            
        }
        
    }catch(Exception e){
        
        
    }





%>
    </body>
</html>
