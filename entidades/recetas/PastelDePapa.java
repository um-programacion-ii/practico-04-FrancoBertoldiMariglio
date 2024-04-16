package entidades.recetas;
import entidades.Ingrediente;
import entidades.utensilios.Olla;
import entidades.utensilios.Sarten;
import entidades.utensilios.Cuchara;
import java.util.HashMap;

public class PastelDePapa extends Receta {

    public PastelDePapa() {
        super(
        60,
        new HashMap<>() {{
            put("Papa", new Ingrediente("Papa", 500));
            put("Huevo", new Ingrediente("Huevo", 2));
            put("Queso", new Ingrediente("Queso", 100));
            put("Sal", new Ingrediente("Sal", 10));
            put("Pimienta", new Ingrediente("Pimienta", 10));
            put("Leche", new Ingrediente("Leche", 100));
            put("Manteca", new Ingrediente("Manteca", 50));
            put("Aceite", new Ingrediente("Aceite", 25));
            put("Cebolla", new Ingrediente("Cebolla", 50));
            put("Carne", new Ingrediente("Carne", 500));
        }},
        new HashMap<>() {{
        put("Olla", new Olla(1));
        put("Sarten", new Sarten(1));
        put("Cuchara", new Cuchara(1));
        }},
        "Hervir las papas, hacer un pure con leche y manteca, dorar la cebolla y la carne, armar el pastel intercalando capas de pure, carne y queso, hornear por 30 minutos");
    }
}