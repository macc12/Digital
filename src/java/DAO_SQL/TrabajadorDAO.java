/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_SQL;

import VO.Trabajador;
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
public class TrabajadorDAO implements IBaseDatos<Trabajador> {

    @Override
    public List<Trabajador> listar() throws SQLException {
        List<Trabajador> trabajadors = null;
        String query = "SELECT * FROM Trabajador";
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            String nombre = null;
            String apellido = null;
            int id = 0;
            String cargo = null;
            double sueldo = 0;
            int diasTraba = 0;
            double deuda = 0;
            double pagado = 0;

            while (rs.next()) {
                if (trabajadors == null) {
                    trabajadors = new ArrayList<>();
                }
                Trabajador aux = new Trabajador();

                nombre = rs.getString("NombreTrabajador");
                aux.setNombre(nombre);

                apellido = rs.getString("ApellidoTrabajador");
                aux.setApellido(apellido);

                id = rs.getInt("IdTrabajador");
                aux.setId(id);

                cargo = rs.getString("Cargo");
                aux.setCargo(cargo);

                sueldo = rs.getDouble("Sueldo");
                aux.setSueldo(sueldo);

                diasTraba = rs.getInt("DiasTrabajados");
                aux.setDiasTraba(diasTraba);

                deuda = rs.getDouble("Deuda");
                aux.setDeuda(deuda);

                pagado = rs.getDouble("Pagado");
                aux.setPagado(pagado);

                trabajadors.add(aux);
            }

            rs.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener la lista de Cliente");
            e.printStackTrace();
        }
        return trabajadors;
    }

    @Override
    public boolean crear(Trabajador t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = " insert into Trabajador" + " values (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getNombre());
            preparedStmt.setString(2, t.getApellido());
            preparedStmt.setInt(3, t.getId());
            preparedStmt.setString(4, t.getCargo());
            preparedStmt.setDouble(5, t.getSueldo());
            preparedStmt.setInt(6, t.getDiasTraba());
            preparedStmt.setDouble(7, t.getDeuda());
            preparedStmt.setDouble(8, t.getPagado());

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
    public boolean borrar(Trabajador t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "delete from Trabajador where IdTrabajador = ?";
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
    public boolean modificar(int cl, Trabajador t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "update Trabajador set NombreTrabajador = ?, ApellidoTrabajador = ?, Cargo = ?, Sueldo = ?, DiasTrabajados = ?, Deuda = ?, Pagado = ? where IdTrabajador = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getNombre());
            preparedStmt.setString(2, t.getApellido());
            preparedStmt.setString(3, t.getCargo());
            preparedStmt.setDouble(4, t.getSueldo());
            preparedStmt.setInt(5, t.getDiasTraba());
            preparedStmt.setDouble(6, t.getDeuda());
            preparedStmt.setDouble(7, t.getPagado());
            preparedStmt.setInt(8, cl);
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
    public Trabajador buscar(int cl) throws SQLException {
        Trabajador temp = null;
        PreparedStatement preparedStmt = null;
        String query = "SELECT * FROM Trabajador where IdTrabajador = ?";
        Connection connection = Conexion.getConnection();
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, cl);
            ResultSet rs = preparedStmt.executeQuery();            
            String nombre = null;
            String apellido = null;
            int id = 0;
            String cargo = null;
            double sueldo = 0;
            int diasTraba = 0;
            double deuda = 0;
            double pagado = 0;           
            if (rs.next()) {
                temp = new Trabajador();
                
                nombre = rs.getString("NombreTrabajador");
                temp.setNombre(nombre);

                apellido = rs.getString("ApellidoTrabajador");
                temp.setApellido(apellido);

                id = rs.getInt("IdTrabajador");
                temp.setId(id);

                cargo = rs.getString("Cargo");
                temp.setCargo(cargo);

                sueldo = rs.getDouble("Sueldo");
                temp.setSueldo(sueldo);

                diasTraba = rs.getInt("DiasTrabajados");
                temp.setDiasTraba(diasTraba);

                deuda = rs.getDouble("Deuda");
                temp.setDeuda(deuda);

                pagado = rs.getDouble("Pagado");
                temp.setPagado(pagado);
                                                
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
}
