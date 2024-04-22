package entidades;

import entidades.excepciones.UtensilioNoDisponible;
import entidades.excepciones.VidaUtilInsuficiente;
import entidades.recetas.Receta;
import entidades.utensilios.Utensilio;

import java.util.HashMap;
import java.util.Map;

public class EstanteService {

    public void checkUtensilios(Estante estante, Receta receta) throws VidaUtilInsuficiente, UtensilioNoDisponible {
        Map<String, Utensilio> utensiliosReceta = receta.getUtensilios();

        for (Map.Entry<String, Utensilio> utensilioReceta : utensiliosReceta.entrySet()) {
            Utensilio utensilioDespensa = estante.getUtensilio(utensilioReceta.getKey());
            if (utensilioDespensa == null) {
                throw new UtensilioNoDisponible(utensilioReceta.getKey());
            } else if (utensilioDespensa.getVidaUtil() < utensilioReceta.getValue().getVidaUtil()) {
                int vidaUtilFaltante = utensilioReceta.getValue().getVidaUtil() - utensilioDespensa.getVidaUtil();
                throw new VidaUtilInsuficiente(utensilioReceta.getKey(), vidaUtilFaltante);
            }
        }
    }

    public Map<String, Utensilio> sacarUtenilios(Estante estante, Receta receta) {
        Map<String, Utensilio> utensiliosReceta = receta.getUtensilios();
        Map<String, Utensilio> utensiliosUsados = new HashMap<>();
        utensiliosReceta.entrySet().stream().forEach(utensilioReceta -> {
            Utensilio utensilioEstante = estante.getUtensilio(utensilioReceta.getKey());
            utensilioEstante.setVidaUtil(utensilioEstante.getVidaUtil() - utensilioReceta.getValue().getVidaUtil());
            utensiliosUsados.put(utensilioReceta.getKey(), utensilioEstante);
        });
        return utensiliosUsados;
    }
}
