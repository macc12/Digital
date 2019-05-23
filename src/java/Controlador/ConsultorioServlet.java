/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO_SQL.ConsultorioDAO;
import VO.Consultorio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ConsultorioServlet extends HttpServlet {

    ConsultorioDAO dao = null;

    @Override
    public void init() throws ServletException {
        dao = new ConsultorioDAO();
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
        RequestDispatcher rq = request.getRequestDispatcher("Consultorios.jsp");
        try {
            if (request.getParameter("borrar") != null) {
                int cl = Integer.parseInt(request.getParameter("borrar"));
                Consultorio temp = this.dao.buscar(cl);
                dao.borrar(temp);
            } else if (request.getParameter("editar") != null) {
                int id = Integer.parseInt(request.getParameter("editar"));
                Consultorio temp = this.dao.buscar(id);
                request.setAttribute("consultorio", temp);
                rq.forward(request, response);
            }
            ArrayList<Consultorio> consultorios;
            consultorios = (ArrayList<Consultorio>) this.dao.listar();
            request.setAttribute("lista", consultorios);
            rq.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (request.getParameter("enviarCons") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("name");
            if (id > 0 && nombre.length() > 0 && nombre != null) {
                try {
                    Consultorio temp = new Consultorio(nombre, id);
                    if (!dao.crear(temp)) {
                        response.sendRedirect("Consultorios.jsp?error=ErrorDatos");
                    }
                    ArrayList<Consultorio> consultorios = (ArrayList<Consultorio>) dao.listar();
                    RequestDispatcher rq = request.getRequestDispatcher("Consultorios.jsp");
                    request.setAttribute("lista", consultorios);
                    rq.forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                response.sendRedirect("Consultorios.jsp?errorendatos");
            }
        }

        if (request.getParameter("ModCon") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("name");
            if (id > 0 && nombre.length() > 0 && nombre != null) {
                try {
                    Consultorio temp = new Consultorio(nombre, id);
                    if (!dao.modificar(id, temp)) {
                        response.sendRedirect("Consultorios.jsp?error=ErrorDatos");
                    }
                    ArrayList<Consultorio> consultorios = (ArrayList<Consultorio>) dao.listar();
                    RequestDispatcher rq = request.getRequestDispatcher("Consultorios.jsp");
                    request.setAttribute("lista", consultorios);
                    rq.forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                response.sendRedirect("Consultorios.jsp?errorendatos");
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
