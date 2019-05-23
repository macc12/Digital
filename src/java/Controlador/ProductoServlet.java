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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProductoServlet extends HttpServlet {

   
    //DAOProducto dao;
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
        try {
            
            if (request.getParameter("init") != null) {
                ConsultorioDAO daoc = new ConsultorioDAO();
                try {
                    ArrayList<Consultorio> consultorios = (ArrayList<Consultorio>) daoc.listar();
                    RequestDispatcher rq = request.getRequestDispatcher("Producto.jsp");
                    request.setAttribute("consultorios", consultorios);
                    rq.forward(request, response);
                    response.sendRedirect("Producto.jsp");
                } catch (SQLException ex) {
                    Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            RequestDispatcher rq = request.getRequestDispatcher("Producto.jsp");
            if (request.getParameter("borrar") != null) {
                String id = request.getParameter("borrar");
                Producto producto = this.dao.buscar(Integer.parseInt(id));
                this.dao.borrar(producto);
            }else if (request.getParameter("editar") != null) {
                String id = request.getParameter("editar");
                Producto producto = this.dao.buscar(Integer.parseInt(id));
                request.setAttribute("producto", producto);
                rq.forward(request, response);
            }
            ArrayList<Producto> productos = (ArrayList<Producto>) this.dao.listar();
            request.setAttribute("lista", productos);
            rq.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            String estado = request.getParameter("estado");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            double precio = Double.parseDouble(request.getParameter("precio"));
            String consul = request.getParameter("consultorio");
            Consultorio aux = null;
            try {
                aux = daoc.buscarc(consul);
            } catch (SQLException ex) {
                Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            int idco = aux.getId();
            if (nombre != null && precio > 0 && cantidad > 0&& nombre.length() > 0  && estado.length() > 0) {
                try {
                    Producto temp = new Producto(nombre, id, precio, estado, cantidad);
                    temp.setConsultorio(idco);
                    if (!dao.crear(temp)) {
                        response.sendRedirect("Producto.jsp?error=ErrorDatos");
                    }
                    ArrayList<Producto> productos = (ArrayList<Producto>) this.dao.listar();
                    RequestDispatcher rq = request.getRequestDispatcher("Producto.jsp");
                    request.setAttribute("lista", productos);
                    rq.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response.sendRedirect("Producto.jsp?error=IngreseDatos");
            }
        }
        if (request.getParameter("editar") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String estado = request.getParameter("estado");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            double precio = Double.parseDouble(request.getParameter("precio")); 
            if (nombre != null && precio > 0 && cantidad > 0&& nombre.length() > 0  && estado.length() > 0) {
                try {
                    Producto temp = new Producto(nombre, id, precio, estado, cantidad);
                    if (!dao.modificar(id, temp)) {
                        response.sendRedirect("Producto.jsp?error=ErrorDatos");
                    }
                    ArrayList<Producto> prodcutos = (ArrayList<Producto>) this.dao.listar();
                    RequestDispatcher rq = request.getRequestDispatcher("Producto.jsp");
                    request.setAttribute("lista", prodcutos);
                    rq.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response.sendRedirect("Producto.jsp?error=IngreseDatos");
            }
        }
        response.sendRedirect("Productoservlet");
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
