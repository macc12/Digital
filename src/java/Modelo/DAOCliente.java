package Modelo;
import java.io.IOException;
import java.util.ArrayList;
public class DAOCliente {
    private ArrayList<Cliente> Clientes; 
    private Arbol arbol;
    public DAOCliente() {
        Clientes= new ArrayList<>();
        arbol=new Arbol();
    }
    public boolean CrearCliente(Cliente ObjCliente) throws IOException{
        if(BuscarCliente(ObjCliente.getId())==null){
            Clientes.add(ObjCliente);
             arbol.agregar("Cliente",ObjCliente.getId(), ObjCliente);
            return true;
        }
        return false;
    }   
    public ArrayList<Cliente> ListarCliente() throws IOException{
        Clientes.addAll(arbol.listarCli());
        return Clientes;
    }  
    public boolean BorrarCliente(Cliente ObjCliente) throws IOException{
      //  arbol.borrarCliente(ObjCliente.getId());
        return this.Clientes.remove(ObjCliente);
    } 
    public boolean ModificarCliente(int id,Cliente ObjCliente) throws IOException{
        for (int i = 0; i <Clientes.size(); i++) {
            Cliente ObjAux = Clientes.get(i); 
            if(ObjAux.getId()==id){
                this.Clientes.get(i).setNombre(ObjCliente.getNombre());
                this.Clientes.get(i).setApellido(ObjCliente.getApellido());
                this.Clientes.get(i).setId(ObjCliente.getId());
                this.Clientes.get(i).setHistoriaClinica(ObjCliente.getHistoriaClinica());
                 arbol.Modificar(ObjCliente, ObjCliente.getId());
                return true;
            }                        
        }
        return false;
    }
    public Cliente BuscarCliente(int id){
        for (int i = 0; i <Clientes.size(); i++) {            
            if(Clientes.get(i).getId()==id){
                return Clientes.get(i);
            }
        }
        return null;
    }
}
