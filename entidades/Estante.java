package entidades;

import java.util.concurrent.ConcurrentHashMap;
import entidades.utensilios.Utensilio;

public class Estante {
    private ConcurrentHashMap<String, Utensilio> utensiliosDisponibles;

    private static class EstanteHolder {
        private static final Estante INSTANCE = new Estante();
    }

    private Estante() {
        utensiliosDisponibles = new ConcurrentHashMap<>();
    }

    public static Estante getInstance() {
        return EstanteHolder.INSTANCE;
    }

    public synchronized void agregarUtensilio(String nombre, Utensilio utensilio) {
        utensiliosDisponibles.put(nombre, utensilio);
    }

    public synchronized Utensilio getUtensilio(String nombre) {
        return utensiliosDisponibles.get(nombre);
    }
}