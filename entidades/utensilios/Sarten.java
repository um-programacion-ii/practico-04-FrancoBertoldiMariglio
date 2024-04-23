package entidades.utensilios;

public class Sarten extends Utensilio {
    public Sarten() {
            super(1000);
}

    public Sarten(int vidaUtil) {
        super(vidaUtil);
    }

    @Override
    public void lavar() {
        this.setVidaUtil(2);
    }
}
