/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public class DAOTrabajador {
    private ArrayList<Trabajador> trabajadores; 

    public DAOTrabajador() {
        trabajadores = new ArrayList<>();
    }
    
    public boolean crearTrabajador(Trabajador trab){
        if(buscarTraba(trab.getId())==null){
            trabajadores.add(trab);
            return true;
        }
        return false;
    }
    
    public ArrayList<Trabajador> listar(){
        return trabajadores;
    }
    
    public boolean borrar(Trabajador trab){
        return this.trabajadores.remove(trab);
    }
    
    public boolean modificar(int id, Trabajador traba){
        for (int i = 0; i < trabajadores.size(); i++) {
            Trabajador aux = trabajadores.get(i); 
            if(aux.getId()==id){
                this.trabajadores.get(i).setNombre(traba.getNombre());
                this.trabajadores.get(i).setApellido(traba.getApellido());
                this.trabajadores.get(i).setId(traba.getId());
                this.trabajadores.get(i).setCargo(traba.getCargo());
                this.trabajadores.get(i).setSueldo(traba.getSueldo());
                return true;
            }                        
        }
        return false;
    }
    
    public Trabajador buscarTraba(int id){
        for (int i = 0; i < trabajadores.size(); i++) {            
            if(trabajadores.get(i).getId()==id){
                return trabajadores.get(i);
            }
        }
        return null;
    }
}
