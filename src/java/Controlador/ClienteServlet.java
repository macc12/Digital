package Controlador;
import VO.Cliente;
import DAO.DAOCliente;
import java.io.IOException;
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
public class ClienteServlet extends HttpServlet {

    DAOCliente dao;

    @Override
    public void init() throws ServletException {
        dao = new DAOCliente();
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
         RequestDispatcher rq = request.getRequestDispatcher("Clientejsp.jsp");
        if (request.getParameter("borrar") != null) {
            String id = request.getParameter("borrar");
            Cliente cliente = this.dao.BuscarCliente(Integer.parseInt(id));
            this.dao.BorrarCliente(cliente);
        }else if (request.getParameter("editar") != null) {
            String id = request.getParameter("editar");
            Cliente cliente = this.dao.BuscarCliente(Integer.parseInt(id));
            request.setAttribute("Cliente", cliente);
            rq.forward(request, response);
        }
        ArrayList<Cliente> clientes = this.dao.ListarCliente();
        request.setAttribute("lista", clientes);
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
            int id = Integer.parseInt(request.getParameter("cedula"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String historiaclinica = request.getParameter("historiaclinica");
            if (nombre != null && apellido != null && historiaclinica.length() > 0 && nombre.length() > 0 && apellido.length() > 0) {
                Cliente temp = new Cliente(nombre, apellido, id, historiaclinica);
                if (!dao.CrearCliente(temp)) {
                    response.sendRedirect("Clientejsp.jsp?error=ErrorDatos");
                }
                ArrayList<Cliente> clientes = this.dao.ListarCliente();
                RequestDispatcher rq = request.getRequestDispatcher("Clientejsp.jsp");
                request.setAttribute("lista", clientes);
                rq.forward(request, response);
            } else {
                response.sendRedirect("Clientejsp.jsp?error=IngreseDatos");
            }
        }
        if (request.getParameter("editar") != null) {
            int id = Integer.parseInt(request.getParameter("cedula"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String historiaclinica = request.getParameter("historiaclinica");
            if (nombre != null && apellido != null && historiaclinica.length() > 0 && nombre.length() > 0 && apellido.length() > 0) {
                Cliente temp = new Cliente(nombre, apellido, id, historiaclinica);
                if (!dao.ModificarCliente(id, temp)) {
                    response.sendRedirect("Clientejsp.jsp?error=ErrorDatos");
                }
                ArrayList<Cliente> clientes = this.dao.ListarCliente();
                RequestDispatcher rq = request.getRequestDispatcher("Clientejsp.jsp");
                request.setAttribute("lista", clientes);
                rq.forward(request, response);
            } else {
                response.sendRedirect("Clientejsp.jsp?error=IngreseDatos");
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
