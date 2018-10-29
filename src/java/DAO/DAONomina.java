package DAO;
import VO.Trabajador;
import java.io.IOException;
import java.util.ArrayList;
public class DAONomina {
    private ArrayList<Trabajador> trabajadores; 
    private Arbol arbol;
    public DAONomina() {
        trabajadores = new ArrayList<>();
        arbol=new Arbol();
    }
   
    public ArrayList<Trabajador> listar() throws IOException{
        trabajadores.addAll(arbol.listarTrab());
        return trabajadores;
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
