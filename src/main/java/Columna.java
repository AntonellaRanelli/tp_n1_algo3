import java.util.ArrayList;
import java.util.List;

public class Columna {

    private List<Carta> cartasReveladas;
    private List<Carta> cartasOcultas;

    public Columna(List<Carta> cartasReveladas, List<Carta> cartasOcultas) { //Constructor
        this.cartasReveladas = cartasReveladas;
        this.cartasOcultas = cartasOcultas;
    }

    public List<Carta> sacarCarta(int indice) { // Selecciona la lista de cartas que el usuario desea mover y la deevuelve y revela la ultima carta Oculta

        List<Carta> cartasElegidas = new ArrayList<>(cartasReveladas.subList(indice,-1));

        cartasReveladas.removeAll(cartasElegidas);

        if (!cartasOcultas.isEmpty())
        {
            Carta cartaSacada = cartasOcultas.remove(0); // Obtener la primera carta de la lista
            cartasReveladas.add(cartaSacada);
        }
        return cartasElegidas;
    }

    public void agregarCartas(List<Carta> listaDeCartas) {
        cartasReveladas.addAll(listaDeCartas);
    } //recibe una lista de cartas y la agrega al final del la lista de cartas reveladas

    public void agregarCarta(Carta carta){
        cartasReveladas.add(carta);
    }

    public List<Carta> getCartasReveladas() { // Devuelve lista
        return cartasReveladas;
    }

    public void setCartasOcultas(Carta carta){
        cartasOcultas.add(carta);
    }

    public List<Carta> getCartasOcultas() { //Devuelve Lista
        return cartasOcultas;
    }

    public Carta obtenerUltimaCartaRevelada() { //devuelve la ultima carta de la lista reveladas
        if (!cartasReveladas.isEmpty()) {
            int lastIndex = cartasReveladas.size() - 1;
            return cartasReveladas.get(lastIndex);
        }
        return null; // Si no hay cartas reveladas
    }

    public void sacarCartas(List<Carta> cartasASacar) {
        cartasReveladas.removeAll(cartasASacar);
    }

    public String toString(){
        return " " + cartasOcultas + cartasReveladas;
    }
}
