/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO_SQL.UsuarioDAO;
import VO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marco
 */
public class UsuarioServlet extends HttpServlet {
    UsuarioDAO dao;

    public void init() throws ServletException {
        dao = new UsuarioDAO();
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
            String usuario = request.getParameter("usuario");
            String pass = request.getParameter("password");
            if(usuario.length() > 0 && usuario != null && pass.length() > 0 && pass != null){
                try {
                    Usuario us = new Usuario(usuario, pass);
                    Usuario temp = dao.buscar(us);
                    if(temp!=null){
                        if(temp.getTipo().compareTo("Administrador")==0){
                            response.sendRedirect("Index.jsp");
                        }
                        if(temp.getTipo().compareTo("AuxContable")==0){
                            response.sendRedirect("IAuxCont.jsp");
                        }
                        if(temp.getTipo().compareTo("AuxAdmi")==0){
                            response.sendRedirect("IAuxAdmini.jsp");
                        }
                    }else{
                        response.sendRedirect("LogIn.jsp?errorendatos");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
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
