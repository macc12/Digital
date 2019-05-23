/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_SQL;

import VO.Citas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CitasDAO  {

    
    public List<Citas> listar() throws SQLException {
        List<Citas> citas = null;
        String query = "SELECT * FROM Citas";
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            String IdCita= "";
            int IdCliente =0;
            int IdTrabajador=0;
            String Estado= "";
            String Dia= "";
            String hora= "";
            String Descripcion= "";
            while (rs.next()) {
                if (citas == null) {
                    citas = new ArrayList<>();
                }
                Citas aux = new Citas();

                IdCita = rs.getString("IdCita");
                aux.setIdCita(IdCita);

                IdCliente = rs.getInt("IdCliente");
                aux.setIdCliente(IdCliente);

                IdTrabajador = rs.getInt("IdTrabajador");
                aux.setIdTrabajador(IdTrabajador);

                Estado = rs.getString("Estado");
                aux.setEstado(Estado);
                
                Dia = rs.getString("Dia");
                aux.setDia(Dia);
                
                hora = rs.getString("hora");
                aux.setHora(hora);
                
                Descripcion = rs.getString("Descripcion");
                aux.setDescripcion(Descripcion);
                
                citas.add(aux);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener la lista de Citas");
            e.printStackTrace();
        }
        return citas;
    }

    
    public boolean crear(Citas t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = " insert into Citas" + " values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getIdCita());
            preparedStmt.setInt(2, t.getIdCliente());
            preparedStmt.setInt(3, t.getIdTrabajador());
            preparedStmt.setString(4, t.getEstado());
            preparedStmt.setString(5, t.getDia ());
            preparedStmt.setString(6, t.getHora());
            preparedStmt.setString(7, t.getDescripcion());

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

   
    public boolean borrar(Citas t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "delete from Citas where IdCita = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getIdCita());
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

    public Citas buscar(String ct) throws SQLException {
        Citas temp = null;
        PreparedStatement preparedStmt = null;
        String query = "SELECT * FROM Citas where IdCita = ?";
        Connection connection = Conexion.getConnection();
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, ct);
            ResultSet rs = preparedStmt.executeQuery();
            String IdCita= "";
            int IdCliente =0;
            int IdTrabajador=0;
            String Estado= "";
            String Dia= "";
            String hora= "";
            String Descripcion= "";
            if (rs.next()) {
                temp = new Citas();

                
                IdCita = rs.getString("IdCita");
                temp.setIdCita(IdCita);

                IdCliente = rs.getInt("IdCliente");
                temp.setIdCliente(IdCliente);

                IdTrabajador = rs.getInt("IdTrabajador");
                temp.setIdTrabajador(IdTrabajador);

                Estado = rs.getString("Estado");
                temp.setEstado(Estado);
                
                Dia = rs.getString("Dia");
                temp.setDia(Dia);
                
                hora = rs.getString("hora");
                temp.setHora(hora);
                
                Descripcion = rs.getString("Descripcion");
                temp.setDescripcion(Descripcion);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    
    public boolean modificar(String cl, Citas t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "update Citas set IdCliente = ?,  IdTrabajador   = ?, Estado = ?, Dia = ?, hora = ?,Descripcion   = ? where IdCita = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getIdCliente());
            preparedStmt.setInt(2, t.getIdTrabajador());
            preparedStmt.setString(3, t.getEstado());
            preparedStmt.setString(4, t.getDia ());
            preparedStmt.setString(5, t.getHora());
            preparedStmt.setString(6, t.getDescripcion());
            preparedStmt.setString(7,cl);
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
