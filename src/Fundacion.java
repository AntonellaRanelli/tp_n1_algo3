import java.util.List;

public class Fundacion {
    private String palo;
    private List<Carta> cartas;

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
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
        cartas.add(carta);
    }
}
