import entidades.*;
import entidades.excepciones.StockInsuficiente;
import entidades.excepciones.VidaUtilInsuficiente;
import entidades.recetas.*;
import entidades.utensilios.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws StockInsuficiente, VidaUtilInsuficiente {

        Ingrediente ingrediente1 = new Ingrediente("Harina", 500);
        Ingrediente ingrediente2 = new Ingrediente("Azucar", 200);
        Ingrediente ingrediente3 = new Ingrediente("Sal", 100);
        Ingrediente ingrediente4 = new Ingrediente("Pimienta", 50);
        Ingrediente ingrediente5 = new Ingrediente("Manteca", 100);
        Ingrediente ingrediente6 = new Ingrediente("Fideos", 1000);
        Ingrediente ingrediente7 = new Ingrediente("Crema", 300);
        Ingrediente ingrediente8 = new Ingrediente("Huevo", 6);
        Ingrediente ingrediente9 = new Ingrediente("Carne", 500);
        Ingrediente ingrediente10 = new Ingrediente("Pan rallado", 250);
        Ingrediente ingrediente11 = new Ingrediente("Aceite", 100);
        Ingrediente ingrediente12 = new Ingrediente("Queso", 100);
        Ingrediente ingrediente13 = new Ingrediente("Cebolla", 100);
        Ingrediente ingrediente14 = new Ingrediente("Papa", 500);
        Ingrediente ingrediente15 = new Ingrediente("Leche", 100);

        Olla olla = new Olla();
        Sarten sarten = new Sarten();
        Cuchillo cuchillo = new Cuchillo();
        Tenedor tenedor = new Tenedor();
        Cuchara cuchara = new Cuchara();
        Freidora freidora = new Freidora();

        List<Receta> recetas = List.of(
                new FideosConCrema(), new Milanesa(), new PastelDePapa()
        );

        List<Despensa> despensas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Despensa despensa = new Despensa();
            despensa.addIngrediente(ingrediente1);
            despensa.addIngrediente(ingrediente2);
            despensa.addIngrediente(ingrediente3);
            despensa.addIngrediente(ingrediente4);
            despensa.addIngrediente(ingrediente5);
            despensa.addIngrediente(ingrediente6);
            despensa.addIngrediente(ingrediente7);
            despensa.addIngrediente(ingrediente8);
            despensa.addIngrediente(ingrediente9);
            despensa.addIngrediente(ingrediente10);
            despensa.addIngrediente(ingrediente11);
            despensa.addIngrediente(ingrediente12);
            despensa.addIngrediente(ingrediente13);
            despensa.addIngrediente(ingrediente14);
            despensa.addIngrediente(ingrediente15);
            despensa.addUtensilio(olla);
            despensa.addUtensilio(sarten);
            despensa.addUtensilio(cuchillo);
            despensa.addUtensilio(tenedor);
            despensa.addUtensilio(cuchara);
            despensa.addUtensilio(freidora);
            despensas.add(despensa);
        }

        System.out.println(despensas.size());

        List<Chef> chefsA = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Chef chef = new Chef("Chef " + (i + 1), i % recetas.size(), despensas.get(i), recetas.get(i % recetas.size()));
            chefsA.add(chef);
        }

        // Domingo a Jueves
        List<Chef> chefsB = new ArrayList<>();
        for (int i = 5; i < 8; i++) {
            Chef chef = new Chef("Chef " + (i + 1), i % recetas.size(), despensas.get(i), recetas.get(i % recetas.size()));
            chefsB.add(chef);
        }

        List<String> dias = new ArrayList<>();
        dias.add("Domingo");
        dias.add("Lunes");
        dias.add("Martes");
        dias.add("Miércoles");
        dias.add("Jueves");
        dias.add("Viernes");
        dias.add("Sábado");
        dias.add("Feriado");

        Random random = new Random();
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
