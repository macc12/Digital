/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_SQL;

import VO.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marco
 */
public class ClienteDAO implements IBaseDatos<Cliente> {

    @Override
    public List<Cliente> listar() throws SQLException {
        List<Cliente> clientes = null;
        String query = "SELECT * FROM Cliente";
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String nombre = null;
            String apellido = null;
            String HistoriaClinica = null;
            while (rs.next()) {
                if (clientes == null) {
                    clientes = new ArrayList<>();
                }
                Cliente aux = new Cliente();

                nombre = rs.getString("NombreCliente");
                aux.setNombre(nombre);

                apellido = rs.getString("ApellidoCliente");
                aux.setApellido(apellido);

                id = rs.getInt("IdCliente");
                aux.setId(id);

                HistoriaClinica = rs.getString("HistoriaClinica");
                aux.setHistoriaClinica(HistoriaClinica);

                clientes.add(aux);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener la lista de Cliente");
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public boolean crear(Cliente t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = " insert into Cliente" + " values (?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getNombre());
            preparedStmt.setString(2, t.getApellido());
            preparedStmt.setInt(3, t.getId());
            preparedStmt.setString(4, t.getHistoriaClinica());

            result = preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result == 0) {
            return false;            
        } else {
            return true;
        }
    }

    @Override
    public boolean borrar(Cliente t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "delete from Cliente where IdCliente = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getId());
            result = preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Cliente buscar(int cl) throws SQLException {
        Cliente temp = null;
        PreparedStatement preparedStmt = null;
        String query = "SELECT * FROM Cliente where IdCliente = ?";
        Connection connection = Conexion.getConnection();
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, cl);
            ResultSet rs = preparedStmt.executeQuery();            
            int id = 0;
            String nombre = null;
            String apellido = null;
            String HistoriaClinica = null;            
            if (rs.next()) {
                temp = new Cliente();
                
                nombre = rs.getString("NombreCliente");
                temp.setNombre(nombre);

                apellido = rs.getString("ApellidoCliente");
                temp.setApellido(apellido);

                id = rs.getInt("IdCliente");
                temp.setId(id);

                HistoriaClinica = rs.getString("HistoriaClinica");
                temp.setHistoriaClinica(HistoriaClinica);                                
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public boolean modificar(int cl, Cliente t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "update Cliente set NombreCliente = ?, ApellidoCliente = ?, HistoriaClinica = ? where IdCliente = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getNombre());
            preparedStmt.setString(2, t.getApellido());
            preparedStmt.setString(3, t.getHistoriaClinica());
            preparedStmt.setInt(4, cl);
            result = preparedStmt.executeUpdate();                

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result == 0) {
            return false;
        } else {
            return true;
        }        
    }

    

}
