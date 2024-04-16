package entidades;

import entidades.recetas.Receta;
import entidades.excepciones.StockInsuficiente;
import entidades.excepciones.VidaUtilInsuficiente;
import entidades.utensilios.Utensilio;

import java.util.Map;

public class CocinaService {

    private static CocinaService instance = null;
    private final DespensaService despensaService = new DespensaService();

    private CocinaService() {
    }

    public static CocinaService getInstance() {
        if (instance == null) {
            instance = new CocinaService();
        }
        return instance;
    }

    public void cocinar(Despensa despensa, Receta receta) throws StockInsuficiente, VidaUtilInsuficiente {
        despensaService.checkIngredientes(despensa, receta);
        despensaService.checkUtensilios(despensa, receta);

        Map<String, Ingrediente> ingredientesDespensa = despensa.getIngredientes();
        Map<String, Ingrediente> ingredientesReceta = receta.getIngredientes();
        Map<String, Utensilio> utensiliosDespensa = despensa.getUtensilios();
        Map<String, Utensilio> utensiliosReceta = receta.getUtensilios();

        System.out.println("Preparación de: " + receta.getClass().getSimpleName() +"\n" + receta.getPreparacion());
        System.out.println("\nIngredientes restantes despues de la preparación:");

        for (Map.Entry<String, Ingrediente> ingredienteReceta : ingredientesReceta.entrySet()) {
            Ingrediente ingredienteDespensa = ingredientesDespensa.get(ingredienteReceta.getKey());
            if (ingredienteDespensa != null) {
                int nuevaCantidad = ingredienteDespensa.getCantidad() - ingredienteReceta.getValue().getCantidad();
                ingredientesDespensa.put(ingredienteReceta.getKey(), new Ingrediente(ingredienteReceta.getKey(), nuevaCantidad));
                System.out.println(ingredienteReceta.getKey() + ": " + nuevaCantidad + " unidades");
            }
        }

        System.out.println("\nVida útil de los utensilios después de la preparación:");

        for (Map.Entry<String, Utensilio> utensilioReceta : utensiliosReceta.entrySet()) {
            Utensilio utensilioDespensa = utensiliosDespensa.get(utensilioReceta.getKey());
            if (utensilioDespensa != null) {
                int nuevaVidaUtil = utensilioDespensa.getVidaUtil() - 1; // assuming each recipe reduces the vidaUtil by 1
                utensilioDespensa.setVidaUtil(nuevaVidaUtil);
                System.out.println(utensilioReceta.getKey() + ": " + nuevaVidaUtil + " vida útil");
            }
        }

        despensaService.renovarUtensilios(despensa);

        System.out.println(receta.getClass().getSimpleName() + " cocinada con éxito!\n");
    }
}