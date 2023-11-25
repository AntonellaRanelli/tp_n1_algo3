package Base;

import Base.Carta;
import Base.CartasApilable;

import java.util.List;

public class Columna extends CartasApilable {

    public Columna(List<Carta> cartasReveladas, List<Carta> cartasOcultas) {
        super(cartasReveladas, cartasOcultas); //Constructor
    }

    public void agregarCarta(List<Carta> listaDeCartas) {
        cartasReveladas.addAll(listaDeCartas);
    } //recibe una lista de cartas y la agrega al final del la lista de cartas reveladas

    public void agregarCarta(Carta carta)//Agrego una funcion con sobrecarga
    {
        cartasReveladas.add(carta);
    }
    public List<Carta> getCartasReveladas() { // Devuelve lista
        return cartasReveladas;
    }

    public List<Carta> getCartasOcultas() { //Devuelve Lista
        return cartasOcultas;
    }

    public void sacarCartas(List<Carta> cartasASacar) {
        cartasReveladas.removeAll(cartasASacar);
        if (cartasReveladas.isEmpty())
            this.revelarCarta();
    }
}
