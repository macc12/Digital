/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;


public class Citas {
    private String IdCita;
    private int IdCliente ;
    private int IdTrabajador ;
    private String Estado ;
    private String Dia  ;
    private String hora ;
    private String Descripcion ;

    public Citas(String IdCita, int IdCliente, int IdTrabajador, String Estado, String Dia, String hora, String Descripcion) {
        this.IdCita = IdCita;
        this.IdCliente = IdCliente;
        this.IdTrabajador = IdTrabajador;
        this.Estado = Estado;
        this.Dia = Dia;
        this.hora = hora;
        this.Descripcion = Descripcion;
    }

    public Citas() {
    }
    

    public String getIdCita() {
        return IdCita;
    }

    public void setIdCita(String IdCita) {
        this.IdCita = IdCita;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public int getIdTrabajador() {
        return IdTrabajador;
    }

    public void setIdTrabajador(int IdTrabajador) {
        this.IdTrabajador = IdTrabajador;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String Dia) {
        this.Dia = Dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
}
