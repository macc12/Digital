package VO;
public class Cliente {
    private String nombre;
    private String apellido;
    private int id;
    private String HistoriaClinica;
    public Cliente(String nombre, String apellido, int id, String HistoriaClinica) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.HistoriaClinica = HistoriaClinica;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getHistoriaClinica() {
        return HistoriaClinica;
    }
    public void setHistoriaClinica(String HistoriaClinica) {
        this.HistoriaClinica = HistoriaClinica;
    }
}
