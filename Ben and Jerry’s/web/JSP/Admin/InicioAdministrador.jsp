<%-- 
    Document   : InicioAdministrador
    Created on : 9/06/2021, 11:11:20 AM
    Author     : sofo9
--%>

<%@page import="Modelo.Usuario"%>
<%@page import="Controlador.ActUsuario"%>
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
    <title>Benito y Gerardo</title>
    <link rel="stylesheet" href="../../css/estiloAdmin.css">
    <link rel="shortcut icon" href="https://www.drodd.com/images16/pastel-blue6.jpg">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
  </head>
  <body>
    <header>
        <input type="checkbox" id="check">
        <label for="check" class="checkbtn">
            <i class="fas fa-bars"></i>
        </label>
        <a class="logo" href="../../index.html">Benito y Gerardo</a>
    <ul>
        <li><a class="active" href="InicioAdministrador.jsp">Inicio</a></li>
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
                <li>Bienvenido<br><%= usu.getNom_usuario()%></li>
        <% 
            }
        %>
        <li><a class="Header" href="RegistrarProdu.jsp">Registrar Helado</a></li>
        <li><a class="Header" href="EditarProdu.jsp">Editar Helado</a></li>
    </ul>
    </header>
    <section class="contenedorPrincipal">
        <h2 style="text-align: center;">¡Bienvenido!</h2>
        <p class="texto" style="text-align: center; margin-top:40px; margin-left: 40px; margin-right: 40px;">
            Benito y Gerardo es una renombrada heladería internacional que ha hecho sonreir a generaciones.
            <br><br>
            Se parte de la comunidad de trabajadores de Benito y Gerardo.
            <br>
        </p>
        <h2 style="text-align: left; margin-left: 3.89rem;">¡Apoyanos!</h2>
        <div class="div-contenido" style="text-align: center; ">
            <a class="linkPromo" href="JSP/Admin/RegistrarProdu.jsp">
            <div class="div-promoUno">
                <p class="PromoUno" style="vertical-align: middle;">Registrar Productos</p>
            </div>
            </a>
            <a class="linkPromo" href="Tienda.html">
            <div class="div-promoDos">
                <p class="PromoDos" style="vertical-align: middle;">Editar Productos</p>
            </div>
            </a>
            <br><br><br>
            <a class="link" href="Tienda.html">Ver todos los productos</a>
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