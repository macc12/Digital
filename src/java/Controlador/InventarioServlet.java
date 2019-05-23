/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO_SQL.ConsultorioDAO;
import DAO_SQL.ProductoDAO;
import VO.Consultorio;
import VO.Producto;
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


public class InventarioServlet extends HttpServlet {

    ProductoDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new ProductoDAO();
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
        if (request.getParameter("init") != null) {
            ConsultorioDAO daoc = new ConsultorioDAO();
            try {
                ArrayList<Consultorio> consultorios = (ArrayList<Consultorio>) daoc.listar();
                RequestDispatcher rq = request.getRequestDispatcher("Inventario.jsp");
                request.setAttribute("consultorios", consultorios);
                rq.forward(request, response);
                response.sendRedirect("Inventario.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestDispatcher rq = request.getRequestDispatcher("Inventario.jsp");
            if (request.getParameter("borrar") != null) {
            try {
                String id = request.getParameter("borrar");
                Producto producto = this.dao.buscar(Integer.parseInt(id));
                this.dao.actualizar(0, producto);
            } catch (SQLException ex) {
                Logger.getLogger(InventarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else if (request.getParameter("editar") != null) {
            try {
                String id = request.getParameter("editar");
                Producto producto = this.dao.buscar(Integer.parseInt(id));
                request.setAttribute("producto", producto);
                rq.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(InventarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        if (request.getParameter("enviarCon") != null) {
            ConsultorioDAO daoc = new ConsultorioDAO();
            String consul = request.getParameter("consultorio");
            Consultorio aux = null;
            try {
                aux = daoc.buscarc(consul);
                ArrayList productos = (ArrayList) dao.listarc(aux.getId());
                RequestDispatcher rq = request.getRequestDispatcher("Inventario.jsp");
                request.setAttribute("lista", productos);
                rq.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (request.getParameter("ModPro") != null) {
            ConsultorioDAO daoc = new ConsultorioDAO();
            int idprod = Integer.parseInt(request.getParameter("idprod"));
            double precio = Double.parseDouble(request.getParameter("Precio"));
            int cantidad = Integer.parseInt(request.getParameter("Precio"));
            int idcon = Integer.parseInt(request.getParameter("idcons"));
            Producto t = new Producto();
            t.setPrecio(precio);
            t.setCantidad(cantidad);
            t.setId(idprod);
            try {
                if (idprod > 0 && precio > 0 && cantidad > 0) {
                    if (!dao.actualizar(idprod, t)) {
                        response.sendRedirect("Inventario.jsp?error=ErrorDatos");
                    }
                    ArrayList productos = (ArrayList) dao.listarc(idcon);
                    RequestDispatcher rq = request.getRequestDispatcher("Inventario.jsp");
                    request.setAttribute("lista", productos);
                    rq.forward(request, response);
                } else {
                    response.sendRedirect("Inventario.jsp?error=IngreseDatos");
                }
            } catch (Exception e) {
                e.printStackTrace();
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
