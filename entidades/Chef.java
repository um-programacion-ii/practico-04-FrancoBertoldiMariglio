package entidades;

import entidades.recetas.Receta;
import entidades.excepciones.StockInsuficiente;
import entidades.excepciones.VidaUtilInsuficiente;
import entidades.excepciones.UtensilioNoDisponible;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Chef implements Callable {

    private String nombre;
    private int estrellasMichelin;
    private Despensa despensa;
    private ArrayList<Receta> recetas;
    public CocinaService cocinaService = CocinaService.getInstance();

    public Chef() {
    }

    public Chef(String nombre, int estrellasMichelin, Despensa despensa, ArrayList<Receta> recetas) {
        this.nombre = nombre;
        this.estrellasMichelin = estrellasMichelin;
        this.despensa = despensa;
        this.recetas = recetas;
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

    public ArrayList<Receta> getReceta() {
        return recetas;
    }

    public void setReceta(Receta receta) {
        this.recetas.add(receta);
    }

    @Override
    public String toString() {
        return "Chef: {" + "nombre: " + nombre + ", estrellasMichelin: " + estrellasMichelin + '}';
    }

    @Override
    public Void call()  {
        int i = 0;
        while (!recetas.isEmpty()) {
            Receta recetaActual = recetas.get(i);
            try {
                boolean cocinada = cocinaService.cocinar(despensa, recetaActual);
                if (cocinada) {
                    recetas.remove(i);
                    continue;
                }
                i = (i + 1) % recetas.size();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
