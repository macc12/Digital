/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO_SQL.QuejasDAO;
import VO.Quejas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marco
 */
public class QuejasServlet extends HttpServlet {

    QuejasDAO dao = null;

    @Override
    public void init() throws ServletException {
        dao = new QuejasDAO();
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
        if (request.getParameter("enviar") != null) {
            try {
                String correo = request.getParameter("correo");
                String texto = request.getParameter("queja");
                if (correo.length() > 0 && texto.length() > 0 && texto != null && correo != null) {
                    Quejas queja = new Quejas(correo, texto);
                    if (!dao.crear(queja)) {
                        response.sendRedirect("Quejas.jsp?error=ErrorDatos");
                    }
                }else{
                    response.sendRedirect("Quejas.jsp?error=ErrorDatos");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("QuejasServlet");

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
