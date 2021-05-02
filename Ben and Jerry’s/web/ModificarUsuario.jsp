<%-- 
    Document   : ModificarUsuario
    Created on : 2/05/2021, 12:05:54 AM
    Author     : sofo9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Usuario</title>
    </head>
    <body>
        <h1>Hello World!</h1>
<% 
    //Variables de conexion
    Connection con = null;
    Statement set = null;
    ResultSet rs = null;
    
    //Variables del Usuario
    int id_usu = Integer.parseInt(request.getParameter("id_usu"));
    String nom_usu = request.getParameter("nom_usu");
    String apelPat_usu = request.getParameter("apelPat_usu");
    String apelMat_usu = request.getParameter("apelMat_usu");
    String fechNaci_usu = request.getParameter("fechNaci_usu");
    String domi_usu = request.getParameter("domi_usu");
    String telePart = request.getParameter("telaPart");
    String teleCelu = request.getParameter("teleCelu");
    
    try{
        ConexionSQL sql = new ConexionSQL();
        
        sql.init(con, set);
        
        con = sql.getCon();
        set = sql.getSet();
        
        
        System.out.println("Se ha conectado con la BD");
        try{
            
            Usuario usu = new Usuario();
            Usuario usu1 = new Usuario();
            
            
        
            usu.Usuario(nom_usu, apelPat_usu, apelMat_usu, fechNaci_usu, domi_usu, telePart, teleCelu);            
            
            System.out.println("Antes del sql.buscarUsiarioBD");
            
            usu1 = sql.buscarUsuarioBD(id_usu);
            System.out.println("Despues del sql.bucarUsuarioBD");
            

            sql.editarUsuario(id_usu, usu);
            System.out.println("Despuesde Editar ");
%>
                <h1>El Usuario con las siguientes Características: </h1>

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
                <br>
                <br>
                
                
                <h1>Ahora Tendra como caracteriticas: </h1>

                <br>
                <br>
                <label>Nombre: <%=usu.getNom()%> <%=usu.getApelPat_usu()%> <%=usu.getApelMat_usu()%> </label>
                <br>
                <br>
                <label>Fecha De Nacimiento: <%=usu.getfechNaci_usu().toString()%></label>
                <br>
                <br>
                <label>Domicilio: <%=usu.getDomi_usu()%></label>
                <br>
                <br>
                <label>Telefono Partícular: <%=usu.getTelePart()%></label>
                <br>
                <br>
                <label>Telefono Celular: <%=usu.getTeleCelu()%></label>
                <br>
                <br>
                <label>Tu id de Usuario: <%= sql.buscarIdUsuarioBD(usu.getNom(), usu.getApelPat_usu(), usu.getApelMat_usu()) %></label>
<%
            System.out.println("Se ha modificado Correctamente al Usuario");
        }catch(Exception e){
            System.out.println("El Usuario no se ha podido modificar, Asegurese de Escribir un ID Existente");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
 %>
                <h1>El Usuario no se ha podido modificar, Asegurese de Escribir un ID Existente</h1>
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

<%try{
        con.close();
    }catch(Exception e){
        super.destroy();
    } %>
