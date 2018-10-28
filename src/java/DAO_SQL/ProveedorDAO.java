/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_SQL;

import VO.Proveedor;
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
public class ProveedorDAO implements IBaseDatos<Proveedor>{

    @Override
    public List<Proveedor> listar() throws SQLException {
        List<Proveedor> proveedors = null;
        String query = "SELECT * FROM Proveedor";
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            int id = 0;
            String nombre = null;
            int telefono = 0;
            String direccion = null;
            while (rs.next()) {
                if (proveedors == null) {
                    proveedors = new ArrayList<>();
                }
                Proveedor aux = new Proveedor();

                nombre = rs.getString("NombreProveedor");
                aux.setNombre(nombre);

                id = rs.getInt("IdProveedor");
                aux.setId(id);
                
                telefono = rs.getInt("Telefono");
                aux.setTelefono(telefono);
                
                direccion = rs.getString("Direccion");
                aux.setDireccion(direccion);

                proveedors.add(aux);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener la lista de Proveedor");
            e.printStackTrace();
        }
        return proveedors;
    }

    @Override
    public boolean crear(Proveedor t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = " insert into Proveedor" + " values (?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getNombre());
            preparedStmt.setInt(2, t.getId());
            preparedStmt.setInt(3, t.getTelefono());
            preparedStmt.setString(4, t.getDireccion());

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
    public boolean borrar(Proveedor t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "delete from Proveedor where IdProveedor = ?";
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
    public boolean modificar(int cl, Proveedor t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "update Proveedor set NombreProveedor = ?, Telefono = ?, Direccion = ? where IdProveedor = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getNombre());
            preparedStmt.setInt(2, t.getTelefono());
            preparedStmt.setString(3, t.getDireccion());
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

    @Override
    public Proveedor buscar(int cl) throws SQLException {
        Proveedor temp = null;
        PreparedStatement preparedStmt = null;
        String query = "SELECT * FROM Proveedor where IdProveedor = ?";
        Connection connection = Conexion.getConnection();
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, cl);
            ResultSet rs = preparedStmt.executeQuery();            
            int id = 0;
            String nombre = null;
            int telefono = 0;
            String direccion = null;        
            if (rs.next()) {
                temp = new Proveedor();
                
                nombre = rs.getString("NombreProveedor");
                temp.setNombre(nombre);

                id = rs.getInt("IdProveedor");
                temp.setId(id);
                
                telefono = rs.getInt("Telefono");
                temp.setTelefono(telefono);
                
                direccion = rs.getString("Direccion");
                temp.setDireccion(direccion);                               
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    
}
