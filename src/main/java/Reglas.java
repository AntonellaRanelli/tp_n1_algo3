import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Reglas {
    public Reglas(){

    }

    public static List<Carta> iniciarJuego()
    {
        List<Carta> cartas = crearCartas();
        mezclarCartas(cartas);

        return cartas;
    }

    private static List<Carta> crearCartas()
    {
        List<Carta> cartas = new ArrayList<>();

        for (Palo palo: Palo.values())
        {
            for(Valor valor: Valor.values())
            {
                if (palo.ordinal() < 2)
                {
                    cartas.add(new Carta(ColorCarta.ROJO, palo, valor, false));
                }else {
                    cartas.add(new Carta(ColorCarta.NEGRO, palo, valor, false));
                }
            }
        }
        return cartas;
    }

    private static void mezclarCartas(List<Carta> cartas)
    {
        Random rand = new Random();
        for (int i = cartas.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            Carta temp = cartas.get(i);
            cartas.set(i, cartas.get(j));
            cartas.set(j, temp);
        }
    }

    public static boolean movimientoValido(Maso maso, Carta cartaActual){
        Carta cartaMaso = maso.desapilarCarta();
        Valor numeroCartaMaso = cartaMaso.obtenerNumero();
        Valor numeroCartaActual = cartaActual.obtenerNumero();

        if (cartaMaso.obtenerColor() == cartaActual.obtenerColor()) {
            maso.apilarCarta(cartaMaso);
        }else{
            if (numeroCartaMaso.ordinal() == (numeroCartaActual.ordinal() + 1)){
                maso.apilarCarta(cartaMaso);
                maso.apilarCarta(cartaActual);
                return true;
            }
        }
        return false;
    }

    public static boolean movimientoValidoFoundation(Maso maso, Carta cartaActual){

        Valor numeroCartaActual = cartaActual.obtenerNumero();
        Palo paloCartaActual = cartaActual.obtenerPalo();
        ColorCarta colorCartaActual = cartaActual.obtenerColor();

        Carta cartaMaso = maso.desapilarCarta();
        Valor numeroCartaMaso;
        Palo paloCartaMaso;
        ColorCarta colorCartaMaso;

        if (cartaMaso == null && (numeroCartaActual == Valor.AS)){
            maso.apilarCarta(cartaActual);
            return true;
        }

        if (cartaMaso == null && (numeroCartaActual != Valor.AS))
            return false;

        numeroCartaMaso = cartaMaso.obtenerNumero();
        paloCartaMaso = cartaMaso.obtenerPalo();
        colorCartaMaso = cartaMaso.obtenerColor();

        if (colorCartaMaso!= colorCartaActual || paloCartaMaso != paloCartaActual){
            maso.apilarCarta(cartaMaso);
            return false;
        }

        if (numeroCartaActual.ordinal() == numeroCartaMaso.ordinal() + 1){
            maso.apilarCarta(cartaMaso);
            maso.apilarCarta(cartaActual);
            return true;
        }
        return false;
    }
}
