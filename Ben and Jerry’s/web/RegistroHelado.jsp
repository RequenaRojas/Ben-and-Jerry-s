<%-- 
    Document   : RegistroHelado
    Created on : 2/05/2021, 02:00:31 PM
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
    
    //Variables del Helado
    String nom_hela = request.getParameter("nom_hela");
    String nom_tipoHela = request.getParameter("nom_tipoHela");
    String tamaño = request.getParameter("tamaño");
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
            //Consigo los id foranes de Clasificacion
            id_tipoHela = sql.buscarIdTipoHeladoBD(nom_tipoHela);
            id_cant = sql.buscarIdCantidadBD(tamaño);
            id_pres = sql.buscarIdPresentacionoBD(nom_pres);
            System.out.println(id_tipoHela);
            System.out.println(id_cant);
            System.out.println(id_pres);
            
            
            
            
            
            //Filtro que las llaves foraneas existan
            if(id_tipoHela == 0 | id_cant == 0 | id_pres == 0){
%>
                <h1>No esta registrado este tipo de helado, tamaño o presentacion</h1>
<%     
            
            }else{
                
                //Agrego la clasificacion
                sql.agregarClasificacionBD(id_tipoHela, id_cant, id_pres);

                //Consigo mi clasificacion apartir de las llaves foraneas
                id_clas = sql.buscarIdClasificacionBD(id_tipoHela, id_cant , id_pres);

                System.out.println(nom_hela);
                System.out.println(prec_hela);
                System.out.println(id_pres);
                
                Helado hela = new Helado();
                System.out.println("Despues de crear el objeto helado");


                hela.Helado(nom_hela, prec_hela, id_clas);

                if(sql.buscarIdHeladoBD(nom_hela) != 0){
    %>
                    <h1>Ya existe este Helado con el mismo nombre</h1>


    <%


                }else{

                    sql.agregarHeladoBS(hela);
    %>
                    <h1>Usuario Registrado con exito awa</h1>

                    <br>
                    <br>
                    <label>Nombre: <%=hela.getNom_hela() %></label>
                    <br>
                    <br>
                    <label>Tamaño: <%=hela.getTamaño() %></label>
                    <br>
                    <br>
                    <label>Tipo de Helado <%=hela.getNom_tipoHela() %></label>
                    <br>
                    <br>
                    <label>Presentacion: <%=hela.getNom_pres()  %></label>
                    <br>
                    <br>
                    <label>Precio: $<%=hela.getPrec_hela()  %></label>
                    <br>
                    <br>
                    <label>El id del Helado es: <%= hela.getId_hela() %></label>



        <%
                }
            }
            System.out.println("Se ha registrado correctamente el Helado");    
        }catch(Exception e){
                System.out.println("No se ha registrado el Helado");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
%>
            <h1>No se ha registrado el Helado</h1>
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