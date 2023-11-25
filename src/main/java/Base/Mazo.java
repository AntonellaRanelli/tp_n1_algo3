package Base;

import Base.Carta;
import Base.CartasApilable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mazo extends CartasApilable {

    public Mazo(List<Carta> cartasReveladas, List<Carta> cartasOcultas) { // Constructor
        super(cartasReveladas, cartasOcultas);
    }

    @Override
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

    public void resetearMazo() { //Vacia la lista de cartasReveladas y las agrega a cartas ocultas
        for(Carta carta: cartasReveladas)
            cartasOcultas.add(0, carta);
        cartasReveladas.clear();
    }

}
