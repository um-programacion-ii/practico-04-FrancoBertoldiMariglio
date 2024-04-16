package entidades.utensilios;

public class Olla extends Utensilio {

    public Olla() {
        super(1);
    }

    public Olla(int vidaUtil) {
        super(vidaUtil);
    }

    @Override
    public void lavar() {
        this.setVidaUtil(1);
    }
}
