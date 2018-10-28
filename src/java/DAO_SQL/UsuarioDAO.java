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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco
 */
public class UsuarioDAO {

    public Usuario buscar(Usuario user) throws SQLException {
        Usuario temp = null;
        PreparedStatement preparedStmt = null;
        String query = "SELECT * FROM usuario where nombreUser = ?";
        Connection connection = Conexion.getConnection();
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getUser());
            ResultSet rs = preparedStmt.executeQuery();
            String usera = null;
            String password = null;
            String tipo = null;
            if(rs.next()){
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
}
