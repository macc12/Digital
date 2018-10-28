package VO;
public class Producto{
    private String nombre;
    private int id;
    private double precio;
    private String  estado;
    private int cantidad;

    public Producto(String nombre, int id, double precio, String estado, int cantidad) {
        this.nombre = nombre;
        this.id = id;
        this.precio = precio;
        this.estado = estado;
        this.cantidad = cantidad;
    }

    public Producto() {
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    
}
