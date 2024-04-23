package entidades.excepciones;

public class VidaUtilInsuficiente extends Exception {
    public VidaUtilInsuficiente(String utensilio, int vidaUtilFaltante) {
        super("La vida útil del utensilio " + utensilio + " es insuficiente. Se necesitan " + vidaUtilFaltante + " unidades más.");
    }
}
