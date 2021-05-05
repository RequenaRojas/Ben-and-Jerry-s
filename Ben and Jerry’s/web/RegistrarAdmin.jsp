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
    
    //Variables del Usuario
    int id_usu = Integer.parseInt(request.getParameter("id_usu"));
    int id_admi;
    
    
    
    
    try{
        
        ConexionSQL sql = new ConexionSQL();
        
        sql.init(con, set);
        
        con = sql.getCon();
        set = sql.getSet();
        
        
        System.out.println("Se ha conectado con la BD");
    
    
        try{
            
            
            Administrador admi = new Administrador();
            Usuario usu = new Usuario();
            id_admi = sql.buscarIdAdministradorBD(id_usu);
            System.out.println(id_admi);
            
            if(id_admi != 0){
%>
                <h1>Ya tienes una cuenta de Administrador vinculada con este Usuario</h1>
                <br>
                <br>
                <label>Tu id de Administrador es: <%= sql.buscarIdAdministradorBD(id_usu)   %></label>
                


<%
            }else{
          
                sql.agregarAdmin(id_usu);
                id_admi = sql.buscarIdAdministradorBD(id_usu);
                
                admi = sql.buscarAdministradorBD(id_admi);
                usu = sql.buscarUsuarioBD(admi.getid_usu());
%>
                <h1>Cuenta de Administrador vinculada con exito</h1>

                <br>
                <br>
                <label>Nombre: <%=usu.getNom()%> <%=usu.getApelPat_usu()%> <%=usu.getApelMat_usu()%> </label>
                <br>
                <br>
                <label>Tu id de Usuario: <%= admi.getid_usu() %></label>
                <br>
                <br>
                <label>Tu id de Administrador sera: <%= admi.getid_admi()  %></label>
                <h1>No la olvides!!</h1>



    <%
            }
            System.out.println("Se ha registrado correctamente al Administrador");    
        }catch(Exception e){
                System.out.println("No se ha registrado al Administrador");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
%>
            <h1>No se ha podido registrar el Usuario</h1>
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