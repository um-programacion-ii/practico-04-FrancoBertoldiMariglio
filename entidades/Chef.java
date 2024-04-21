package entidades;

import entidades.recetas.Receta;
import entidades.excepciones.StockInsuficiente;
import entidades.excepciones.VidaUtilInsuficiente;
import java.util.concurrent.Callable;

public class Chef implements Callable {

    private String nombre;
    private int estrellasMichelin;
    private Despensa despensa;
    private Receta receta;
    public CocinaService cocinaService = CocinaService.getInstance();

    public Chef() {
    }

    public Chef(String nombre, int estrellasMichelin, Despensa despensa, Receta receta) {
        this.nombre = nombre;
        this.estrellasMichelin = estrellasMichelin;
        this.despensa = despensa;
        this.receta = receta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstrellasMichelin() {
        return estrellasMichelin;
    }

    public void setEstrellasMichelin(int estrellasMichelin) {
        this.estrellasMichelin = estrellasMichelin;
    }

    public Despensa getDespensa() {
        return despensa;
    }

    public void setDespensa(Despensa despensa) {
        this.despensa = despensa;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public CocinaService getCocinaService() {
        return cocinaService;
    }

    @Override
    public String toString() {
        return "Chef: {" + "nombre: " + nombre + ", estrellasMichelin: " + estrellasMichelin + '}';
    }

    @Override
    public Void call() {

        while (True) {

            try {
                cocinaService.cocinar(despensa, receta);
            } catch (StockInsuficiente | VidaUtilInsuficiente e) {
                e.printStackTrace();
            }

            return null;
        }
}