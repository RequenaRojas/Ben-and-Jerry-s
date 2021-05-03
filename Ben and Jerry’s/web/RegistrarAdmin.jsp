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
    //Variables de la conexion 
    String ip, iph;
    int puerto, puertoh;
    
    
    try{
        
        ConexionSQL sql = new ConexionSQL();
        
        sql.init(con, set);
        
        con = sql.getCon();
        set = sql.getSet();
        
        
        System.out.println("Se ha conectado con la BD");
    
    
        try{
            
            
            Administrador admi = new Administrador();
            id_admi = sql.buscarIdAdministradorBD(id_usu);
            
            if(id_admi != 0){
%>
                <h1>Ya tienes una cuenta de Administrador vinculada con este Usuario</h1>
                <br>
                <br>
                <label>Tu id de Administrador es: <%= sql.buscarIdAdministradorBD(id_usu)   %></label>
                


<%
            }else{
          
                sql.agregarAdmin(id_usu);
                
                admi = sql.buscarAdministradorBD(id_admi);
%>
                <h1>Cuenta de Administrador vinculada con exito</h1>

                <br>
                <br>
                <label>Nombre: <%=admi.getNom()%> <%=admi.getApelPat_usu()%> <%=admi.getApelMat_usu()%> </label>
                <br>
                <br>
                <label>Tu id de Usuario: <%= sql.buscarIdUsuarioBD(admi.getNom(), admi.getApelPat_usu(), admi.getApelMat_usu()) %></label>
                <br>
                <br>
                <label>Tu id de Administrador sera: <%= sql.buscarIdAdministradorBD(id_usu)   %></label>
                <h1>No la olvides!!</h1>



    <%
            }
            System.out.println("Se ha registrado correctamente el Usuario");    
        }catch(Exception e){
                System.out.println("No se ha registrado el usuario");
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