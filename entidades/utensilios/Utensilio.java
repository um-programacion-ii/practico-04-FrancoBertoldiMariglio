package entidades.utensilios;
import interfaces.Despensable;
import interfaces.Reutilizable;

public class Utensilio implements Despensable, Reutilizable {
    private int vidaUtil;

    public Utensilio() {}

    public Utensilio(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public int getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public void lavar() { }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
