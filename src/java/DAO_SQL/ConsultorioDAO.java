/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_SQL;

import VO.Consultorio;
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
public class ConsultorioDAO implements IBaseDatos<Consultorio>{

    @Override
    public List<Consultorio> listar() throws SQLException {
        List<Consultorio> consultorios = null;
        String query = "SELECT * FROM Consultorio";
        Connection connection = Conexion.getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            String nombre = null;
            int id = 0;
            while(rs.next()){
                if(consultorios == null){
                    consultorios = new ArrayList<>();
                }
                Consultorio aux = new Consultorio();
                
                nombre = rs.getString("NombreConsultorio");
                id = rs.getInt("IdConsultorio");
                
                aux.setNombre(nombre);
                aux.setId(id);
                consultorios.add(aux);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener la lista de Consultorios");
            e.printStackTrace();
        }
        return consultorios;
    }

    @Override
    public boolean crear(Consultorio t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = " insert into Consultorio" + " values (?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getNombre());
            preparedStmt.setInt(2, t.getId());
            
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
    public boolean borrar(Consultorio t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "delete from Consultorio where IdConsultorio = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, t.getId());
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
    public boolean modificar(int cl, Consultorio t) throws SQLException {
        int result = 0;
        Connection connection = Conexion.getConnection();
        String query = "update Consultorio set NombreConsultorio = ? where IdConsultorio = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, t.getNombre());
            preparedStmt.setInt(2, cl);
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
    public Consultorio buscar(int cl) throws SQLException {
        Consultorio temp = null;
        PreparedStatement preparedStmt = null;
        String query = "SELECT * FROM Consultorio where IdConsultorio = ?";
        Connection connection = Conexion.getConnection();
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, cl);
            ResultSet rs = preparedStmt.executeQuery(); 
            String nombre = null;
            int id = 0;
            while(rs.next()){
                temp = new Consultorio();
                
                nombre = rs.getString("NombreConsultorio");
                id = rs.getInt("IdConsultorio");
                
                temp.setNombre(nombre);
                temp.setId(id);
                
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    
}
