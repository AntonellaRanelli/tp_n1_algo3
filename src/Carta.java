public class Carta {

    private String color;
    private String palo;
    private int numero;

    public Carta() {
    }

    public Carta(String color, String palo, int numero) {
        this.color = color;
        this.palo = palo;
        this.numero = numero;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String toString() {
        return "Número: " + numero + ", Palo: " + palo + ", Color: " + color;
    }
}
