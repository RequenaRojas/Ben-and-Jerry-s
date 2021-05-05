<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de helado</title> 
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
    
    //Variables del Helado
    String nom_hela = request.getParameter("nom_hela");
    String nom_tipoHela = request.getParameter("nom_tipoHela");
    
    String tamaño = (String)request.getParameter("nom_cant");
    
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
            //Consigo los id foraneas de Clasificacion
            
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
                        
                        //Agrego la clasificacion
                        sql.agregarClasificacionBD(id_tipoHela, id_cant, id_pres);
                        //Consigo mi clasificacion apartir de las llaves foraneas
                        id_clas = sql.buscarIdClasificacionBD(id_tipoHela, id_cant , id_pres);
                        System.out.println(nom_hela);
                        System.out.println(prec_hela);
                        System.out.println(id_pres);
                        System.out.println(id_clas);
                        
                        Helado hela = new Helado();

                        hela.Helado(nom_hela, prec_hela, id_clas);
                        hela.setAtributos(id_clas, sql);
                        if(sql.buscarIdHeladoBD(nom_hela) != 0){
    %>
                    <h2 style="text-align: center;">Ya existe un helado con el mismo nombre</h2>
    
    
    <%
                }else{
                    sql.agregarHeladoBS(hela);
    %>
                        <h2 style="text-align: center;">Helado registrado con éxito</h2>
                        <br>
                        <br>
                        <p class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;">
                        Nombre: <%=hela.getNom_hela() %>
                        <br><br>
                        Tamaño: <%=hela.getTamaño() %>
                        <br><br>
                        Tipo de helado <%=hela.getNom_tipoHela() %>
                        <br><br>
                        Presentación: <%=hela.getNom_pres()  %>
                        <br><br>
                        Precio: $<%=hela.getPrec_hela()  %>
                        <br><br>
                        El id del helado es: <%= sql.buscarIdHeladoBD(nom_hela) %>
                        </p>
    
    <%
                    }
                }
                System.out.println("Se ha registrado correctamente el Helado");    
            }catch(Exception e){
                    System.out.println("No se ha registrado el Helado");
                    System.out.println(e.getMessage());
                    System.out.println(e.getStackTrace());
    %>
    <h2 style="text-align: center;">No se ha registrado el helado</h2>
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