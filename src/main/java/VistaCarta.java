import Base.Carta;
import javafx.scene.image.Image;

public class VistaCarta {
    Carta carta;
    String direccionCarta;

    Image imagenCarta;

    public VistaCarta(Carta carta){
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
