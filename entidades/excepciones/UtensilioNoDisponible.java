package entidades.excepciones;

public class UtensilioNoDisponible extends Exception {
    public UtensilioNoDisponible(String utensilio) {
        super("El utensilio " + utensilio + " no está disponible.");
    }
}