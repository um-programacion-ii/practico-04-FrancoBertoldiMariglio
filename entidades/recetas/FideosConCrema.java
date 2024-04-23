package entidades.recetas;
import entidades.utensilios.Cuchara;
import entidades.utensilios.Cuchillo;
import entidades.utensilios.Olla;
import entidades.Ingrediente;

import java.util.HashMap;

public class FideosConCrema extends Receta {

    public FideosConCrema() {
        super(
        20,
        new HashMap<>() {{
        put("Fideos", new Ingrediente("Fideos", 200));
        put("Crema", new Ingrediente("Crema", 100));
        }},
        new HashMap<>() {{
        put("Cuchillo", new Cuchillo(1));
        put("Cuchara", new Cuchara(1));
        put("Olla", new Olla(1));
        }},
        "Hervir los fideos, mezclar con crema y servir.");
    }
}
