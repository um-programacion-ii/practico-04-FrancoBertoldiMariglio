package entidades.utensilios;

import entidades.utensilios.Utensilio;

public class Tenedor extends Utensilio {

    public Tenedor() {
        super(1000);
    }

    public Tenedor(int vidaUtil) {
        super(vidaUtil);
    }

    @Override
    public void lavar() {
        this.setVidaUtil(4);
    }
}
