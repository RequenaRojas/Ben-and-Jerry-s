<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultar Clasificacion</title>
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
    ps=con.prepareStatement("select * from id_usu")
    
   %>
   try{
        
    tabla = new Tabla();
    Cantidad_usu =  new Cantidad();
    ConexionSQL sql = new ConexionSQL();
    
   sql.init(con, set);
    
    
    tabla = sql.buscarTabla(id_tab);
    
    tabla.setAtributos(id_tab, sql);
    
    clas.setAtributos(usu1.getId_clas(), sql);
    
    sql.init(con, set);
    
    con = sql.getCon();
    set = sql.getSet();
    
    try{
     //Dtos de la tabla 2

        
    

   <div>
       <table>
           <tr>
               <th>Tipo de Helado:</th>
               <th>Presentacion:</th>
               <th>Tamaño:</th>


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
        









    
</body>
</html>