/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_SQL;

import VO.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco
 */
public class UsuarioDAO implements IBaseDatos<Usuario> {

    public Usuario buscarU(Usuario user) throws SQLException {
        Usuario temp = null;
        PreparedStatement preparedStmt = null;
        String query = "SELECT * FROM usuario where nombreUser = ? and contrase = ?";
        Connection connection = Conexion.getConnection();
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getUser());
            preparedStmt.setString(2, user.getPassword());
            ResultSet rs = preparedStmt.executeQuery();
            String usera = null;
            String password = null;
            String tipo = null;
            if (rs.next()) {
                temp = new Usuario();

                usera = rs.getString("nombreUser");
                temp.setUser(usera);

                password = rs.getString("contrase");
                temp.setPassword(password);

                tipo = rs.getString("tipouser");
                temp.setTipo(tipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    public Usuario buscarCl(String name) throws SQLException {
        Usuario temp = null;
        PreparedStatement preparedStmt = null;
        String query = "SELECT * FROM usuario where nombreUser = name";
        Connection connection = Conexion.getConnection();
        try {
            preparedStmt = connection.prepareStatement(query);            
            
            ResultSet rs = preparedStmt.executeQuery();
            String usera = null;
            String password = null;
            String tipo = null;
            if (rs.next()) {
                temp = new Usuario();

                usera = rs.getString("nombreUser");
                temp.setUser(usera);

                password = rs.getString("contrase");
                temp.setPassword(password);

                tipo = rs.getString("tipouser");
                temp.setTipo(tipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = null;
        String query = "SELECT * FROM usuario";
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            String user = null;
            String pass = null;
            String tipo = null;

            while (rs.next()) {
                if (usuarios == null) {
                    usuarios = new ArrayList<>();
                }
                user = rs.getString("nombreUser");
                pass = rs.getString("contrase");
                tipo = rs.getString("tipouser");

                Usuario aux = new Usuario(user, pass, tipo);

                usuarios.add(aux);
            }

        } catch (Exception e) {
            System.out.println("Problemas al obtener la lista de usuarios");
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public boolean crear(Usuario t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = " insert into usuario" + " values (?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getUser());
            preparedStmt.setString(2, t.getPassword());
            preparedStmt.setString(3, t.getTipo());

            result = preparedStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean borrar(Usuario t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "delete from usuario where nombreUser = ? and contrase = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getUser());
            preparedStmt.setString(2, t.getPassword());
            result = preparedStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean modificar(int cl, Usuario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /*
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "update usuario set contrase = ?, tipouser = ? where nombreUser = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getPassword());
            preparedStmt.setString(2, t.getTipo());
            preparedStmt.setString(3, t.getUser());

            result = preparedStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == 0) {
            return false;
        } else {
            return true;
        }
         */
    }

    @Override
    public Usuario buscar(int cl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean modificarU(String cl, Usuario t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "update usuario set contrase = ?, tipouser = ? where nombreUser = cl";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getPassword());
            preparedStmt.setString(2, t.getTipo());

            result = preparedStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

}
