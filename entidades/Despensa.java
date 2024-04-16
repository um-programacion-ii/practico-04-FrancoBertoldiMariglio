package entidades;
import entidades.utensilios.Utensilio;

import java.util.HashMap;
import java.util.Map;

public class Despensa {
//    private Ingrediente[] ingredientes = new Ingrediente[20];
    private Map<String, Ingrediente> ingredientes = new HashMap<>();

    private Map<String, Utensilio> utensilios = new HashMap<>();

    public void addIngrediente(Ingrediente ingrediente) {
        ingredientes.put(ingrediente.getNombre(), ingrediente);
    }

    public void addUtensilio(Utensilio utensilio) {
        utensilios.put(utensilio.getClass().getSimpleName(), utensilio);
    }

    public void getIngrediente(String ingrediente, int cantidad) {
        if (ingredientes.containsKey(ingrediente)) {
            int cantidadActual = ingredientes.get(ingrediente).getCantidad();
            if (cantidadActual >= cantidad) {
                ingredientes.put(ingrediente, new Ingrediente(ingrediente, cantidadActual - cantidad));
            } else {
                System.out.println("No hay suficiente " + ingrediente + " en la despensa.");
            }
        } else {
            System.out.println("No se encontro el ingrediente " + ingrediente);
        }
    }

    public Map<String, Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public Map<String, Utensilio> getUtensilios() {
        return utensilios;
    }
}
