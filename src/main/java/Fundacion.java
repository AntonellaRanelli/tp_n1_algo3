import java.util.ArrayList;
import java.util.List;

public class Fundacion {
    private List<Carta> cartas;

    public Fundacion() {
        cartas = new ArrayList<>();
    }

    public boolean agregarCarta(Carta carta) {
        if (cartaEsSiguiente(carta)) {
            cartas.add(carta);
            return true;
        } else {
            return false;
        }
    }

    private boolean cartaEsSiguiente(Carta carta) {
        if (cartas.isEmpty()) {
            return carta.obtenerNumero() == Valor.AS;
        } else {
            Carta ultimaCarta = cartas.get(cartas.size() - 1);
            return carta.obtenerPalo() == ultimaCarta.obtenerPalo() && carta.obtenerNumero().ordinal() == ultimaCarta.obtenerNumero().ordinal()+1;
        }
    }

    public boolean fundacionCompleta() {
        return cartas.size() == 13;
    }
}
