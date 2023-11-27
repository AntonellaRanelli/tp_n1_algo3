package Base;

import Base.Carta;

import java.io.Serializable;
import java.util.List;

public class CartasApilable implements Serializable {
    protected List<Carta> cartasReveladas;
    protected List<Carta> cartasOcultas;

    public CartasApilable(List<Carta> cartasReveladas, List<Carta> cartasOcultas){
        this.cartasReveladas = cartasReveladas;
        this.cartasOcultas = cartasOcultas;
    }

    public void setCartasOcultas(List<Carta> cartasOcultas) {
        this.cartasOcultas = cartasOcultas;
    }

    public Carta obtenerUltimaCartaRevelada() { //devuelve la ultima carta de la lista reveladas
        if (!cartasReveladas.isEmpty()) {
            int lastIndex = cartasReveladas.size() - 1;
            return cartasReveladas.get(lastIndex);
        }
        return null; // Si no hay cartas reveladas
    }

    public Carta revelarCarta() { // Si cartasOcutas no esta vacio, saca la ultima carta oculta y la agrega a lista de cartas reveladas
        if (!cartasOcultas.isEmpty()) {
            int lastIndex = cartasOcultas.size() - 1;
            Carta cartaRevelada = cartasOcultas.remove(lastIndex);
            cartasReveladas.add(cartaRevelada);
            return cartaRevelada;
        }
        return null; // Si no hay cartas ocultas devuelve null
    }

    public boolean cartasOcultasVacio(){
        return cartasOcultas.isEmpty();
    }

    public List<Carta> getCartasReveladas(){
        return cartasReveladas;
    }

    public List<Carta> getCartasOcultas(){return cartasOcultas;}
}
