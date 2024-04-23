package entidades.utensilios;

import entidades.utensilios.Utensilio;

public class Cuchillo extends Utensilio {

    public Cuchillo() {
        super(1000);
    }

    public Cuchillo(int vidaUtil) {
        super(vidaUtil);
    }

    @Override
    public void lavar() {
        this.setVidaUtil(10);
    }
}
