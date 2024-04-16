package entidades.utensilios;

public class Freidora extends Utensilio {
    public Freidora() {
                super(2);
            }

    public Freidora(int vidaUtil) {
        super(vidaUtil);
    }

    @Override
    public void lavar() {
        this.setVidaUtil(2);
    }
}
