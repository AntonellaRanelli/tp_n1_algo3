package Spider;

import Base.Carta;
import javafx.scene.image.Image;

public class VistaCartaSpider {
    Carta carta;
    String direccionCarta;

    Image imagenCarta;

    public VistaCartaSpider(Carta carta){
        this.carta = carta;
        direccionCarta = "/Large/" + carta.getPalo().ordinal() + carta.getNumero().ordinal() + ".png";
        imagenCarta = new Image(direccionCarta);
    }

    public String getDireccionCarta(){
        return direccionCarta;
    }

    public Image getImagenCarta(){
        return imagenCarta;
    }

    public Carta getCarta(){return carta;}
}
