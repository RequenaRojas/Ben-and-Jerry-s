<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" import="java.sql.*,java.util.Date, java.util.* , Clases.*, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar helado</title>
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
            Helado hela = new Helado();
           
        try{
             ConexionSQL sql = new ConexionSQL();
                
                sql.init(con, set);
                
                con = sql.getCon();
                set = sql.getSet();
                
                
                System.out.println("Se ha conectado con la BD");
            
            
                try{
                    
                    hela = sql.buscarHelado(id_hela);
                    hela.setAtributos(hela.getId_clas(), sql);
                    
        %>
        <h2 style="text-align: left;">Se ha borrado el helado con las siguientes características: </h2>
        <p  class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;"></p>
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
            Id del Helado es: <%= hela.getId_hela() %>
        </p>
                        
        <%
                
                        sql.eliminarHelado(id_hela);
                    }catch(Exception e){
                    System.out.println("No se ha podido modificar el Helado");
                        System.out.println(e.getMessage());
                        System.out.println(e.getStackTrace());
                
        %>
        <h2 style="text-align: left;">No se ha borrado el helado, hubo un error</h2>
        <%
                    }
            }catch(Exception e){
                System.out.println("Error al conectar con la BD");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
        %>
        <h2 style="text-align: left;">Hubo un error al Conectarse con la BD</h2>
            
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