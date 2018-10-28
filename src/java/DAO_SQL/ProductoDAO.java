/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_SQL;

import VO.Producto;
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
public class ProductoDAO implements IBaseDatos<Producto> {

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = null;
        String query = "SELECT * FROM Producto";
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            String nombre = null;
            int id = 0;
            double precio = 0;
            String estado = null;
            int cantidad = 0;

            while (rs.next()) {
                if (productos == null) {
                    productos = new ArrayList<>();
                }
                Producto aux = new Producto();

                nombre = rs.getString("NombreProducto");
                aux.setNombre(nombre);

                id = rs.getInt("IdProducto");
                aux.setId(id);

                precio = rs.getDouble("Precio");
                aux.setPrecio(precio);
                
                estado = rs.getString("Estado");
                aux.setEstado(estado);
                
                cantidad = rs.getInt("Cantidad");
                aux.setCantidad(cantidad);
                
                productos.add(aux);
            }

            rs.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener la lista de Producto");
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public boolean crear(Producto t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = " insert into Producto" + " values (?,?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getNombre());
            preparedStmt.setInt(2, t.getId());
            preparedStmt.setDouble(3, t.getPrecio());
            preparedStmt.setString(4, t.getEstado());
            preparedStmt.setInt(5, t.getCantidad());

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
    public boolean borrar(Producto t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "delete from Producto where IdProducto = ?";
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
    public boolean modificar(int cl, Producto t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "update Producto set NombreProducto = ?, Precio = ?, Estado = ?, Cantidad = ? where IdProducto = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getNombre());
            preparedStmt.setDouble(2, t.getPrecio());
            preparedStmt.setString(3, t.getEstado());
            preparedStmt.setInt(4, t.getCantidad());
            preparedStmt.setInt(5, cl);
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
    public Producto buscar(int cl) throws SQLException {
        Producto temp = null;
        PreparedStatement preparedStmt = null;
        String query = "SELECT * FROM Producto where IdProducto = ?";
        Connection connection = Conexion.getConnection();
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, cl);
            ResultSet rs = preparedStmt.executeQuery();            
            String nombre = null;
            int id = 0;
            double precio = 0;
            String estado = null;
            int cantidad = 0;      
            if (rs.next()) {
                temp = new Producto();
                
                nombre = rs.getString("NombreProducto");
                temp.setNombre(nombre);

                id = rs.getInt("IdProducto");
                temp.setId(id);

                precio = rs.getDouble("Precio");
                temp.setPrecio(precio);
                
                estado = rs.getString("Estado");
                temp.setEstado(estado);
                
                cantidad = rs.getInt("Cantidad");
                temp.setCantidad(cantidad);                                
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

}
