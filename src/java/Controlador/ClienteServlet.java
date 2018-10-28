package Controlador;

import VO.Cliente;
import DAO.DAOCliente;
import DAO_SQL.ClienteDAO;
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

/**
 *
 * @author ACER
 */
public class ClienteServlet extends HttpServlet {

    //DAOCliente dao;
    ClienteDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new ClienteDAO();
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
            RequestDispatcher rq = request.getRequestDispatcher("Cliente.jsp");
            if (request.getParameter("borrar") != null) {

                String id = request.getParameter("borrar");
                //Cliente cliente = this.dao.BuscarCliente(Integer.parseInt(id));
                Cliente cliente = this.dao.buscar(Integer.parseInt(id));
                this.dao.borrar(cliente);

            } else if (request.getParameter("editar") != null) {

                String id = request.getParameter("editar");
                Cliente cliente = this.dao.buscar(Integer.parseInt(id));
                request.setAttribute("cliente", cliente);
                rq.forward(request, response);

            }
            ArrayList<Cliente> clientes;

            clientes = (ArrayList<Cliente>) this.dao.listar();

            request.setAttribute("lista", clientes);
            rq.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                String historiaclinica = request.getParameter("historiaclinica");
                if (nombre != null && apellido != null && historiaclinica.length() > 0 && nombre.length() > 0 && apellido.length() > 0) {
                    try {
                        Cliente temp = new Cliente(nombre, apellido, id, historiaclinica);
                        if (!dao.crear(temp)) {
                            response.sendRedirect("Cliente.jsp?error=ErrorDatos");
                        }
                        ArrayList<Cliente> clientes = (ArrayList<Cliente>) this.dao.listar();
                        RequestDispatcher rq = request.getRequestDispatcher("Cliente.jsp");
                        request.setAttribute("lista", clientes);
                        rq.forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    response.sendRedirect("Cliente.jsp?error=IngreseDatos");
                }
            }
            if (request.getParameter("editar") != null) {
                int id = Integer.parseInt(request.getParameter("cedula"));
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String historiaclinica = request.getParameter("historiaclinica");
                if (nombre != null && apellido != null && historiaclinica.length() > 0 && nombre.length() > 0 && apellido.length() > 0) {
                    try {
                        Cliente temp = new Cliente(nombre, apellido, id, historiaclinica);
                        if (!dao.modificar(id, temp)) {
                            response.sendRedirect("Cliente.jsp?error=ErrorDatos");
                        }
                        ArrayList<Cliente> clientes = (ArrayList<Cliente>) this.dao.listar();
                        RequestDispatcher rq = request.getRequestDispatcher("Cliente.jsp");
                        request.setAttribute("lista", clientes);
                        rq.forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    response.sendRedirect("Cliente.jsp?error=IngreseDatos");
                }
            }
            response.sendRedirect("ClienteServlet");
        
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
