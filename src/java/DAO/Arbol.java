/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.Cliente;
import VO.Proveedor;
import VO.Trabajador;
import VO.Producto;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author JHOHAN
 */
public class Arbol {

    private RandomAccessFile raf;
    private RandomAccessFile ref;

    public Arbol() {
 
    }

    public void agregar(String nombre, long valor,Object t) throws IOException {
            raf = new RandomAccessFile("Arbol" + nombre, "rw");
            ref = new RandomAccessFile(nombre, "rw");
            System.out.println(ref.length()+"tam");
        raf.seek(0);
        agregar2(nombre,valor,t);
    }
    public void addProducto(int id, String Nombre, int Cantidad, double Precio, String estado)throws IOException{
        ref.seek(ref.length());
       ref.writeUTF(Nombre);
        ref.writeInt(id);
        ref.writeDouble(Precio); 
        ref.writeUTF(estado);
        ref.writeInt(Cantidad);
    }
    public void addTraba(String Nombre,String Apellido, int id, String cargo, double Sueldo,int Diastraba, double deuda,double pagado)throws IOException {
        ref.seek(ref.length());
         ref.writeInt(id);
         ref.writeUTF(Nombre);
         ref.writeUTF(Apellido);
         ref.writeUTF(cargo);
         ref.writeDouble(Sueldo);  
         ref.writeInt(Diastraba);
         ref.writeDouble(deuda);
         ref.writeDouble(pagado);
         System.out.println("agregado");
    }

    public void addCli(String Nombre, String apellido,int id,String historia)throws IOException {
         ref.seek(ref.length());
         ref.writeUTF(Nombre);
         ref.writeUTF(apellido);
         ref.writeInt(id);
         ref.writeUTF(historia);
         
    }
    public void addProve(String nombre, int id, int telefono, String direccion)throws IOException{
         ref.seek(ref.length());
         ref.writeUTF(nombre);
         ref.writeInt(id);
         ref.writeInt(telefono);
         ref.writeUTF(direccion);
          
    }

    public void agregar2(String nombre,long valor, Object t) throws IOException {
        if(raf.length()>10){
            try{
        if (valor > raf.readLong()) {
            raf.skipBytes(4);
            long pos = raf.getFilePointer();
            if (raf.readInt() == -1) {
                raf.seek(pos);
                raf.writeInt((int) raf.length());
                raf.seek(raf.length());
                raf.writeLong(valor);
                raf.writeInt(-1);
                raf.writeInt(-1);
                raf.writeLong(ref.length());
switch(nombre){
                case "Producto":
                 Producto p = (Producto)t;
                 addProducto(p.getId(),p.getNombre(),p.getCantidad(),p.getPrecio(),p.getEstado());
                 
                 break;
                case "Cliente":
                    Cliente c=(Cliente)t;
                    addCli(c.getNombre(),c.getApellido(),c.getId(),c.getHistoriaClinica());
                    break;
                case "Trabajador":
                    Trabajador a=(Trabajador)t;
                    addTraba(a.getNombre(),a.getApellido(),a.getId(),a.getCargo(),a.getSueldo(),a.getDiasTraba(),a.getDeuda(),a.getPagado());
                    break;
                 case"Provedor":
                     Proveedor pro=(Proveedor)t;
                     addProve(pro.getNombre(),pro.getId(),pro.getTelefono(),pro.getDireccion());
                     break;
                     
            }
            } else {

                raf.seek(pos);
                int ns = raf.readInt();
                raf.seek(ns);
                agregar2(nombre,valor, t);
            }
        } else {
            int pos = (int) raf.getFilePointer();
            if (raf.readInt() == -1) {
                raf.seek(pos);
                raf.writeInt((int) raf.length());
                raf.seek(raf.length());
                raf.writeLong(valor);
                raf.writeInt(-1);
                raf.writeInt(-1);
                raf.writeLong(ref.length());
switch(nombre){
                case "Producto":
                 Producto p = (Producto)t;
                 addProducto(p.getId(),p.getNombre(),p.getCantidad(),p.getPrecio(),p.getEstado());
                 
                 break;
                case "Cliente":
                    Cliente c=(Cliente)t;
                    addCli(c.getNombre(),c.getApellido(),c.getId(),c.getHistoriaClinica());
                    break;
                case "Trabajador":
                    Trabajador a=(Trabajador)t;
                    addTraba(a.getNombre(),a.getApellido(),a.getId(),a.getCargo(),a.getSueldo(),a.getDiasTraba(),a.getDeuda(),a.getPagado());
                    break;
                 case"Provedor":
                     Proveedor pro=(Proveedor)t;
                     addProve(pro.getNombre(),pro.getId(),pro.getTelefono(),pro.getDireccion());
                     break;          
            }
            } else {
                raf.seek(pos);
                int ns = raf.readInt();
                raf.seek(ns);
                agregar2(nombre,valor,t);
            }
        }
            }catch(Exception e){
                System.out.println(e.getMessage()+" "+e.getCause());
            }
    }else{
            
            raf.writeLong(valor);   
            raf.writeInt(-1);
            raf.writeInt(-1);
            raf.writeLong(ref.length());
            switch(nombre){
               case "Producto":
                 Producto p = (Producto)t;
                 addProducto(p.getId(),p.getNombre(),p.getCantidad(),p.getPrecio(),p.getEstado());
                 break;
                case "Cliente":
                    Cliente c=(Cliente)t;
                    addCli(c.getNombre(),c.getApellido(),c.getId(),c.getHistoriaClinica());
                    break;
                case "Trabajador":
                    Trabajador a=(Trabajador)t;
                    addTraba(a.getNombre(),a.getApellido(),a.getId(),a.getCargo(),a.getSueldo(),a.getDiasTraba(),a.getDeuda(),a.getPagado());
                    break;
                 case"Provedor":
                     Proveedor pro=(Proveedor)t;
                     addProve(pro.getNombre(),pro.getId(),pro.getTelefono(),pro.getDireccion());
                     break;
//                     //                     
            }
        }
    }
    public void imprimirArbol() {
        try {
             raf = new RandomAccessFile("ArbolTrabajador", "rw");
            raf.seek(0);
            while (raf.getFilePointer() <= raf.length()) {
                System.out.print(raf.readLong() + " ");
                System.out.print(raf.readInt() + " ");
                System.out.print(raf.readInt() + " ");
                System.out.print(raf.readLong() + " ");
                System.out.println("");
            }
        } catch (Exception e) {

        }
    }
    public void borrarProducto(int id) throws IOException{
         ref = new RandomAccessFile("Producto", "rw");
        ArrayList<Producto> productos=new ArrayList();
        long pos=buscarpos("Producto", id);
          if(pos!=-1){
        ref.seek(pos);
        ref.skipBytes(30);
        while(ref.getFilePointer()<ref.length()){
            productos.add((Producto)readProd());
        }
        ref.seek(pos);
        for (Producto p : productos) {
             addProducto(p.getId(),p.getNombre(),p.getCantidad(),p.getPrecio(),p.getEstado());
        }
        ref.setLength(ref.length()-30);
         }
    }
    public void borrarCliente(int id) throws IOException{
        ref = new RandomAccessFile("Cliente", "rw");
        ArrayList<Cliente> clientes=new ArrayList();
        long pos=buscarpos("Cliente", id);
          if(pos!=-1){
        ref.seek(pos);
        ref.skipBytes(24);
        while(ref.getFilePointer()<ref.length()){
            clientes.add((Cliente)readClie());
        }
        ref.seek(pos);
        for (Cliente c : clientes) {
             addCli(c.getNombre(),c.getApellido(),c.getId(),c.getHistoriaClinica());
        }       
        ref.setLength(ref.length()-24);
          }
    }
     public void borrarProvedor(int id) throws IOException{
         ref = new RandomAccessFile("Provedor", "rw");
         ArrayList<Proveedor> provedores=new ArrayList();
        long pos=buscarpos("Provedor", id);
          if(pos!=-1){
        ref.seek(pos);
        ref.skipBytes(21);
        while(ref.getFilePointer()<ref.length()){
            provedores.add((Proveedor)readProv());
        }
        ref.seek(pos);
         for (Proveedor pro : provedores) {
            addProve(pro.getNombre(),pro.getId(),pro.getTelefono(),pro.getDireccion());  
         }
        ref.setLength(ref.length()-21);
          }
    }
    public void borrarTrabajador(int id) throws IOException{
        ref = new RandomAccessFile("Trabajador", "rw");
         ArrayList<Trabajador> trabajadores=new ArrayList();
        long pos=buscarpos("Trabajador", id);
        if(pos!=-1){
        ref.seek(pos);
        ref.skipBytes(57);
        while(ref.getFilePointer()<ref.length()){
            trabajadores.add((Trabajador)readTraba());
        }
        ref.seek(pos);
        for (Trabajador a : trabajadores) {
            addTraba(a.getNombre(),a.getApellido(),a.getId(),a.getCargo(),a.getSueldo(),a.getDiasTraba(),a.getDeuda(),a.getPagado());
        }
        ref.setLength(ref.length()-57);
        }
    }
    public Object buscar(String nombre,int valor) throws IOException {
        raf = new RandomAccessFile("Arbol" + nombre, "rw");
        ref = new RandomAccessFile(nombre, "rw");
        raf.seek(0);
        ref.seek(0);
        return busqueda(nombre,valor);
    }
        public long buscarpos(String nombre,int valor) throws IOException {
        raf = new RandomAccessFile("Arbol" + nombre, "rw");
        ref = new RandomAccessFile(nombre, "rw");
        raf.seek(0);
        ref.seek(0);
        return busquedapos(nombre,valor);
    }
public Object readProd()throws IOException{
      Producto p= new Producto(ref.readUTF(),ref.readInt(),ref.readDouble(),ref.readUTF(),ref.readInt());
       return p;
}
public Object readTraba()throws IOException{
      Trabajador p= new Trabajador(ref.readInt(),ref.readUTF(),ref.readUTF(),ref.readUTF(),ref.readDouble(),ref.readInt(),ref.readDouble(),ref.readDouble());
       return p;
}
public Object readProv()throws IOException{
      Proveedor p=new Proveedor(ref.readUTF(),ref.readInt(),ref.readInt(),ref.readUTF());
       return p;
}
public Object readClie()throws IOException{
      Cliente p= new Cliente(ref.readUTF(),ref.readUTF(),ref.readInt(),ref.readUTF());
       return p;
}
public void Modificar(Object o, int val) throws IOException{
   if(o instanceof Trabajador){
       long pos=busquedapos("Trabajador",val);
       Trabajador a=(Trabajador)o;
       ref = new RandomAccessFile("Trabajador", "rw");
       ref.seek(pos);
       addTraba(a.getNombre(),a.getApellido(),a.getId(),a.getCargo(),a.getSueldo(),a.getDiasTraba(),a.getDeuda(),a.getPagado());
       
   }else if(o instanceof Cliente ){
       long pos=busquedapos("Cliente",val);
       ref = new RandomAccessFile("Cliente", "rw");
       ref.seek(pos);
       Cliente c=(Cliente)o;
       addCli(c.getNombre(),c.getApellido(),c.getId(),c.getHistoriaClinica());
   }else if(o instanceof Proveedor){
       long pos=busquedapos("Provedor",val);
       ref = new RandomAccessFile("Provedor", "rw");
       ref.seek(pos);
        Proveedor pro=(Proveedor)o;
        addProve(pro.getNombre(),pro.getId(),pro.getTelefono(),pro.getDireccion());
   }else if(o instanceof Producto){
       long pos=busquedapos("Producto",val);
       ref = new RandomAccessFile("Producto", "rw");
       ref.seek(pos);
       Producto p = (Producto)o;
       addProducto(p.getId(),p.getNombre(),p.getCantidad(),p.getPrecio(),p.getEstado());
   }
}
        
    public Object busqueda(String nombre, int valor) throws IOException {
        long clave = raf.readLong();
        if (clave == valor) {
            raf.skipBytes(8);
            ref.seek(raf.readLong());
            switch (nombre) {
                case "Producto":
                    return readProd();
                case "Cliente":
                    return readClie();
                case "Trabajador":
                    return readTraba();
                case "Provedor":
                    return readProv();
            }
        } else if (clave > valor) {
            long p = raf.readInt();
            raf.seek(p);
            return busqueda(nombre, valor);
        } else {
            raf.skipBytes(4);
            raf.seek(raf.readInt());
            return busqueda(nombre, valor);
        }
        return null;
    }
   public long busquedapos(String nombre, int valor) throws IOException {
        long clave = raf.readLong();
        if (clave == valor) {
            raf.skipBytes(8);
             ref.seek(raf.readLong());
            return ref.getFilePointer();
        } else if (clave > valor) {
            long p = raf.readInt();
            raf.seek(p);
            return busquedapos(nombre, valor);
        } else {
            raf.skipBytes(4);
            raf.seek(raf.readInt());
            return busquedapos(nombre, valor);
        }  
    }
   public ArrayList<Cliente> listarCli() throws IOException{
       ref = new RandomAccessFile("Cliente", "rw");
       ref.seek(0);
       ArrayList<Cliente> clientes= new ArrayList();
       for (int i = 0; i < ref.length(); i+=24) {
           clientes.add((Cliente)readClie());
       }
       return clientes;
   }
      public ArrayList<Proveedor> listarProv() throws IOException{
       ref = new RandomAccessFile("Provedor", "rw");
       ref.seek(0);
       ArrayList<Proveedor> provedores= new ArrayList();
       for (int i = 0; i < ref.length(); i+=24) {
           provedores.add((Proveedor)readProv());
       }
       return provedores;
   }
    public ArrayList<Trabajador> listarTrab() throws IOException{
       ref = new RandomAccessFile("Trabajador", "rw");
       ref.seek(0);
       ArrayList<Trabajador> trabajadores= new ArrayList();
       for (int i = 0; i < ref.length(); i+=24) {
           trabajadores.add((Trabajador)readTraba());
       }
       return trabajadores;
   }
    public ArrayList<Producto> listarProd() throws IOException{
       ref = new RandomAccessFile("Producto", "rw");
       ref.seek(0);
       ArrayList<Producto> productos= new ArrayList();
       for (int i = 0; i < ref.length(); i+=24) {
           productos.add((Producto)readProd());
       }
       return productos;
   }
}
