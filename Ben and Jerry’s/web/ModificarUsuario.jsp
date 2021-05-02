<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Modificar usuario</title>
        <link rel="stylesheet" href="./css/estilo.css">
        <link rel="shortcut icon" href="https://www.drodd.com/images16/pastel-blue6.jpg">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
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
                <h2 style="text-align: center;">El Usuario con las siguientes características: </h2>
                <p  class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;">
                Nombre: <%=usu1.getNom()%> <%=usu1.getApelPat_usu()%> <%=usu1.getApelMat_usu()%>
                <br><br>
                Fecha de nacimiento: <%=usu1.getfechNaci_usu()%>
                <br><br>
                Domicilio: <%=usu1.getDomi_usu()%>
                <br><br>
                Teléfono partícular: <%=usu1.getTelePart()%>
                <br><br>
                Teléfono celular: <%=usu1.getTeleCelu()%>
                <br><br>
                </p>
                
                <h2 style="text-align: center;">Ahora tendrá como caracteriticas: </h2>
                <p  class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;">
                Nombre: <%=usu.getNom()%> <%=usu.getApelPat_usu()%> <%=usu.getApelMat_usu()%>
                <br><br>
                Fecha de nacimiento: <%=usu.getfechNaci_usu().toString()%>
                <br><br>
                Domicilio: <%=usu.getDomi_usu()%>
                <br><br>
                Teléfono particular: <%=usu.getTelePart()%>
                <br><br>
                Teléfono celular: <%=usu.getTeleCelu()%>
                <br><br>
                Tu id de Usuario: <%= sql.buscarIdUsuarioBD(usu.getNom(), usu.getApelPat_usu(), usu.getApelMat_usu()) %>
                </p>
<%
            System.out.println("Se ha modificado Correctamente al Usuario");
        }catch(Exception e){
            System.out.println("El Usuario no se ha podido modificar, Asegurese de Escribir un ID Existente");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
 %>
    <h2 style="text-align: center;">El Usuario no se ha podido modificar, Asegurese de Escribir un ID Existente</h2>
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

<%try{
        con.close();
    }catch(Exception e){
        super.destroy();
    } %>