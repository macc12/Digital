package DAO;
import VO.Producto;
import java.io.IOException;
import java.util.ArrayList;
public class DAOProducto {
     private ArrayList<Producto> prodcutos; 
     private Arbol arbol;
    public DAOProducto() {
        prodcutos = new ArrayList<>();
         arbol=new Arbol();
    }
    public boolean CrearProducto(Producto prod) throws IOException{
        if(BuscarProducto(prod.getId())==null){
            prodcutos.add(prod);
            arbol.agregar("Producto", prod.getId(), prod);
            return true;
        }
        return false;
    } 
    public ArrayList<Producto>ListarProducto() throws IOException{
         prodcutos.addAll(arbol.listarProd());
        return prodcutos;
    } 
    public boolean BorrarProducto(Producto prod) throws IOException{
     //   arbol.borrarProducto(prod.getId());
        return this.prodcutos.remove(prod);
    } 
    public boolean ModificarProducto(int id, Producto prod) throws IOException{
        for (int i = 0; i < prodcutos.size(); i++) {
            Producto aux = prodcutos.get(i); 
            if(aux.getId()==id){
                this.prodcutos.get(i).setNombre(prod.getNombre());
                this.prodcutos.get(i).setId(prod.getId());
                this.prodcutos.get(i).setPrecio(prod.getPrecio());
                this.prodcutos.get(i).setEstado(prod.getEstado());  
                this.prodcutos.get(i).setEstado(prod.getEstado());  
                arbol.Modificar(prod, prod.getId());
            }                        
        }
        return false;
    }
    public Producto BuscarProducto(int id){
        for (int i = 0; i < prodcutos.size(); i++) {            
            if(prodcutos.get(i).getId()==id){
                return prodcutos.get(i);
            }
        }
        return null;
    }
}