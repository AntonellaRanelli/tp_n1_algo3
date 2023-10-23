import java.io.Serializable;

public class Carta implements Serializable {
    private ColorCarta color;
    private Palo palo;
    private Valor numero;

    public Carta(ColorCarta color, Palo palo, Valor numero) {
        this.color = color;
        this.palo = palo;
        this.numero = numero;
    }

    public ColorCarta getColor() {
        return color;
    }

    public Palo getPalo() {
        return palo;
    }

    public Valor getNumero() {
        return numero;
    }

}
