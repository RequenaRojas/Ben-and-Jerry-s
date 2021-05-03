<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Oferta</title>
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
    int id_ofer = Integer.parseInt(request.getParameter("id_ofer"));
    
    
    try{
        
        Oferta usu1 = new Oferta();
        Clasificacion clas =  new Clasificacion();
        ConexionSQL sql = new ConexionSQL();
        
       sql.init(con, set);
        
        
        usu1 = sql.buscarOferta(id_ofer);
        
        usu1.setAtributos(id_ofer, sql);
        
        clas.setAtributos(usu1.getId_clas(), sql);
        
        sql.init(con, set);
        
        con = sql.getCon();
        set = sql.getSet();
        
        try{
         
            
        
%>
                <h2 style="text-align: left;">Se ha eliminado la Oferta con las siguientes características: </h2>
        <p  class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;">
                Nombre: <%=usu1.getNom_ofer() %> 
                <br><br>
                Porcentaje de descuentos: <%=usu1.getPorc_ofer() %>%
                <br><br>
                Id del Admin vinculado: <%= usu1.getId_admi() %>
                 <br><br>
                Aplicaba a los helados con las caracteristicas:
                <br><br>
                Tipo de Helado: <%=clas.getNom_tipoHela()  %>
                <br><br>
                Cantidad: <%=clas.getTamaño() %>
                <br><br>
                Presentacion: <%=clas.getNom_pres() %>
                <br><br>
                <br><br>
        </p>
               



<%
        
        sql.eliminarOferta(id_ofer);
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