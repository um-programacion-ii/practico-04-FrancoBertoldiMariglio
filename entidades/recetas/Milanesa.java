package entidades.recetas;
import entidades.Ingrediente;
import entidades.recetas.Receta;
import entidades.utensilios.Cuchillo;
import entidades.utensilios.Tenedor;
import entidades.utensilios.Freidora;
import java.util.HashMap;

public class Milanesa extends Receta {

    public Milanesa () {
        super(
            30,
            new HashMap<>() {{
                put("Carne", new Ingrediente("Carne", 200));
                put("Huevo", new Ingrediente("Huevo", 1));
                put("Pan rallado", new Ingrediente("Pan rallado", 200));
                put("Sal", new Ingrediente("Sal", 10));
                put("Pimienta", new Ingrediente("Pimienta", 10));
                put("Aceite", new Ingrediente("Aceite", 100));
            }},
            new HashMap<>() {{
                put("Cuchillo", new Cuchillo(1));
                put("Tenedor", new Tenedor(1));
                put("Freidora", new Freidora(1));
            }},
            "Pasar la carne por huevo y pan rallado, freir en aceite caliente por 5 minutos");
    }
}