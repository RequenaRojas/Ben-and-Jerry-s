
package Servlets;

import Controlador.ActUsuario;
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
public class DarAlta extends HttpServlet {

    
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
        /* TODO output your page here. You may use following sample code. */
         
            String nombre_usuario = request.getParameter("nombre_usuario");
            String pass_usuario = request.getParameter("pass_usuario");
            Usuario usu = null;
            try{ 
                usu = Usuario.verificarUsuario(nombre_usuario, pass_usuario);
            }catch(Exception e){
                System.out.println("Error al verificar el usuario");
                response.sendRedirect("Error/ErrorVerificarUsuario.html");
            }
            try{
//            Asigno el Privilegio
            usu.setPrivilegio(2);
            
            ActUsuario.ActualizarUsuario(usu.getId_usuario(), usu);
            }catch(Exception e){
                System.out.println("Error al actualicar el Usuario el usuario");
                response.sendRedirect("Error/ErrorVerificarUsuario.html");
            }
            
            
               
            if(usu != null){
                try{ 
//                      Cambio de Usuario
                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("Usuario", usu.getId_usuario());
                    System.out.println("Se ha dado de alta al Usuario con exito");                
                    response.sendRedirect("JSP/Admin/InicioAdministrador.jsp");

                 }catch(Exception e){
                    System.out.println("Error");
                    response.sendRedirect("Error/Error.html");
                }
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
