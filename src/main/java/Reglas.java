import java.util.List;

public class Reglas {

    public static boolean validarMovimientoEntreColumnas(List<Carta> cartasAMover, Carta cartaColumnaDestino) {
        // Verificar si la cartaColumnaDestino es NULL
        if (cartaColumnaDestino == null) {
            // La primera carta debe ser 13
            if (cartasAMover.isEmpty() || cartasAMover.get(0).getNumero() != Valor.K) {
                return false;
            }
        } else {
            // Verificar si el color de la cartaColumnaDestino no es igual a la primera carta de cartasAMover
            if (!cartaColumnaDestino.getColor().equals(cartasAMover.get(0).getColor())) {
                return false;
            }

            // Verificar si el número de la cartaColumnaDestino es menor por 1
            if (cartaColumnaDestino.getNumero().ordinal() != cartasAMover.get(0).getNumero().ordinal() - 1) {
                return false;
            }
        }

        return true; // Movimiento válido
    }

    public static boolean validarMovimientoColumnaAFundacion(Carta cartaColumna, Carta cartaReveladaFundacion)
    {
        if (cartaReveladaFundacion == null && (cartaColumna.getNumero() == Valor.AS)) {
            return true;
        }

        if (cartaReveladaFundacion != null){
            if (cartaColumna.getPalo() == cartaReveladaFundacion.getPalo()) {
                if (cartaColumna.getNumero().ordinal() == cartaReveladaFundacion.getNumero().ordinal() + 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean validarMovimientoMazoAColumna( List<Carta> cartasReveladasColumna, Carta ultimaCartaReveladaMazo) {
        // Verificar si la lista de cartas reveladas de columna es null o vacía
        if (cartasReveladasColumna == null || cartasReveladasColumna.isEmpty()) {
            return false;
        }

        // Verificar si la última carta revelada del mazo no es del mismo color que la última carta de la lista de columna
        if (!ultimaCartaReveladaMazo.getColor().equals(cartasReveladasColumna.get(cartasReveladasColumna.size() - 1).getColor())) {
            // Verificar si el número de la última carta revelada del mazo es menor por 1 que el de la última carta de la lista de columna
            if (ultimaCartaReveladaMazo.getNumero().ordinal() == cartasReveladasColumna.get(cartasReveladasColumna.size() - 1).getNumero().ordinal() - 1) {
                return true;
            }
        }

        return false; // Movimiento no válido
    }

    public static boolean validarMovimientoMazoAFundacion( List<Carta> ultimaCartaReveladaFundacion, Carta ultimaCartaReveladaMazo) {
        // Verificar si la lista de última carta revelada de fundación es null
        if (ultimaCartaReveladaFundacion == null) {
            // La última carta revelada de mazo debe ser igual a 1
            if (ultimaCartaReveladaMazo != null && ultimaCartaReveladaMazo.getNumero() == Valor.AS) {
                return true;
            }
        } else {
            // Asegurarse de que la lista de última carta revelada de fundación contenga solo una carta
            if (ultimaCartaReveladaFundacion.size() == 1) {
                Carta cartaFundacion = ultimaCartaReveladaFundacion.get(0);

                // Verificar si la última carta revelada de mazo es del mismo palo que la última carta de la lista de fundación
                if (ultimaCartaReveladaMazo != null && ultimaCartaReveladaMazo.getPalo().equals(cartaFundacion.getPalo())) {
                    // Verificar si el número de la última carta revelada de mazo es mayor por 1 que el de la última carta de la lista de fundación
                    if (ultimaCartaReveladaMazo.getNumero().ordinal() == cartaFundacion.getNumero().ordinal() + 1) {
                        return true;
                    }
                }
            }
        }

        return false; // Movimiento no válido
    }

    public static boolean validarMovimientoFundacionAColumna(Carta cartaColumna, Carta cartaFundacion){

        if (cartaColumna == null || cartaFundacion == null)
            return false;

        if(cartaColumna.getColor() == cartaFundacion.getColor())
            return false;

        Valor numeroCartaColumna = cartaColumna.getNumero();
        Valor numeroCartaFundacion = cartaFundacion.getNumero();

        if (numeroCartaFundacion.ordinal() + 1 == numeroCartaColumna.ordinal())
            return true;

        return false;
    }
    public static boolean verificarJuegoGanado(List<Fundacion> fundaciones) {
        int cantidadMaximaCartas = 13;
        for (Fundacion fundacion : fundaciones) {
            List<Carta> cartas = fundacion.getCartas();
            if (cartas.size() != cantidadMaximaCartas) {
                return false;
            }
        }
        return true; // Todas las fundaciones tienen 13 cartas, el juego está ganado
    }

}
