import java.util.ArrayList;
import java.util.List;

public class Columna {
    private List<Carta> cartasOcultas;

    private List<Carta> cartasReveladas;

    public Columna()
    {
        cartasOcultas = new ArrayList<>();
        cartasReveladas = new ArrayList<>();
    }

    public boolean columnaVacia() {
        return cartasOcultas.isEmpty() && cartasReveladas.isEmpty();
    }

    public Carta sacarCarta() {
        if (!cartasReveladas.isEmpty()) {
            return cartasReveladas.remove(cartasReveladas.size() - 1);
        } else {
            return null;
        }
    }
}
