package entidades.excepciones;

public class StockInsuficiente extends Exception {
    public StockInsuficiente(String ingrediente, String receta, int cantidadFaltante) {
        super("No hay suficiente " + ingrediente +
              " en la despensa para poder cocinar " + receta +
              ". Se necesitan " + cantidadFaltante + " unidades más.");
    }
}
