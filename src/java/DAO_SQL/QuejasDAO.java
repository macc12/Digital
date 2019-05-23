/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_SQL;

import VO.Quejas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class QuejasDAO implements IBaseDatos<Quejas> {

    @Override
    public List<Quejas> listar() throws SQLException {
        List<Quejas> quejas = null;
        String query = "SELECT * FROM quejas";
        try {
            Connection connection = Conexion.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            String correo, texto = null;
            while (rs.next()) {
                if (quejas == null) {
                    quejas = new ArrayList<>();
                }
                Quejas aux = new Quejas();

                correo = rs.getString("correo");
                aux.setCorreo(correo);

                texto = rs.getString("texto");
                aux.setTexto(texto);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener la lista de Quejas");
            e.printStackTrace();
        }
        return quejas;
    }

    @Override
    public boolean crear(Quejas t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = " insert into quejas" + " values (?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getCorreo());
            preparedStmt.setString(2, t.getTexto());
            
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
    public boolean borrar(Quejas t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(int cl, Quejas t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Quejas buscar(int cl) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
