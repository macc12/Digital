/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAOProvedor;
import Modelo.Provedor;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
public class ProvedorServet extends HttpServlet {

    DAOProvedor dao;

    @Override
    public void init() throws ServletException {
        dao = new DAOProvedor();
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
         RequestDispatcher rq = request.getRequestDispatcher("Provedorjsp.jsp");
        if (request.getParameter("borrar") != null) {
            String id = request.getParameter("borrar");
            Provedor provedor = this.dao.BuscarProveedor(Integer.parseInt(id));
            this.dao.BorrarProveedor(provedor);
        }else if (request.getParameter("editar") != null) {
            String id = request.getParameter("editar");
            Provedor provedor = this.dao.BuscarProveedor(Integer.parseInt(id));
            request.setAttribute("provedor", provedor);
            rq.forward(request, response);
        }
        ArrayList<Provedor> provedores = this.dao.ListarProveedor();
        request.setAttribute("lista", provedores);
        rq.forward(request, response);
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
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String direccion= request.getParameter("direccion");
            int telefono = Integer.parseInt(request.getParameter("telefono"));
            if (nombre != null  && telefono != 0 && nombre.length() > 0 && direccion.length() > 0  /*&& productos !=null*/) {
                Provedor temp = new Provedor(nombre, id, telefono, direccion, new ArrayList<>());
                if (!dao.CrearProveedor(temp)) {
                    response.sendRedirect("Provedorjsp.jsp?error=ErrorDatos");
                }
                ArrayList<Provedor> provedores = this.dao.ListarProveedor();
                RequestDispatcher rq = request.getRequestDispatcher("Provedorjsp.jsp");
                request.setAttribute("lista", provedores);
                rq.forward(request, response);
            } else {
                response.sendRedirect("Provedorjsp.jsp?error=IngreseDatos");
            }
        }
        if (request.getParameter("editar") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String direccion= request.getParameter("direccion");
            int telefono = Integer.parseInt(request.getParameter("telefono"));
            if (nombre != null  && telefono != 0 && nombre.length() > 0 && direccion.length() > 0  /*&& productos !=null*/) {
                Provedor temp = new Provedor(nombre, id, telefono, direccion, new ArrayList<>());
                if (!dao.ModificarProveedor(id, temp)) {
                    response.sendRedirect("Provedorjsp.jsp?error=ErrorDatos");
                }
                ArrayList<Provedor> provedores = this.dao.ListarProveedor();
                RequestDispatcher rq = request.getRequestDispatcher("Provedorjsp.jsp");
                request.setAttribute("lista", provedores);
                rq.forward(request, response);
            } else {
                response.sendRedirect("Provedorjsp.jsp?error=IngreseDatos");
            }
        }
        response.sendRedirect("ProvedorServlet");
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
