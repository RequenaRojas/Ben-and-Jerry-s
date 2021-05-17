package Servlets;

import Controlador.ActTelefono;
import Controlador.ActUsuario;
import Modelo.Telefono;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class guardarUsuario extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            String nom_usu = request.getParameter("nom_usu");
            String apelPat_usu = request.getParameter("apelPat_usu");
            String apelMat_usu = request.getParameter("apelMat_usu");
            String fechNaci_usu = request.getParameter("fechNaci_usu");
            String domi_usu = request.getParameter("domi_usu");
            String telePart = request.getParameter("telaPart");
            String teleCelu = request.getParameter("teleCelu");
            
            Usuario usu = new Usuario();
            
            usu.setNom(nom_usu);
            usu.setApelPat_usu(apelPat_usu);
            usu.setApelMat_usu(apelMat_usu);
            usu.setFechNaci_usu(fechNaci_usu);
            usu.setDomi_usu(domi_usu);
            
            Telefono tel = new Telefono();
            
            tel.setTelePart(telePart);
            tel.setTeleCelu(teleCelu);
            
            
            ActTelefono.RegiTelefono(tel);
            tel.setId(ActTelefono.getID(tel));
            
            usu.setId_tele(tel.getId());
            
            int estatus = ActUsuario.RegiUsuario(usu);
            if(estatus > 0){
                response.sendRedirect("");
            }else{
                response.sendRedirect("");
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet guardarUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet guardarUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
