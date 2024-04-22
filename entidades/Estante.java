package entidades;

import java.util.concurrent.ConcurrentHashMap;
import entidades.utensilios.*;

public class Estante {
    private ConcurrentHashMap<String, Utensilio> utensiliosDisponibles;

    private static class EstanteHolder {
        private static final Estante INSTANCE = new Estante();
    }

    private Estante() {
        utensiliosDisponibles = new ConcurrentHashMap<>();
        inicializarUtensilios();
    }

    public static Estante getInstance() {
        return EstanteHolder.INSTANCE;
    }

    private void inicializarUtensilios() {
        agregarUtensilio("Olla", new Olla());
        agregarUtensilio("Sarten", new Sarten());
        agregarUtensilio("Cuchillo", new Cuchillo());
        agregarUtensilio("Tenedor", new Tenedor());
        agregarUtensilio("Cuchara", new Cuchara());
        agregarUtensilio("Freidora", new Freidora());
    }

    public synchronized void agregarUtensilio(String nombre, Utensilio utensilio) {
        utensiliosDisponibles.put(nombre, utensilio);
    }

    public synchronized Utensilio getUtensilio(String nombre) {
        return utensiliosDisponibles.get(nombre);
    }
}