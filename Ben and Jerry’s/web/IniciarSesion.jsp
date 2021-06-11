<%@page import="Controlador.ActUsuario"%>
<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    boolean sesionIniciada;
    int id_usuario = 0;
    try{
         id_usuario = (Integer) sesion.getAttribute("Usuario");
            sesionIniciada = true;
         if(id_usuario != 0){
             sesionIniciada = true;
             System.out.println("Ya hay una sesion abierta");
         }
    }catch(Exception e){
        System.out.println("No se ha iniciado Sesion");
        sesionIniciada = false;
    }
%>

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Benito y Gerardo</title>
    <link rel="stylesheet" href="./css/estilo.css">
    <link rel="shortcut icon" href="https://th.bing.com/th/id/OIP.KadbGAD98FqNXDfQzQKPYQHaHa?pid=ImgDet&rs=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  </head>
  <body>
    <header class="header-admin">
        <input type="checkbox" id="check">
        <label for="check" class="checkbtn">
            <i class="fas fa-bars"></i>
        </label>
        <a class="logo" href="index.html">Benito y Gerardo</a>
    <ul>
        <li><a class="Header3" href="InicioAdministrador.jsp">Inicio</a></li>
        <% 
            if(sesionIniciada == false){
        %>
                <li><a class="active3" href="IniciarSesion.jsp">Iniciar Sesión</a></li>
        <% 
            }
        %>
        <% 
            if(sesionIniciada == true){
                Usuario usu = ActUsuario.getUsuario(id_usuario);
        %>
                <li class="active3">Bienvenido <%= usu.getNom_usuario()%></li>
        <% 
            }
        %>
        <li><a class="Header3" href="RegistrarProdu.jsp">Registrar Helado</a></li>
        <li><a class="Header3" href="EditarProdu.jsp">Editar Helado</a></li>
    </ul>
    </header>
    <section class="contenedorPrincipal">
        <div class="div-form" style="margin-bottom: 20px;">
            <form  action="\src\java\Servlets\IniciarAdmin.java" method="post" name="formUsu"  >  
                <h2 class="h2-admin" style="text-align: left;">Por favor, ingrese sus datos:</h2>
                <br><br>
                <label class="nomCam">User:</label>
                <br>
                <input class="campo" type="text" name="user_usuario" pattern="^(?=.{1,10}$)[a-zA-ZáéíóúüñÁÉÍÓÚÑ]+(?:[\s][a-zA-ZáéíóúüñÁÉÍÓÚÑ]+)*$" >
                <br><br>
                <label class="nomCam">Contraseña:</label>
                <br>
                <input class="campo" type="password" name="pass_usuario"  pattern="^(?=.{1,10}$)[a-zA-ZáéíóúüñÁÉÍÓÚÑ]+(?:[\s][a-zA-ZáéíóúüñÁÉÍÓÚÑ]+)*$">
                <br>
                <br>
                <input class="botón3" type="submit" value="Iniciar Sesión">
                <br><br>
                <input class="botón3" type="reset" value="Borrar datos de registro">
            </form>
        </div>   
        <div class="div-contenido" style="text-align: center;">
            
            <br>
            <h2 class="h2-admin">
                ¿No tienes cuenta de Administrador?
            </h2>
            <br><br>
            <a class="link3" href="RegistrarAdmin.jsp">¡Unetenos!</a>
            <br><br><br>
        </div>
    </section>
    <footer class="footer-admin">
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