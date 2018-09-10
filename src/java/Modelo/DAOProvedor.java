/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class DAOProvedor {
private ArrayList<Provedor> provedores; 

    public DAOProvedor() {
        provedores = new ArrayList<>();
    }
    
    public boolean crearTrabajador(Provedor prov){
        if(buscarprove(prov.getId())==null){
            provedores.add(prov);
            return true;
        }
        return false;
    }
    
    public ArrayList<Provedor> listar(){
        return provedores;
    }
    
    public boolean borrar(Provedor trab){
        return this.provedores.remove(trab);
    }
    
    public boolean modificar(int id, Provedor traba){
        for (int i = 0; i < provedores.size(); i++) {
            Provedor aux = provedores.get(i); 
            if(aux.getId()==id){
                this.provedores.get(i).setNombre(traba.getNombre());
                this.provedores.get(i).setId(traba.getId());
                this.provedores.get(i).setProductos(traba.getProductos());
            }                        
        }
        return false;
    }
    
    public Provedor buscarprove(int id){
        for (int i = 0; i < provedores.size(); i++) {            
            if(provedores.get(i).getId()==id){
                return provedores.get(i);
            }
        }
        return null;
    }
}
