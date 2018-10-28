/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_SQL;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Marco
 */
public interface IBaseDatos<T> {
    List<T> listar() throws SQLException;
    boolean crear(T t) throws SQLException; 
    boolean borrar(T t) throws SQLException;
    boolean modificar(int cl ,T t) throws SQLException;
    T buscar(int cl) throws SQLException;
}
