import java.awt.*;
import java.awt.Color;

public class Carta {
    private Palo palo;

    private Valor numero;

    public Carta(Palo palo, Valor numero)
    {

        this.palo = palo;
        this.numero = numero;

    }

    public Palo obtenerPalo()
    {
        return palo;
    }

    public Valor obtenerNumero()
    {
        return numero;
    }

    public String toString(){
        String devolver = "Palo: " + palo + ", Valor: "+ numero;
        return devolver;
    }
}
