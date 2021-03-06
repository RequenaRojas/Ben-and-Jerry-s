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
                <li><a class="Header" href="IniciarSesion.jsp">Iniciar Sesión</a></li>
        <% 
            }
        %>
        <% 
            if(sesionIniciada == true){
                Usuario usu = ActUsuario.getUsuario(id_usuario);
        %>
                <li class="Header">Bienvenido<br><%= usu.getNom_usuario()%></li>
        <% 
            }
        %>
        <li><a class="Header" href="RegistrarProdu.jsp">Registrar Helado</a></li>
        <li><a class="Header" href="EditarProdu.jsp">Editar Helado</a></li>
    </ul>
    </header>
    <section class="contenedorPrincipal">
        
        <div class="div-contenido" style="text-align: center;">
            <h2 style="text-align: center; ">Obtener una Cuenta de Administrador</h2>
            <br>
            <p class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;">
                Para obtener una cuenta de Administrador deberá leer Primero los términos y condiciones
                <br>
                <br>
                <i>* Términos y Condiciones que nínguna persona lee porque es mucho texto *</i>
                <br>
                <br>
                Con tan solo <b>inicie sesión en está sección</b>, podrá acceder a todas los permisos de un Administrador. 
            </p>
            
            <br>
        </div>
        
        <!--Fomulario-->
        <div class="div-form">
            <form  name="formUsu"  action="../../DarAlta" method="post">  
                <label class="nomCam">User:</label>
                <br>
                <input class="campo" type="text" name="user_usuario" pattern="^[a-zA-ZÀ-ÿ\s]{4,50}$" >
                <br><br>
                <label class="nomCam">Contraseña:</label>
                <br>
                <input class="campo" type="password" name="pass_usuario"  pattern="^[a-zA-ZÀ-ÿ\s]{4,50}$">
                <br>
                <br>
                <input class="botón" type="submit" value="Dar de alta Mi Sesión">
                <br><br>
                <input class="botón" type="reset" value="Borrar Campos">
            </form>
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