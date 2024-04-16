package entidades.recetas;
import entidades.Ingrediente;
import entidades.utensilios.Utensilio;

import java.util.Map;
import java.util.stream.Collectors;

public class Receta {
    protected int tiempoCoccion;
//    protected Ingrediente[] ingredientes;

    protected Map<String, Ingrediente> ingredientes;

    protected Map<String, Utensilio> utensilios;

    protected String preparacion;

    public Receta(int tiempoCoccion, Map<String, Ingrediente> ingredientes, Map<String, Utensilio> utensilios, String preparacion) {
        this.tiempoCoccion = tiempoCoccion;
        this.ingredientes = ingredientes;
        this.utensilios = utensilios;
        this.preparacion = preparacion;
    }

    public int getTiempoCoccion() {
        return tiempoCoccion;
    }

    public Map<String, Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public Map<String, Utensilio> getUtensilios() {
        return utensilios;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setTiempoCoccion(int tiempoCoccion) {
        this.tiempoCoccion = tiempoCoccion;
    }

    public void setIngredientes(Map<String, Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setUtensilios(Map<String, Utensilio> utensilios) {
        this.utensilios = utensilios;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

  @Override
    public String toString() {
        String ingredientesStr = ingredientes.entrySet().stream()
            .map(entry -> entry.getKey() + "=" + entry.getValue().getCantidad())
            .collect(Collectors.joining(", ", "[", "]"));

        return String.format("Receta {tiempoCoccion=%d, ingredientes=%s, utensilios=%s, preparacion='%s'}",
                             tiempoCoccion, ingredientesStr, utensilios.keySet(), preparacion);
    }
}
