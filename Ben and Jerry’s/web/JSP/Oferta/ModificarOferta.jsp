<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar oferta</title>
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
    
//Variables del Oferta
    int id_ofer = Integer.parseInt(request.getParameter("id_ofer"));
    int porc_ofer = Integer.parseInt(request.getParameter("porc_ofer"));
    String nom_ofer = request.getParameter("nom_ofer");
    
    String nom_tipoHela = request.getParameter("nom_tipoHela");
    String tamaño = (String)request.getParameter("tamano"); 
    String nom_pres = request.getParameter("nom_pres");
    
    System.out.println(nom_tipoHela+" "+ tamaño +" "+ nom_pres);
    
    int id_tipoHela, id_cant, id_pres, id_clas;
    
try{
     ConexionSQL sql = new ConexionSQL();
        
        sql.init(con, set);
        
        con = sql.getCon();
        set = sql.getSet();
        
        
        System.out.println("Se ha conectado con la BD");
    
    
        try{
            Oferta usu = new Oferta();
            Oferta usu1 = new Oferta();
            Clasificacion clas = new Clasificacion();
            
            
            
            
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
                clas = sql.buscarClasificacionBD(id_clas);
                
                usu1 = sql.buscarOferta(id_ofer);
                usu1.setAtributos(id_ofer, sql);
                
                usu.Oferta(nom_ofer, porc_ofer);
                usu.setId_admi(usu1.getId_admi());
                usu.setId_clas(id_clas);
                usu.setAtributos(id_clas, sql);
                if(usu1 == null){

                    %>
                                    <h2 style="text-align: center;">El ID no esta registrado ningun helado</h2>


                    <%
                }else{

                
                
                    sql.editarOferta(id_ofer, usu);
    %>
                    <h2 style="text-align: center;">El helado con las siguientes características: </h2>
                    <p class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;">
                                Nombre: <%=usu1.getNom_ofer() %>
                                <br><br>
                                Porcentaje de descuento: <%= usu1.getPorc_ofer() %>%
                                <br><br>
                                Aplicara con los Helados que tengan como caracteristica:
                                <br><br>
                                Tamaño: <%= sql.buscarCantidadBD(clas.getId_cant()) %>
                                <br><br>
                                Tipo de helado <%=sql.buscarTipoHeladoBD(clas.getId_tipoHela()) %>
                                <br><br>
                                Presentación: <%=sql.buscarPresentacionBD(clas.getId_pres())  %>
                                <br><br>
                                El id de la Oferta es: <%= sql.buscarIdOferta(usu1.getNom_ofer()) %>
                                </p>


                    <h2 style="text-align: center;">Ahora tendra como características: </h2>
                    <p  class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;">
                        Nombre: <%=usu.getNom_ofer() %>
                                <br><br>
                                Porcentaje de descuento: <%= usu.getPorc_ofer() %>%
                                <br><br>
                                Aplicara con los Helados que tengan como caracteristica:
                                <br><br>
                                Tamaño: <%= tamaño%>
                                <br><br>
                                Tipo de helado <%=nom_tipoHela %>
                                <br><br>
                                Presentación: <%=nom_pres  %>
                                <br><br>
                                El id de la Oferta es: <%= id_ofer  %>
                                </p>

    <%

                    }
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