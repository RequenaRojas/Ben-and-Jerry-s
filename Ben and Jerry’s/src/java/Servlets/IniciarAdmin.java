package Servlets;

import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofo9
 */
public class IniciarAdmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
           
           
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
             /* TODO output your page here. You may use following sample code. */
           String user_usuario = request.getParameter("user_usuario");
           String pass_usuario = request.getParameter("pass_usuario");
           
           Usuario usu = null;
           try{
               usu = Usuario.verificarUsuario(user_usuario, pass_usuario);
           }catch(Exception e){
               System.out.println("Error al verificar al Usuario");
           }
           
           if(usu == null){response.sendRedirect("Error/ErrorVerificarUsuario.html");}
           else if(usu.getPrivilegio() == 1){response.sendRedirect("Error/ErrorSinPrivilegio.html");}
           else if(usu.getPrivilegio() == 2){
               
               HttpSession sesion = request.getSession(true);
               sesion.setAttribute("Usuario", usu.getId_usuario());
               response.sendRedirect("JSP/Admin/InicioAdministrador.jsp");
           
           }else{response.sendRedirect("Error/Error.html");}
        }catch(Exception e){
            System.out.println("Error con todo alv");
            System.out.println(e.getMessage());
            response.sendRedirect("../../Error/Error.html");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}