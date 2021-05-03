<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar helados</title>
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
    <section class="contenedorPrincipal">
<% 
//Variables de conexion
    Connection con = null;
    Statement set = null;
    ResultSet rs = null;
//Variables del Helado
    int id_hela = Integer.parseInt(request.getParameter("id_hela"));
    String nom_hela = request.getParameter("nom_hela");
    String nom_tipoHela = request.getParameter("nom_tipoHela");
    String tamaño = (String)request.getParameter("tamano"); 
    String nom_pres = request.getParameter("nom_pres");
    float prec_hela = Float.parseFloat(request.getParameter("pre_hela"));
    int id_tipoHela, id_cant, id_pres, id_clas;
try{
     ConexionSQL sql = new ConexionSQL();
        
        sql.init(con, set);
        
        con = sql.getCon();
        set = sql.getSet();
        
        
        System.out.println("Se ha conectado con la BD");
    
    
        try{
            Helado usu = new Helado();
            Helado usu1 = new Helado();
            
            id_tipoHela = sql.buscarIdTipoHeladoBD(nom_tipoHela);
            
            id_cant = sql.buscarIdCantidadBD(tamaño);
            
            id_pres = sql.buscarIdPresentacionoBD(nom_pres);
            
            if(id_tipoHela == 0 | id_cant == 0 | id_pres == 0){
%>
            <h2 style="text-align: center;">No esta registrado este tipo de helado, tamaño o presentacion</h2>
<%  
            }else{
            
                
                id_clas = sql.buscarIdClasificacionBD(id_tipoHela, id_cant , id_pres);
                
                //Si no existe la clasificacion entonces creare una
                if(id_clas == 0){
                    sql.agregarClasificacionBD(id_tipoHela, id_cant, id_pres);
                }
                
                id_clas = sql.buscarIdClasificacionBD(id_tipoHela, id_cant , id_pres);
                
                
                usu.Helado(nom_hela, prec_hela, id_clas);
                usu.setAtributos(id_clas, sql);
                usu.setId_hela(id_hela);
                usu1 = sql.buscarHelado(id_hela);
                usu1.setAtributos(id_clas, sql);
                sql.editarHelado(id_hela, usu);
%>
            <h2 style="text-align: center;">El helado con las siguientes características: </h2>
            <p  class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;">
                Nombre: <%=usu1.getNom_hela() %>
                <br><br>
                Tamaño: <%=usu1.getTamaño() %>
                <br><br>
                Tipo de helado <%=usu1.getNom_tipoHela() %>
                <br><br>
                Presentación: <%=usu1.getNom_pres()  %>
                <br><br>
                Precio: $<%=usu1.getPrec_hela()  %>
                <br><br>
                El id del Helado es: <%= usu1.getId_hela() %>
            </p>
                
                
            <h2 style="text-align: center;">Ahora tendra como características: </h2>
            <p  class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;">
                Nombre: <%=usu.getNom_hela() %>
                <br>
                <br>
                Tamaño: <%=usu.getTamaño() %>
                <br>
                <br>
                Tipo de helado <%=usu.getNom_tipoHela() %>
                <br>
                <br>
                Presentacion: <%=usu.getNom_pres()  %>
                <br>
                <br>
                Precio: $<%=usu.getPrec_hela()  %>
                <br>
                <br>
                El id del Helado es: <%= usu.getId_hela() %>
            </p>
                
<%
        
                }
            }catch(Exception e){
            System.out.println("No se ha podido modificar el Helado");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
        }
%>
<h2 style="text-align: center;">No se ha registrado el helado</h2>
<%
    }catch(Exception e){
        System.out.println("Error al conectar con la BD");
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
%>
<h2 style="text-align: center;">Hubo un error al Conectarse con la BD</h2>
    
<%}%>
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