public class Reglas {
    public Reglas(){

    }

    public static boolean movimientoValido(Maso maso, Carta cartaActual){
        Carta cartaMaso = maso.desapilarCarta();
        Valor numeroCartaMaso = cartaMaso.obtenerNumero();
        Valor numeroCartaActual = cartaActual.obtenerNumero();

        if (cartaMaso.obtenerColor() == cartaActual.obtenerColor()) {
            maso.apilarCarta(cartaMaso);
            return false;
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
