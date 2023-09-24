import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo
{

    private List<Carta> cartas;

    public Mazo()
    {
        cartas = new ArrayList<>();
    }

    public void crearMazo()
    {
        for (Palo palo : Palo.values()) {
            for (Valor valor : Valor.values()) {
                cartas.add(new Carta(palo, valor));
            }
        }
    }

    public void mezclarMazo()
    {
        Collections.shuffle(cartas);
    }

    public int obtenerCantidadActual()
    {
        return cartas.size();
    }

    public Carta sacarCarta() {
        if (!cartas.isEmpty()) {
            return cartas.remove(cartas.size() - 1);
        } else {
            return null;
        }
    }
}
