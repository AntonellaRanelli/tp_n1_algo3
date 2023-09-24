import java.util.List;

public class Fundacion {
    private Palo palo;
    private List<Carta> cartas;

    public Fundacion(){

    }

    public Palo getPalo() {
        return palo;
    }

    public List<Carta> getCartas() { //Constructor
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public Carta obtenerUltimaCarta() {// Devuelve la ultima carta de la fundacion , o sea la carta visible
        if (!cartas.isEmpty()) {
            int lastIndex = cartas.size() - 1;
            return cartas.get(lastIndex);
        }
        return null; // Si la lista está vacía, devuelve null
    }

    public void agregarCarta(Carta carta) {
        if (cartas.isEmpty() && (carta.getNumero() == Valor.AS))
            palo = carta.getPalo();

        cartas.add(carta);
    }

    public boolean mazoVacio()
    {
        return cartas.isEmpty();
    }

    public String toString(){
        return " " + cartas;
    }
}
