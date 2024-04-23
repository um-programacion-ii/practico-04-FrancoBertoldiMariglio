import entidades.*;
import entidades.recetas.*;
import entidades.LavaPlatos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main {

    private static List<Chef> crearChefs(int cantidad, List<Despensa> despensas, List<Receta> todasLasRecetas, Random random) {
        List<Chef> chefs = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            int estrellasMichelin = random.nextInt(5) + 1;
            List<Receta> recetasParaChef = new ArrayList<>();
            for (int j = 0; j < Math.min(estrellasMichelin, 3); j++) {
                recetasParaChef.add(todasLasRecetas.get(j % todasLasRecetas.size()));
            }
            Chef chef = new Chef("Chef " + (i + 1), estrellasMichelin, despensas.get(i), (ArrayList<Receta>) recetasParaChef);
            chefs.add(chef);
        }
        return chefs;
    }

    private static List<Ingrediente> crearIngredientes() {
        return Arrays.asList(
            new Ingrediente("Harina", 1000),
            new Ingrediente("Azucar", 200),
            new Ingrediente("Sal", 300),
            new Ingrediente("Pimienta", 200),
            new Ingrediente("Manteca", 100),
            new Ingrediente("Fideos", 1000),
            new Ingrediente("Crema", 300),
            new Ingrediente("Huevo", 12),
            new Ingrediente("Carne", 1000),
            new Ingrediente("Pan rallado", 800),
            new Ingrediente("Aceite", 500),
            new Ingrediente("Queso", 400),
            new Ingrediente("Cebolla", 600),
            new Ingrediente("Papa", 900),
            new Ingrediente("Leche", 500)
        );
    }

    public static void main(String[] args) {

        List<Ingrediente> ingredientes = crearIngredientes();

        Estante estante = Estante.getInstance();

        List<Receta> todasLasRecetas = Arrays.asList(new FideosConCrema(), new Milanesa(), new PastelDePapa());

        List<Despensa> despensas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Despensa despensa = new Despensa();
            for (Ingrediente ingrediente : ingredientes) {
                despensa.addIngrediente(ingrediente);
            }
            despensas.add(despensa);
        }

        Random random = new Random();

        // Viernes, Sabado y Feriados
        List<Chef> chefsA = crearChefs(5, despensas, todasLasRecetas, random);

        // Domingo a Jueves
        List<Chef> chefsB = crearChefs(8, despensas, todasLasRecetas, random);

        List<String> dias = new ArrayList<>();
        dias.add("Domingo");
        dias.add("Lunes");
        dias.add("Martes");
        dias.add("Miércoles");
        dias.add("Jueves");
        dias.add("Viernes");
        dias.add("Sábado");
        dias.add("Feriado");

        int indiceDiaAleatorio = random.nextInt(dias.size());
        String diaAleatorio = dias.get(indiceDiaAleatorio);
        System.out.println("Hoy es " + diaAleatorio);

        if (diaAleatorio.equals("Lunes") || diaAleatorio.equals("Martes") || diaAleatorio.equals("Miércoles") || diaAleatorio.equals("Jueves")) {
            ExecutorService executorServiceA = Executors.newFixedThreadPool(3);
            for (Chef chef : chefsA) {
                FutureTask<Void> futureTask = new FutureTask<>(chef);
                executorServiceA.submit(futureTask);
            }
            executorServiceA.shutdown();
        } else {
            ExecutorService executorServiceB = Executors.newFixedThreadPool(5);
            for (Chef chef : chefsB) {
                FutureTask<Void> futureTask = new FutureTask<>(chef);
                executorServiceB.submit(futureTask);
            }
            executorServiceB.shutdown();
        }
    }
}
