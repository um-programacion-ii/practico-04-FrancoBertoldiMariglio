package entidades;

import entidades.excepciones.UtensilioNoDisponible;
import entidades.recetas.Receta;
import entidades.excepciones.StockInsuficiente;
import entidades.excepciones.VidaUtilInsuficiente;
import entidades.utensilios.Utensilio;

import java.util.Map;

public class CocinaService {

    private static CocinaService instance = null;
    private final DespensaService despensaService = new DespensaService();

    private final EstanteService estanteService = new EstanteService();

    private final Estante estante = Estante.getInstance();

    private CocinaService() {
    }

    public static CocinaService getInstance() {
        if (instance == null) {
            instance = new CocinaService();
        }
        return instance;
    }

    public boolean cocinar(Despensa despensa, Receta receta) throws InterruptedException {

        Map<String, Ingrediente> ingredientesDespensa = despensa.getIngredientes();
        Map<String, Ingrediente> ingredientesReceta = receta.getIngredientes();
        Map<String, Utensilio> utensiliosReceta = receta.getUtensilios();

        synchronized (this.estante) {
            try {
                estanteService.checkUtensilios(this.estante, receta);
            } catch (UtensilioNoDisponible | VidaUtilInsuficiente e) {
                e.printStackTrace();
                System.out.println("Estoy esperando que se desocupe el utensilio");
                return false;
            }

            Map<String, Utensilio> utensiliosEstante = estanteService.sacarUtenilios(this.estante, receta);

            System.out.println("\nVida útil de los utensilios después de la preparación:");

            utensiliosReceta.entrySet().forEach(utensilioReceta -> {
                Utensilio utensilioEstante = utensiliosEstante.get(utensilioReceta.getKey());
                int nuevaVidaUtil = utensilioEstante.getVidaUtil() - 1;
                utensilioEstante.setVidaUtil(nuevaVidaUtil);
                System.out.println(utensilioReceta.getKey() + ": " + nuevaVidaUtil + " vida útil");
            });
        }

        Thread.sleep(3000);

        try {
            despensaService.checkIngredientes(despensa, receta);
        } catch (StockInsuficiente e) {
            e.printStackTrace();
            return false;
        }

        System.out.println("Preparación de: " + receta.getClass().getSimpleName() +"\n" + receta.getPreparacion());
        System.out.println("\nIngredientes restantes despues de la preparación:");

        ingredientesReceta.entrySet().stream().forEach(ingredienteReceta -> {
            Ingrediente ingredienteDespensa = ingredientesDespensa.get(ingredienteReceta.getKey());
            if (ingredienteDespensa != null) {
                int nuevaCantidad = ingredienteDespensa.getCantidad() - ingredienteReceta.getValue().getCantidad();
                ingredientesDespensa.put(ingredienteReceta.getKey(), new Ingrediente(ingredienteReceta.getKey(), nuevaCantidad));
                System.out.println(ingredienteReceta.getKey() + ": " + nuevaCantidad + " unidades");
            }
        });

        Thread.sleep(3000);

        System.out.println(receta.getClass().getSimpleName() + " cocinada con éxito!\n");
        return true;
    }
}
