<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/estilo.css">
    <link rel="shortcut icon" href="https://www.drodd.com/images16/pastel-blue6.jpg">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    <link rel="stylesheet" href="./css/estilo.css">
    <title>Consultar Usuarios</title>
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
    <h1>Consulta Exitosa</h1>

    <% 
    //Variables de conexion
    Connection con = null;
    Statement set = null;
    ResultSet rs = null;
    
    //Datos de la tabla
    PreparedStatement ps;
    ResultSet rs;
    ps=con.prepareStatement("select * from Usuario")
    
    try{
        ConexionSQL sql = new ConexionSQL();
        
        sql.init(con, set);
        
        con = sql.getCon();
        set = sql.getSet();
        
        
        System.out.println("Se ha conectado con la BD");
        try{
            //Objetos Usuario
            
            Usuario usu = new Usuario();
           
%>



    <div>
        <table>
            <tr>
                <th>Id del usuario:</th> <%= sql.buscarIdUsuarioBD(usu.getNom(), usu.getApelPat_usu(), usu.getApelMat_usu()) %>
                <th>Nombre del usuario:</th> <%=usu1.getNom()%> <%=usu1.getApelPat_usu()%> <%=usu1.getApelMat_usu()%>
            </tr>
 
 
 
 
 
        </table>
    </div>
    <%
             System.out.println("Se han mostrado las tablas");
         }catch(Exception e){
             System.out.println("No se han podido encontrar los datos");
             System.out.println(e.getMessage());
             System.out.println(e.getStackTrace());
  %>
     <h2 style="text-align: center;">No se han encontrado las tablas, intente de nuevo</h2>
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