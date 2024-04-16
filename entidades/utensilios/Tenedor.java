package entidades.utensilios;

import entidades.utensilios.Utensilio;

public class Tenedor extends Utensilio {

    public Tenedor() {
        super(4);
    }

    public Tenedor(int vidaUtil) {
        super(vidaUtil);
    }

    @Override
    public void lavar() {
        this.setVidaUtil(4);
    }
}
