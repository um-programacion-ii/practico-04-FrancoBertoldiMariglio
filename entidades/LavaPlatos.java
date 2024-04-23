package entidades;

import entidades.utensilios.Utensilio;

import java.util.Map;
import java.util.concurrent.Callable;

public class LavaPlatos implements Callable {

    private Map<String, Utensilio> utensiliosUsados;

    public LavaPlatos() {
    }

    public LavaPlatos(Map<String, Utensilio> utensiliosUsados) {
        this.utensiliosUsados = utensiliosUsados;
    }

    public Void call() throws InterruptedException {
        for (Utensilio utensilio : utensiliosUsados.values()) {
            if (utensilio.getVidaUtil() == 0) {
                Thread.sleep(300);
                utensilio.lavar();
            }
        }
        return null;
    }
}
