/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO_SQL.ConsultorioDAO;
import DAO_SQL.TrabajadorDAO;
import VO.Consultorio;
import VO.Trabajador;
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


public class TrabajadorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    TrabajadorDAO dao;

    public void init() throws ServletException {
        dao = new TrabajadorDAO();
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
                    RequestDispatcher rq = request.getRequestDispatcher("Trabajador.jsp");
                    request.setAttribute("consultorios", consultorios);
                    rq.forward(request, response);
                    response.sendRedirect("Trabajador.jsp");
                } catch (SQLException ex) {
                    Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (request.getParameter("initp") != null) {
                ConsultorioDAO daoc = new ConsultorioDAO();
                try {
                    ArrayList<Trabajador> trabajadors = (ArrayList<Trabajador>) dao.listar();
                    RequestDispatcher rq = request.getRequestDispatcher("Prestamos.jsp");
                    request.setAttribute("trabajadores", trabajadors);
                    rq.forward(request, response);
                    response.sendRedirect("Prestamos.jsp");
                } catch (SQLException ex) {
                    Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (request.getParameter("borrarp") != null) {
                int id = Integer.parseInt(request.getParameter("borrarp"));
                this.dao.elimpres(id);
                ArrayList<Trabajador> trabajadors = (ArrayList<Trabajador>) dao.listar();
                RequestDispatcher rq = request.getRequestDispatcher("Prestamos.jsp");
                request.setAttribute("trabajadores", trabajadors);
                rq.forward(request, response);
            } else if (request.getParameter("editarp") != null){
                int id = Integer.parseInt(request.getParameter("editarp"));                                
                Trabajador trabajador = this.dao.buscar(id);
                RequestDispatcher rq = request.getRequestDispatcher("Prestamos.jsp");
                request.setAttribute("trabajador", trabajador);
                rq.forward(request, response);
            }
            RequestDispatcher   rq = request.getRequestDispatcher("Trabajador.jsp");
            if (request.getParameter("borrar") != null) {

                String id = request.getParameter("borrar");
                //Cliente cliente = this.dao.BuscarCliente(Integer.parseInt(id));
                Trabajador trabajador = this.dao.buscar(Integer.parseInt(id));
                this.dao.borrar(trabajador);

            } else if (request.getParameter("editar") != null) {

                String id = request.getParameter("editar");
                Trabajador trabajador = this.dao.buscar(Integer.parseInt(id));
                request.setAttribute("trabajador", trabajador);
                rq.forward(request, response);

            }
            ArrayList<Trabajador> trabajadors;

            trabajadors = (ArrayList<Trabajador>) this.dao.listar();

            request.setAttribute("lista", trabajadors);
            rq.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TrabajadorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            int id = Integer.parseInt(request.getParameter("id"));
            String cargo = request.getParameter("cargo");
            double sueldo = Double.parseDouble(request.getParameter("salario"));
            int diasTraba = Integer.parseInt(request.getParameter("DiasTrabajados"));
            double deuda = Double.parseDouble(request.getParameter("Deuda"));
            double pagado = Double.parseDouble(request.getParameter("Pagado"));
            String consul = request.getParameter("consultorio");
            Consultorio aux = null;
            try {
                aux = daoc.buscarc(consul);
            } catch (SQLException ex) {
                Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            int idco = aux.getId();
            if (nombre != null && apellido != null && nombre.length() > 0 && apellido.length() > 0 && id > 0 && sueldo > 0
                    && cargo.length() > 0 && diasTraba > 0) {
                try {
                    Trabajador temp = new Trabajador(id, nombre, apellido, cargo, sueldo, diasTraba, deuda, pagado);
                    temp.setConsultorio(idco);
                    if (!dao.crear(temp)) {
                        response.sendRedirect("Trabajador.jsp?error=ErrorDatos");
                    }
                    ArrayList<Trabajador> trabajadors = (ArrayList<Trabajador>) this.dao.listar();
                    RequestDispatcher rq = request.getRequestDispatcher("Trabajador.jsp");
                    request.setAttribute("lista", trabajadors);
                    rq.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(TrabajadorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response.sendRedirect("Trabajador.jsp?error=IngreseDatos");
            }
        }
        if (request.getParameter("editar") != null) {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            int id = Integer.parseInt(request.getParameter("id"));
            String cargo = request.getParameter("cargo");
            double sueldo = Double.parseDouble(request.getParameter("salario"));
            int diasTraba = Integer.parseInt(request.getParameter("DiasTrabajados"));
            double deuda = Double.parseDouble(request.getParameter("Deuda"));
            double pagado = Double.parseDouble(request.getParameter("Pagado"));
            if (nombre != null && apellido != null && nombre.length() > 0 && apellido.length() > 0 && id > 0 && sueldo > 0
                    && cargo.length() > 0 && diasTraba > 0) {
                try {
                    Trabajador temp = new Trabajador(id, nombre, apellido, cargo, sueldo, diasTraba, deuda, pagado);
                    if (!dao.modificar(id, temp)) {
                        response.sendRedirect("Trabajador.jsp?error=ErrorDatos");
                    }
                    ArrayList<Trabajador> trabajadors = (ArrayList<Trabajador>) this.dao.listar();
                    RequestDispatcher rq = request.getRequestDispatcher("Trabajador.jsp");
                    request.setAttribute("lista", trabajadors);
                    rq.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(TrabajadorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response.sendRedirect("Trabajador.jsp?error=IngreseDatos");
            }
        }
        //response.sendRedirect("TrabajadorServlet");
        if (request.getParameter("crepres") != null) {
            int id = Integer.parseInt(request.getParameter("trabajador"));
            double deuda = Double.parseDouble(request.getParameter("cantidad"));
            if (id > 0 && deuda > 0) {
                try {
                    if (!dao.editpres(id, deuda)) {
                        response.sendRedirect("Prestamos.jsp?error=IngreseDatos");
                    }
                    ArrayList<Trabajador> trabajadors = (ArrayList<Trabajador>) this.dao.listarpres();
                    RequestDispatcher rq = request.getRequestDispatcher("Prestamos.jsp");
                    request.setAttribute("lista", trabajadors);
                    rq.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(TrabajadorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response.sendRedirect("Prestamos.jsp?error=IngreseDatos");
            }
        }
        if (request.getParameter("Modpre") != null) {
            int id = Integer.parseInt(request.getParameter("trabajador"));
            double deuda = Double.parseDouble(request.getParameter("cantidad"));
            if (id > 0 && deuda > 0) {
                try {
                    if (!dao.editpres(id, deuda)) {
                        response.sendRedirect("Prestamos.jsp?error=IngreseDatos");
                    }
                    ArrayList<Trabajador> trabajadors = (ArrayList<Trabajador>) this.dao.listarpres();
                    RequestDispatcher rq = request.getRequestDispatcher("Prestamos.jsp");
                    request.setAttribute("lista", trabajadors);
                    rq.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(TrabajadorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response.sendRedirect("Prestamos.jsp?error=IngreseDatos");
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
