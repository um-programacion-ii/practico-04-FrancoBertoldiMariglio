package entidades;

import entidades.excepciones.StockInsuficiente;
import entidades.recetas.Receta;

import java.util.Map;

public class DespensaService {

    public void checkIngredientes(Despensa despensa, Receta receta) throws StockInsuficiente {
        Map<String, Ingrediente> ingredientesDespensa = despensa.getIngredientes();
        Map<String, Ingrediente> ingredientesReceta = receta.getIngredientes();

        if (!ingredientesDespensa.isEmpty() && !ingredientesReceta.isEmpty()) {
            for (Map.Entry<String, Ingrediente> ingredienteReceta : ingredientesReceta.entrySet()) {
                Ingrediente ingredienteDespensa = ingredientesDespensa.get(ingredienteReceta.getKey());
                if (ingredienteDespensa != null) {
                    if (ingredienteDespensa.getCantidad() < ingredienteReceta.getValue().getCantidad()) {
                        int cantidadFaltante = ingredienteReceta.getValue().getCantidad() - ingredienteDespensa.getCantidad();
                        throw new StockInsuficiente(ingredienteReceta.getKey(), receta.getClass().getSimpleName(), cantidadFaltante);
                    }
                } else {
                    throw new StockInsuficiente(ingredienteReceta.getKey(), receta.getClass().getSimpleName(), ingredienteReceta.getValue().getCantidad());
                }
            }
        }
        else {
            throw new StockInsuficiente("Desconocido", receta.getClass().getSimpleName(), 0);
        }
    }
}
