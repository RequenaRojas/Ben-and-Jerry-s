<%@page import="Clases.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de usuario</title> 
        <link rel="stylesheet" href="./css/estilo.css">
        <link rel="shortcut icon" href="https://www.drodd.com/images16/pastel-blue6.jpg">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
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
<%  //Variables de conexion
    Connection con = null;
    Statement set = null;
    ResultSet rs = null;
    
    //Variables del Usuario
    int id_admin = Integer.parseInt(request.getParameter("id_admin"));
    String nom_of = request.getParameter("nom_ofer");
    int porc_ofer = Integer.parseInt(request.getParameter("porc_ofer"));
    
    String nom_tipoHela = request.getParameter("nom_tipoHela");
    
    String tamaño = request.getParameter("nom_cant");
    
    String nom_pres = request.getParameter("nom_pres");
    
    int id_tipoHela, id_cant, id_pres, id_clas;
    
    Oferta ofer = new Oferta();
     
    
    
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
            //Consigo los id foraneas de Clasificacion
            System.out.println(nom_tipoHela +" "+ tamaño+" "+nom_pres);
            id_tipoHela = sql.buscarIdTipoHeladoBD(nom_tipoHela);
            
            id_cant = sql.buscarIdCantidadBD(tamaño);
            
            id_pres = sql.buscarIdPresentacionoBD(nom_pres);
            
            System.out.println(id_tipoHela);
            System.out.println(id_cant);
            System.out.println(id_pres);
            
            
            
            
            
            //Filtro que las llaves foraneas existan
            if(id_tipoHela == 0 | id_cant == 0 | id_pres == 0){
%>
                    <h2 style="text-align: center;">No esta registrado este tipo de helado, tamaño o presentación</h1>
    <%     
            
            }else{
                id_clas = sql.buscarIdClasificacionBD(id_tipoHela, id_cant , id_pres);
                System.out.println("despues de buscar el id_clas");
                
                //Si no existe la clasificacion entonces creare una
                if(id_clas == 0){
                    sql.agregarClasificacionBD(id_tipoHela, id_cant, id_pres);
                }
                

                id_clas = sql.buscarIdClasificacionBD(id_tipoHela, id_cant , id_pres);

                System.out.println(id_clas);
                ofer.Oferta(nom_of, porc_ofer);

                ofer.setId_admi(id_admin);
                System.out.println(id_admin);
                ofer.setId_clas(id_clas);
                if(sql.buscarIdOferta(nom_of) != 0){
    %>
                    <h2 style="text-align: center;">Ya existe esta oferta con el mismo nombre</h2>
    
    
    <%
                }else{
                    System.out.println("Antes de agregar oferta");
                    sql.agregarOferta(ofer);
    %>
                        <h2 style="text-align: center;">Oferta registrado con éxito</h2>
                        <br>
                        <br>
                        <p class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;">
                        Nombre: <%=ofer.getNom_ofer() %>
                        <br><br>
                        Porcentaje de descuento: <%= ofer.getPorc_ofer() %>%
                        <br><br>
                        Aplicara con los Helados que tengan como caracteristica
                        <br><br>
                        Tamaño: <%=sql.buscarTipoHeladoBD(id_tipoHela) %>
                        <br><br>
                        Tipo de helado <%=sql.buscarCantidadBD(id_cant) %>
                        <br><br>
                        Presentación: <%=sql.buscarPresentacionBD(id_pres)  %>
                        <br><br>
                        El id de la oferta es: <%= sql.buscarIdOferta(ofer.getNom_ofer()) %>
                        </p>
    
    <%
                    }
                }
                System.out.println("Se ha registrado correctamente la Oferta");    
            }catch(Exception e){
                    System.out.println("No se ha registrado la Oferta, Asegurese de checar su ID de Administrador");
                    System.out.println(e.getMessage());
                    System.out.println(e.getStackTrace());
    %>
    <h2 style="text-align: center;">No se ha registrado la Oferta, Asegurese de checar su ID de Administrador</h2>
    <%
    }
    }catch(Exception e){
        System.out.println("Error al conectar con la BD");
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
    %>
    <h2 style="text-align: center;">Hubo un error al Conectarse con la BD</h2>
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