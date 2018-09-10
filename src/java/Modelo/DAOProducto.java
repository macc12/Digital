package Modelo;

import Modelo.Producto;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class DAOProducto {
     private ArrayList<Producto> prodcutos; 

    public DAOProducto() {
        prodcutos = new ArrayList<>();
    }
    
    public boolean crear(Producto prod){
        if(buscar(prod.getId())==null){
            prodcutos.add(prod);
            return true;
        }
        return false;
    }
    
    public ArrayList<Producto> listar(){
        return prodcutos;
    }
    
    public boolean borrar(Producto prod){
        return this.prodcutos.remove(prod);
    }
    
    public boolean modificar(int id, Producto prod){
        for (int i = 0; i < prodcutos.size(); i++) {
            Producto aux = prodcutos.get(i); 
            if(aux.getId()==id){
                this.prodcutos.get(i).setNombre(prod.getNombre());
                this.prodcutos.get(i).setId(prod.getId());
                this.prodcutos.get(i).setPrecio(prod.getPrecio());
                this.prodcutos.get(i).setEstado(prod.getEstado());  
            }                        
        }
        return false;
    }
    
    public Producto buscar(int id){
        for (int i = 0; i < prodcutos.size(); i++) {            
            if(prodcutos.get(i).getId()==id){
                return prodcutos.get(i);
            }
        }
        return null;
    }
    }