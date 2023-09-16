import java.util.Stack;

public class Maso
{
    private int cantActual;

    private Stack<Carta> coleccionCartas;

    public Maso()
    {
        cantActual = 0;
        coleccionCartas = new Stack<>();
    }

    public int apilarCarta(Carta carta)
    {
        coleccionCartas.push(carta);
        cantActual++;
        return cantActual;
    }

    public Carta desapilarCarta()
    {
        return coleccionCartas.pop();
    }

    public boolean masoVacio()
    {
        return coleccionCartas.isEmpty();
    }

    public int obtenerCantidadActual()
    {
        return cantActual;
    }
}
