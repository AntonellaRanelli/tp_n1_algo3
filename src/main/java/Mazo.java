import java.util.ArrayList;
import java.util.List;

public class Mazo {
    private List<Carta> cartasOcultas;

    private List<Carta> cartasReveladas;

    public Mazo(List<Carta> cartasOcultas, List<Carta> cartasReveladas) { // Constructor
        this.cartasOcultas = cartasOcultas;
        this.cartasReveladas = cartasReveladas;
    }

    public void setCartasOcultas(List<Carta> cartasOcultas) {
        this.cartasOcultas = cartasOcultas;
    }

    public Carta revelarCarta() { // Si cartasOcutas no esta vacio, saca la ultima carta oculta y la agrega a lista de cartas reveladas
        if (!cartasOcultas.isEmpty()) {
            int lastIndex = cartasOcultas.size() - 1;
            Carta cartaRevelada = cartasOcultas.remove(lastIndex);
            cartasReveladas.add(cartaRevelada);
            return cartaRevelada;
        }else{
            resetearMazo();
        }
        return null; // Si no hay cartas ocultas devuelve null
    }

    public Carta entregarCarta() { //devuelve la ultima carta revelada y la elimina de la lista .
        if (!cartasReveladas.isEmpty()) {
            int lastIndex = cartasReveladas.size() - 1;
            return cartasReveladas.remove(lastIndex);
        }
        return null; // Si no hay cartas reveladas devuelve null
    }

    public Carta obtenerUltimaCartaRevelada() {
        if (!cartasReveladas.isEmpty()) {
            int lastIndex = cartasReveladas.size() - 1;
            return cartasReveladas.get(lastIndex);
        }
        return null; // Si no hay cartas reveladas
    }

    public void resetearMazo() { //Vacia la lista de cartasReveladas y las agrega a cartas ocultas
        cartasOcultas.addAll(cartasReveladas);
        cartasReveladas.clear();
    }

}
