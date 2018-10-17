package DAO;
import VO.Trabajador;
import java.io.IOException;
import java.util.ArrayList;
public class DAOTrabajador {
    private ArrayList<Trabajador> trabajadores; 
    private Arbol arbol;
    public DAOTrabajador() {
        trabajadores = new ArrayList<>();
        arbol=new Arbol();
    }
    
    public boolean crearTrabajador(Trabajador trab) throws IOException{
        if(buscarTraba(trab.getId())==null){
            trabajadores.add(trab);
            arbol.agregar("Trabajador", trab.getId(), trab);
            return true;
        }
        return false;
    }
    
    public ArrayList<Trabajador> listar() throws IOException{
        trabajadores.addAll(arbol.listarTrab());
        return trabajadores;
    }
    
    public boolean borrar(Trabajador trab) throws IOException{
     //   arbol.borrarTrabajador(trab.getId());
        return this.trabajadores.remove(trab);
    }
    
    public boolean modificar(int id, Trabajador traba) throws IOException{
        for (int i = 0; i < trabajadores.size(); i++) {
            Trabajador aux = trabajadores.get(i); 
            if(aux.getId()==id){
                this.trabajadores.get(i).setNombre(traba.getNombre());
                this.trabajadores.get(i).setApellido(traba.getApellido());
                this.trabajadores.get(i).setId(traba.getId());
                this.trabajadores.get(i).setCargo(traba.getCargo());
                this.trabajadores.get(i).setSueldo(traba.getSueldo());
                arbol.Modificar(traba, traba.getId());
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
