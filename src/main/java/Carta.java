import java.awt.*;
import java.awt.Color;

public class Carta {
    private ColorCarta color;

    private Palo palo;

    private Valor numero;

    private boolean volteada;

    public Carta(ColorCarta color, Palo palo, Valor numero, boolean volteada)
    {
        this.color = color;
        this.palo = palo;
        this.numero = numero;
        this.volteada = volteada;
    }

    public ColorCarta obtenerColor()
    {
        return color;
    }

    public Palo obtenerPalo()
    {
        return palo;
    }

    public Valor obtenerNumero()
    {
        return numero;
    }

    public boolean cartaVolteada()
    {
        return volteada;
    }

    public boolean voltearCartaCara()
    {
        if (!volteada)
            volteada = true;
        return volteada;
    }

    public boolean voltearCartaReverso()
    {
        if (volteada)
            volteada = false;
        return volteada;
    }
}
