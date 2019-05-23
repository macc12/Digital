/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO_SQL.UsuarioDAO;
import VO.Usuario;
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
import javax.servlet.http.HttpSession;


public class UsuarioServlet extends HttpServlet {

    UsuarioDAO dao;

    public void init() throws ServletException {
        dao = new UsuarioDAO();
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
            RequestDispatcher rqa = request.getRequestDispatcher("Index.jsp");
            if (request.getParameter("cerrarses") != null) {
                HttpSession sesionUsuario = request.getSession();
                Usuario _sesionUsuario = (Usuario) sesionUsuario.getAttribute("usuario");
                if (_sesionUsuario != null) {
                    sesionUsuario.invalidate();
                    response.sendRedirect("LogIn.jsp");
                }
            }
            RequestDispatcher rq = request.getRequestDispatcher("ContUsuarios.jsp");
            if (request.getParameter("borrar") != null) {
                String cl = request.getParameter("borrar");
                Usuario temp = this.dao.buscarCl(cl);
                dao.borrar(temp);
            } else if (request.getParameter("editar") != null) {
                String id = request.getParameter("editar");
                Usuario temp = this.dao.buscarCl(id);
                request.setAttribute("usuario", temp);
                rq.forward(request, response);
            }
            ArrayList<Usuario> usuarios;
            usuarios = (ArrayList<Usuario>) this.dao.listar();
            request.setAttribute("lista", usuarios);
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
        if (request.getParameter("enviar") != null) {
            String usuario = request.getParameter("usuario");
            String pass = request.getParameter("password");
            if (usuario.length() > 0 && usuario != null && pass.length() > 0 && pass != null) {
                try {
                    Usuario us = new Usuario(usuario, pass);
                    Usuario temp = dao.buscarU(us);
                    if (temp != null) {
                        if (temp.getTipo().compareTo("Administrador") == 0) {
                            HttpSession sesionUsuario = request.getSession();
                            Usuario _sesionUsuario = (Usuario) sesionUsuario.getAttribute("usuario");
                            if (_sesionUsuario == null) {
                                //El usuario no a creado la sesion
                                if (temp != null) {
                                    sesionUsuario.setAttribute("usuario", temp);
                                    sesionUsuario.setMaxInactiveInterval(20);
                                    response.sendRedirect("Index.jsp");
                                } else {
                                    response.sendRedirect("LogIn.jsp");
                                }
                            } else {
                                response.sendRedirect("Index.jsp");
                            }
                            if (temp != null) {

                            } else {
                                request.setAttribute("Error", "Revisar usuario/ pass");
                                RequestDispatcher rq = request.getRequestDispatcher("LogIn.jsp");
                                rq.forward(request, response);
                            }
                        }
                        if (temp.getTipo().compareTo("AuxContable") == 0) {
                            response.sendRedirect("IAuxCont.jsp");
                        }
                        if (temp.getTipo().compareTo("AuxAdmi") == 0) {
                            HttpSession sesionUsuario = request.getSession();
                            Usuario _sesionUsuario = (Usuario) sesionUsuario.getAttribute("usuario");
                            if (_sesionUsuario == null) {
                                //El usuario no a creado la sesion
                                if (temp != null) {
                                    sesionUsuario.setAttribute("usuario", temp);
                                    sesionUsuario.setMaxInactiveInterval(20);
                                    response.sendRedirect("Index.jsp");
                                } else {
                                    response.sendRedirect("LogIn.jsp");
                                }
                            } else {
                                response.sendRedirect("Index.jsp");
                            }
                            if (temp != null) {

                            } else {
                                request.setAttribute("Error", "Revisar usuario/ pass");
                                RequestDispatcher rq = request.getRequestDispatcher("LogIn.jsp");
                                rq.forward(request, response);
                            }
                        }
                    } else {
                        response.sendRedirect("LogIn.jsp?errorendatos");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (request.getParameter("enviarReg") != null) {
            String usuario = request.getParameter("usuario");
            String pass = request.getParameter("password");
            String passr = request.getParameter("passwordr");
            String tipo = request.getParameter("tipoUser");
            if (usuario.length() > 0 && usuario != null && pass.length() > 0 && pass != null && pass.compareTo(passr) == 0) {
                try {
                    Usuario temp = new Usuario(passr, passr, tipo);
                    if (!dao.crear(temp)) {
                        response.sendRedirect("ContUsuarios.jsp?error=ErrorDatos");
                    }
                    ArrayList<Usuario> usuarios = (ArrayList<Usuario>) this.dao.listar();
                    RequestDispatcher rq = request.getRequestDispatcher("ContUsuarios.jsp");
                    request.setAttribute("lista", usuarios);
                    rq.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response.sendRedirect("ContUsuarios.jsp?errorendatos");
            }
        }

        if (request.getParameter("ModReg") != null) {
            String usuario = request.getParameter("usuario");
            String pass = request.getParameter("password");
            String passr = request.getParameter("passwordr");
            String tipo = request.getParameter("tipoUser");
            if (usuario.length() > 0 && usuario != null && pass.length() > 0 && pass != null && pass.compareTo(passr) == 0) {
                try {
                    Usuario temp = new Usuario(passr, passr, tipo);
                    if (!dao.modificarU(usuario, temp)) {
                        response.sendRedirect("ContUsuarios.jsp?error=ErrorDatos");
                    }
                    ArrayList<Usuario> usuarios = (ArrayList<Usuario>) this.dao.listar();
                    RequestDispatcher rq = request.getRequestDispatcher("ContUsuarios.jsp");
                    request.setAttribute("lista", usuarios);
                    rq.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response.sendRedirect("ContUsuarios.jsp?errorendatos");
            }
        }
        //response.sendRedirect("UsuarioServlet");
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
