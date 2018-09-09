/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAOTrabajador;
import Modelo.Trabajador;
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
 * @author Nicolas
 */
public class IniServlet extends HttpServlet {

    DAOTrabajador dao;

    @Override
    public void init() throws ServletException {
        dao = new DAOTrabajador();
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
        if (request.getParameter("borrar") != null) {
            String id = request.getParameter("borrar");
            Trabajador trabaja = this.dao.buscarTraba(Integer.parseInt(id));
            this.dao.borrar(trabaja);

            ArrayList<Trabajador> trabajadores = this.dao.listar();
            RequestDispatcher rq = request.getRequestDispatcher("Trabajador.jsp");
            request.setAttribute("lista", trabajadores);
            rq.forward(request, response);

        }

        if (request.getParameter("editar") != null) {
            String id = request.getParameter("editar");
            Trabajador trabajador = this.dao.buscarTraba(Integer.parseInt(id));

            RequestDispatcher rq = request.getRequestDispatcher("Trabajador.jsp");
            request.setAttribute("trabajador", trabajador);
            rq.forward(request, response);

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
            int id = Integer.parseInt(request.getParameter("cedula"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            double salario = Double.parseDouble(request.getParameter("salario"));
            String cargo = request.getParameter("cargo");
            if (nombre != null && apellido != null && salario != 0 && nombre.length() > 0 && apellido.length() > 0) {
                Trabajador temp = new Trabajador(nombre, apellido, cargo, salario, id);
                if (!dao.crearTrabajador(temp)) {
                    response.sendRedirect("Trabajador.jsp?error=ErrorDatos");
                }
                ArrayList<Trabajador> trabajadores = this.dao.listar();
                RequestDispatcher rq = request.getRequestDispatcher("Trabajador.jsp");
                request.setAttribute("lista", trabajadores);
                rq.forward(request, response);
            } else {
                response.sendRedirect("Trabajador.jsp?error=IngreseDatos");
            }
        }
        if (request.getParameter("modificar") != null) {
            int id = Integer.parseInt(request.getParameter("cedula"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            double salario = Double.parseDouble(request.getParameter("salario"));
            String cargo = request.getParameter("cargo");
            if (nombre != null && apellido != null && salario != 0 && nombre.length() > 0 && apellido.length() > 0) {
                Trabajador temp = new Trabajador(nombre, apellido, cargo, salario, id);
                if (!dao.modificar(id, temp)) {
                    response.sendRedirect("Trabajador.jsp?error=ErrorDatos");
                }
                ArrayList<Trabajador> trabajadores = this.dao.listar();
                RequestDispatcher rq = request.getRequestDispatcher("Trabajador.jsp");
                request.setAttribute("lista", trabajadores);
                rq.forward(request, response);
            } else {
                response.sendRedirect("Trabajador.jsp?error=IngreseDatos");
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
