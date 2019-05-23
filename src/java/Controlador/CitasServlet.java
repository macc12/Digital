/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import DAO_SQL.CitasDAO;
import VO.Citas;
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


public class CitasServlet extends HttpServlet {

    //DAOCitas dao;
    CitasDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new CitasDAO();
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
            RequestDispatcher rq = request.getRequestDispatcher("Citas.jsp");
            if (request.getParameter("borrar") != null) {

                String id = request.getParameter("borrar");
                //Citas cita = this.dao.Buscar cita(Integer.parseInt(id));
                Citas cita = this.dao.buscar(id);
                this.dao.borrar(cita);

            } else if (request.getParameter("editar") != null) {

                String id = request.getParameter("editar");
                Citas cita = this.dao.buscar(id);
                request.setAttribute("Cita", cita);
                rq.forward(request, response);

            }
            ArrayList<Citas> citas;

            citas = (ArrayList<Citas>) this.dao.listar();

            request.setAttribute("lista", citas);
            rq.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CitasServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                String IdCita= request.getParameter("IdCita");
                int IdCliente = Integer.parseInt(request.getParameter("IdCliente"));
                int  IdTrabajador  = Integer.parseInt(request.getParameter("IdTrabajador"));
                String Estado = request.getParameter("Estado");
                String Dia = request.getParameter("Dia");
                String hora = request.getParameter("hora");
                String  Descripcion = request.getParameter("Descripcion");
                if ( IdCliente != 0 && IdTrabajador !=0 && Estado.length() > 0 && Dia.length() > 0 && hora.length() > 0 && Descripcion.length() > 0 ) {
                    try {
                        Citas temp = new Citas( IdCita, IdCliente,  IdTrabajador,Estado, Dia,  hora,  Descripcion);
                        if (!dao.crear(temp)) {
                            response.sendRedirect("Citas.jsp?error=ErrorDatos");
                        }
                        ArrayList<Citas> citas = (ArrayList<Citas>) this.dao.listar();
                        RequestDispatcher rq = request.getRequestDispatcher("Citas.jsp");
                        request.setAttribute("lista", citas);
                        rq.forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(CitasServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    response.sendRedirect("Citas.jsp?error=IngreseDatos");
                }
            }
            if (request.getParameter("editar") != null) {
                String IdCita= request.getParameter("IdCita");
                int IdCliente = Integer.parseInt(request.getParameter("IdCliente"));
                int  IdTrabajador  = Integer.parseInt(request.getParameter("IdTrabajador"));
                String Estado = request.getParameter("Estado");
                String Dia = request.getParameter("Dia");
                String hora = request.getParameter("hora");
                String  Descripcion = request.getParameter("Descripcion");
                 if ( IdCliente != 0 && IdTrabajador !=0 && Estado.length() > 0 && Dia.length() > 0 && hora.length() > 0 && Descripcion.length() > 0 ) {
                    try {
                        Citas temp = new Citas( IdCita, IdCliente,  IdTrabajador,Estado, Dia,  hora,  Descripcion);
                        if (!dao.modificar(IdCita, temp)) {
                            response.sendRedirect("Citas.jsp?error=ErrorDatos");
                        }
                        ArrayList<Citas> citas = (ArrayList<Citas>) this.dao.listar();
                        RequestDispatcher rq = request.getRequestDispatcher("Citas.jsp");
                        request.setAttribute("lista", citas);
                        rq.forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(CitasServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    response.sendRedirect("Citas.jsp?error=IngreseDatos");
                }
            }
            response.sendRedirect("CitasServlet");
        
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