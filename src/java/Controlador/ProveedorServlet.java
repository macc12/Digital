/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import DAO_SQL.ConsultorioDAO;
import DAO_SQL.ProveedorDAO;
import VO.Consultorio;
import VO.Proveedor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProveedorServlet extends HttpServlet {

    //DAOProvedor dao;
    ProveedorDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new ProveedorDAO();
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
        try {

            if (request.getParameter("init") != null) {
                ConsultorioDAO daoc = new ConsultorioDAO();
                try {
                    ArrayList<Consultorio> consultorios = (ArrayList<Consultorio>) daoc.listar();
                    RequestDispatcher rq = request.getRequestDispatcher("Proveedor.jsp");
                    request.setAttribute("consultorios", consultorios);
                    rq.forward(request, response);
                    response.sendRedirect("Proveedor.jsp");
                } catch (SQLException ex) {
                    Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
            RequestDispatcher rq = request.getRequestDispatcher("Proveedor.jsp");
            if (request.getParameter("borrar") != null) {

                String id = request.getParameter("borrar");
                Proveedor proveedor = this.dao.buscar(Integer.parseInt(id));
                this.dao.borrar(proveedor);

            } else if (request.getParameter("editar") != null) {

                String id = request.getParameter("editar");
                Proveedor proveedor = this.dao.buscar(Integer.parseInt(id));
                request.setAttribute("proveedor", proveedor);
                rq.forward(request, response);
                                
            }
            ArrayList<Proveedor> proveedores = (ArrayList<Proveedor>) this.dao.listar();
            request.setAttribute("lista", proveedores);
            rq.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        if (request.getParameter("enviar") != null) {
            ConsultorioDAO daoc = new ConsultorioDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String consul = request.getParameter("consultorio");
            Consultorio aux = null;
            try {
                aux = daoc.buscarc(consul);
            } catch (SQLException ex) {
                Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            int idco = aux.getId();
            int telefono = Integer.parseInt(request.getParameter("telefono"));
            if (nombre != null && telefono != 0 && nombre.length() > 0 && direccion.length() > 0 /*&& productos !=null*/) {
                try {
                    Proveedor temp = new Proveedor(nombre, id, telefono, direccion, new ArrayList<>());
                    temp.setConsultorio(idco);
                    if (!dao.crear(temp)) {
                        response.sendRedirect("Proveedor.jsp?error=ErrorDatos");
                    }
                    ArrayList<Proveedor> provedores = (ArrayList<Proveedor>) this.dao.listar();
                    RequestDispatcher rq = request.getRequestDispatcher("Proveedor.jsp");
                    request.setAttribute("lista", provedores);
                    rq.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response.sendRedirect("Proveedor.jsp?error=IngreseDatos");
            }
        }
        if (request.getParameter("editar") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            int telefono = Integer.parseInt(request.getParameter("telefono"));
            if (nombre != null && telefono != 0 && nombre.length() > 0 && direccion.length() > 0 /*&& productos !=null*/) {
                try {
                    Proveedor temp = new Proveedor(nombre, id, telefono, direccion, new ArrayList<>());
                    if (!dao.modificar(id, temp)) {
                        response.sendRedirect("Proveedor.jsp?error=ErrorDatos");
                    }
                    ArrayList<Proveedor> proveedores = (ArrayList<Proveedor>) this.dao.listar();
                    RequestDispatcher rq = request.getRequestDispatcher("Proveedor.jsp");
                    request.setAttribute("lista", proveedores);
                    rq.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response.sendRedirect("Proveedor.jsp?error=IngreseDatos");
            }
        }

        response.sendRedirect("ProveedorServlet");
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
