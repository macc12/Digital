package DAO;
import VO.Provedor;
import java.io.IOException;
import java.util.ArrayList;
public class DAOProvedor {
private ArrayList<Provedor> provedores; 
   private Arbol arbol;
    public DAOProvedor() {
        provedores = new ArrayList<>();
         arbol=new Arbol();
    } 
    public boolean CrearProveedor(Provedor prov) throws IOException{
        if(BuscarProveedor(prov.getId())==null){
            provedores.add(prov);
            arbol.agregar("Provedor", prov.getId(), prov);
            return true;
        }
        return false;
    }    
    public ArrayList<Provedor> ListarProveedor() throws IOException{
        provedores.addAll(arbol.listarProv());
        return provedores;
    } 
    public boolean BorrarProveedor(Provedor trab) throws IOException{
      //  arbol.borrarProvedor(trab.getId());
        return this.provedores.remove(trab);
    }
    public boolean ModificarProveedor(int id, Provedor traba) throws IOException{
        for (int i = 0; i < provedores.size(); i++) {
            Provedor aux = provedores.get(i); 
            if(aux.getId()==id){
                this.provedores.get(i).setNombre(traba.getNombre());
                this.provedores.get(i).setId(traba.getId());
                this.provedores.get(i).setProductos(traba.getProductos());
                arbol.Modificar(traba, traba.getId());
            }                        
        }
        return false;
    }
    public Provedor BuscarProveedor(int id){
        for (int i = 0; i < provedores.size(); i++) {            
            if(provedores.get(i).getId()==id){
                return provedores.get(i);
            }
        }
        return null;
    }
}
