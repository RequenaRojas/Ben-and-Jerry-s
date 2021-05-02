<%-- 
    Document   : Registro
    Created on : 30/04/2021, 10:55:06 PM
    Author     : sofo9
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Usuario</title> 
</head>
    <body>
<%  //Variables de conexion
    Connection con = null;
    Statement set = null;
    ResultSet rs = null;
    
    //Variables del Usuario
    String nom_usu = request.getParameter("nom_usu");
    String apelPat_usu = request.getParameter("apelPat_usu");
    String apelMat_usu = request.getParameter("apelMat_usu");
    String fechNaci_usu = request.getParameter("fechNaci_usu");
    String domi_usu = request.getParameter("domi_usu");
    String telePart = request.getParameter("telaPart");
    String teleCelu = request.getParameter("teleCelu");
     
    
    
    //Variables de la conexion del Usuario
    String ip, iph;
    int puerto, puertoh;
    
    
    try{
        
        ConexionSQL sql = new ConexionSQL();
        
        sql.init(con, set);
        
        con = sql.getCon();
        set = sql.getSet();
        
        
        System.out.println("Se ha conectado con la BD");
    
    
        try{
            
            ip = request.getLocalAddr();
            puerto = request.getLocalPort();
            
            iph = request.getRemoteAddr();
            puertoh = request.getRemotePort();
            
            Usuario usu = new Usuario();
            
            usu.Usuario(nom_usu, apelPat_usu, apelMat_usu, fechNaci_usu, domi_usu, telePart, teleCelu);

            
            
            if(sql.buscarIdUsuarioBD(nom_usu, apelPat_usu, apelMat_usu) != 0){
%>
                <h1>Ya existe este Usuario con Nombre, Apellidos Paterno y Materno que acabás de escribir</h1>


<%
              
                
            }else{
          
                sql.agregarUsuarioBD(usu);
%>
                <h1>Usuario Registrado con exito awa</h1>

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
            }
            System.out.println("Se ha registrado correctamente el Usuario");    
        }catch(Exception e){
                System.out.println("No se ha registrado el usuario");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
%>
            <h1>No se ha podido registrar el Usuario</h1>
<%
    }
    
    

    
    
    }catch(Exception e){
        System.out.println("Error al conectar con la BD");
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
%>
        <h1>Hubo un error al Conectarse con la BD</h1>
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