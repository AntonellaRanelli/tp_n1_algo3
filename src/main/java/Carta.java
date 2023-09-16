public class Carta {
    private String color;

    private String palo;

    private String numero;

    private boolean volteada;

    public Carta(String color, String palo, String numero, boolean volteada)
    {
        this.color = color;
        this.palo = palo;
        this.numero = numero;
        this.volteada = volteada;
    }

    public String obtenerColor()
    {
        return color;
    }

    public String obtenerPalo()
    {
        return palo;
    }

    public String obtenerNumero()
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
