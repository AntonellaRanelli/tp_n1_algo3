import java.util.ArrayList;
import java.util.List;

public class Fundacion {
    private Palo palo;
    private List<Carta> cartas;

    public Fundacion(){
        this.cartas = new ArrayList<>();
    }

    public Palo getPalo() {
        return palo;
    }

    public List<Carta> getCartas() { //Constructor
        return cartas;
    }

    public void setCartas(List<Carta> cartas) { //Se deberia borrar, no lo borré por los tests issue 2
        this.cartas = cartas;
    }

    public Carta obtenerUltimaCarta() {// Devuelve la ultima carta de la fundacion , o sea la carta visible
        if (!fundacionVacia()) { //Uso del metodo mazoVacio() issue 3
            int lastIndex = cartas.size() - 1;
            return cartas.get(lastIndex);
        }
        return null; // Si la lista está vacía, devuelve null
    }

    public void agregarCarta(Carta carta) { // En Fundacion solo necesita que se agregue una sola carta a la vez
        if (fundacionVacia() && (carta.getNumero() == Valor.AS)) //Uso del metodo mazoVacio() issue 3
            palo = carta.getPalo();

        cartas.add(carta);
    }

    public void eliminarUltimaCarta(){
        if (!fundacionVacia()) { //Uso del metodo mazoVacio() issue 3
            int lastIndex = cartas.size() - 1;
            cartas.remove(lastIndex);
        }
    }

    public boolean fundacionVacia()
    {
        return cartas.isEmpty();
    }

}
