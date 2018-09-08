/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Nicolas
 */
public class Trabajador {
    private String nombre;
    private String apellido;
    private int id;
    private String cargo;
    private double sueldo;
    private int diasTraba;
    private double deuda;
    private double pagado;

    public Trabajador(String nombre, String apellido, String cargo, double sueldo, int id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public int getDiasTraba() {
        return diasTraba;
    }

    public void setDiasTraba(int diasTraba) {
        this.diasTraba = diasTraba;
    }

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(double pagado) {
        this.pagado = pagado;
    }
    
}
