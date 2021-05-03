<%-- 
    Document   : ModificarHelados
    Created on : 2/05/2021, 11:55:51 AM
    Author     : sofo9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Helados</title>
    </head>
    <body>
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
                <h1>No esta registrado este tipo de helado, tamaño o presentacion</h1>
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
                <h1>Se ha modificado con el Helado con las siguientes Carcatristicas: </h1>


                    <br>
                    <br>
                    <label>Nombre: <%=usu1.getNom_hela() %></label>
                    <br>
                    <br>
                    <label>Tamaño: <%=usu1.getTamaño() %></label>
                    <br>
                    <br>
                    <label>Tipo de Helado <%=usu1.getNom_tipoHela() %></label>
                    <br>
                    <br>
                    <label>Presentacion: <%=usu1.getNom_pres()  %></label>
                    <br>
                    <br>
                    <label>Precio: $<%=usu1.getPrec_hela()  %></label>
                    <br>
                    <br>
                    <label>El id del Helado es: <%= usu1.getId_hela() %></label>

                
                
                <h1>Ahora Tendra como caracteriticas: </h1>

                <br>
                <br>
                <label>Nombre: <%=usu.getNom_hela() %></label>
                <br>
                <br>
                <label>Tamaño: <%=usu.getTamaño() %></label>
                <br>
                <br>
                <label>Tipo de Helado <%=usu.getNom_tipoHela() %></label>
                <br>
                <br>
                <label>Presentacion: <%=usu.getNom_pres()  %></label>
                <br>
                <br>
                <label>Precio: $<%=usu.getPrec_hela()  %></label>
                <br>
                <br>
                <label>El id del Helado es: <%= usu.getId_hela() %></label>
                
<%
        
                }
            }catch(Exception e){
            System.out.println("No se ha podido modificar el Helado");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
        }
%>
            <h1>No se ha registrado el Helado</h1>
<%
    }catch(Exception e){
        System.out.println("Error al conectar con la BD");
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
%>
        <h1>Hubo un error al Conectarse con la BD</h1>
    
<%}%>
  
    </body>
</html>
<%try{
        con.close();
    }catch(Exception e){
        super.destroy();
    } %>