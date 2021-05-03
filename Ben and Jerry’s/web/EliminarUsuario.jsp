<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Usuario</title>
    </head>
    <body>
        <header>
            <input type="checkbox" id="check">
            <label for="check" class="checkbtn">
                <i class="fas fa-bars"></i>
            </label>
            <a class="logo" href="index.html">Benito y Gerardo</a>
        <ul>
            <li><a class="Header" href="index.html">Inicio</a></li>
            <li><a class="Header" href="Registro.html">Regístrate</a></li>
            <li><a class="active" href="Usuario.html">Usuario</a></li>
            <li><a class="Header" href="Tienda.html">Tienda</a></li>
        </ul>
        </header>
    </ul>
    </header>
    <section class="contenedorPrincipal">
        
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
                <h2 style="text-align: left;">Se ha eliminado el Usuario con las siguientes características: </h2>
        <p  class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;">
                Nombre: <%=usu1.getNom()%> <%=usu1.getApelPat_usu()%> <%=usu1.getApelMat_usu()%> 
                <br><br>
                Fecha De Nacimiento: <%=usu1.getfechNaci_usu()%>
                <br><br>
                Domicilio: <%=usu1.getDomi_usu()%>
                <br><br>
                Telefono Partícular: <%=usu1.getTelePart()%>
                <br><br>
                Telefono Celular: <%=usu1.getTeleCelu()%>
                <br><br>
        </p>
               



<%
        
        sql.eliminarUsuario(id_usu);
        }catch(Exception e){
            System.out.println("No se ha podido Eliminar el Usuario");
 %>
                <h2 style="text-align:center">El Usuario no se ha podido eliminar, asegurese de escribir un ID existente</h2>
<%   
        }
        
    
    }catch(Exception e){
        System.out.println("No se ha podido Conectar con la Base de Datos");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
 %>
                <h2 style="text-align: center;">No se ha podido Conectar con la Base de Datos</h2>
<%   
        
     
    }
%>
<br><br><br>
    <a class="link" href="index.html" style="text-align: center;">Regresar a inicio</a>
    </section>
    <footer>
        <p>
        <br>
            Integrantes del equipo:
        <br><br>
            - Fernández García Gael - Morales de los Santos Jaime Emmanuel - Requena Rojas Moisés Sófocles -
        <br><br>
        </p>
    </footer>
    </body>
</html>