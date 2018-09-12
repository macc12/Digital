/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.RandomAccessFile;

/**
 *
 * @author JHOHAN
 */
public class Pruebabusquedaabaa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
                  Arbol arbol=new Arbol();
                  System.out.println(arbol.buscarpos("Trabajador", 2));
                  arbol.borrarTrabajador(2);
                  System.out.println("hola");
                  System.out.println(arbol.buscarpos("Trabajador", 2));
//                    Trabajador p=new Trabajador(1,"Juan","perez","secretaria",1500,30,0,0);
//                    System.out.println(p.getApellido()+p.getPagado());
//                  arbol.agregar("Trabajador", p.getId(), p);
//                  p=new Trabajador(2,"pedro","t","secretaria",1550,20,100,0);
//                   System.out.println(p.getApellido()+p.getPagado());
//                  arbol.agregar("Trabajador", p.getId(), p);
//                  System.out.println("/////////////");
//                 arbol.imprimirArbol();
//                 System.out.println("/////////////");
//                Cliente a=(Cliente)arbol.buscar("Trabajador",2);
//               System.out.println(a.getNombre()+a.getApellido()+a.getId());
//               System.out.println("//////////////////////");
//               arbol.borrar();
//               System.out.println("lo boroo");
//               System.out.println("/////////////");
//                  a=(Producto)arbol.buscar("Cliente",1);
//               System.out.println(a.getNombre()+a.getId()+a.getCantidad()+a.getPrecio());
//               System.out.println("//////////////////////");
//                    p=new Producto("pera", 2, 100, 1500,"bueno");
//                     arbol.agregar("Producto", p.getId(), p);
//                     System.out.println("/////////////");
//                  a=(Producto)arbol.buscar("Producto",2);
//               System.out.println(a.getNombre()+a.getId()+a.getCantidad()+a.getPrecio());
               
        }catch(Exception e){
            
        }
    }
    
}
