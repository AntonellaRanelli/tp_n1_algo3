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

    public static boolean validarMovimientoAFundacion(Carta cartaColumna, Carta cartaAIngresar)
    {
        if (cartaAIngresar == null && (cartaColumna.getNumero() == Valor.AS)) {
            return true;
        }

        if (cartaAIngresar != null){
            if (cartaColumna.getPalo() == cartaAIngresar.getPalo()) {
                if (cartaColumna.getNumero().ordinal() == cartaAIngresar.getNumero().ordinal() + 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean validarMovimientoAColumna(Carta cartaColumna, Carta cartaAIngresar){

        if (cartaColumna == null || cartaAIngresar == null)
            return false;

        if(cartaColumna.getColor() == cartaAIngresar.getColor())
            return false;

        Valor numeroCartaColumna = cartaColumna.getNumero();
        Valor numeroCartaFundacion = cartaAIngresar.getNumero();

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
