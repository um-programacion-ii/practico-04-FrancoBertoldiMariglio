package entidades;
import interfaces.Cocinable;
import interfaces.Despensable;

public class Ingrediente implements Cocinable, Despensable {
    private String nombre;
    private int cantidad;

    public Ingrediente() { }

    public Ingrediente(String nombre, int cantidad) {
            this.nombre = nombre;
            this.cantidad = cantidad;
        }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return nombre;
    }
    public boolean sacar(int cantidad) {
        if (this.cantidad < cantidad) {
            System.out.println("No hay suficiente cantidad de " + nombre + " me falta " +
                    (cantidad - this.cantidad) + "");
            return false;
        }
        this.cantidad -= cantidad;
        return true;
    }
}
