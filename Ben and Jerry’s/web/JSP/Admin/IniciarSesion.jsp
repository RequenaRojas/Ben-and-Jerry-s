<%-- 
    Document   : IniciarSesion
    Created on : 9/06/2021, 01:01:17 PM
    Author     : sofo9
--%>

<%@page import="Controlador.ActUsuario"%>
<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--Validación de la sesión-->
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



<!DOCTYPE html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de usuario</title>
    <link rel="stylesheet" href="../../css/estiloAdmin.css">
    <link rel="shortcut icon" href="https://www.drodd.com/images16/pastel-blue6.jpg">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  </head>
  <body>
    <header>
        <input type="checkbox" id="check">
        <label for="check" class="checkbtn">
            <i class="fas fa-bars"></i>
        </label>
        <a class="logo" href="../../index.html">Benito y Gerardo</a>
    <ul>
        <li><a class="Header" href="InicioAdministrador.jsp">Inicio</a></li>
        <% 
            if(sesionIniciada == false){
        %>
                <li><a class="active" href="IniciarSesion.jsp">Iniciar Sesión</a></li>
        <% 
            }
        %>
        <% 
            if(sesionIniciada == true){
                Usuario usu = ActUsuario.getUsuario(id_usuario);
        %>
                <li class="active">Bienvenido<br><%= usu.getNom_usuario()%></li>
        <% 
            }
        %>
        <li><a class="Header" href="RegistrarProdu.jsp">Registrar Helado</a></li>
        <li><a class="Header" href="EditarProdu.jsp">Editar Helado</a></li>
    </ul>
    </header>
    <section class="contenedorPrincipal">
        <div class="div-form">
            <form  action="../../IniciarAdmin" method="post" name="formUsu"  >  
                <h2 style="text-align: left;">Por favor, ingrese sus datos:</h2>
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
                <input class="botón" type="submit" value="Iniciar Sesión">
                <br><br>
                <input class="botón" type="reset" value="Borrar datos de registro">
            </form>
        </div>   
        <div class="div-contenido" style="text-align: center;">
            
            <br>
            <p class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;">
                ¿No tienes cuenta de Administrador?
            </p>
            <a class="link" href="RegistrarAdmin.jsp">¡Unetenos!</a>
            <br><br><br>
        </div>
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
