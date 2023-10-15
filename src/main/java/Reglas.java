import java.util.List;

public class Reglas {

    public static boolean validarMovimientoEntreColumnas(List<Carta> cartasAMover, Carta cartaColumnaDestino) {
        // Verificar si la cartaColumnaDestino es NULL
        if (cartaColumnaDestino == null) {
            // La primera carta debe ser 13
            return !cartasAMover.isEmpty() && cartasAMover.get(0).getNumero() == Valor.K;
        } else {
            // Verificar si el color de la cartaColumnaDestino no es igual a la primera carta de cartasAMover
            if (cartaColumnaDestino.getColor().equals(cartasAMover.get(0).getColor())) {
                return false;
            }
            // Verificar si el número de la cartaColumnaDestino es mayor por 1
            return cartaColumnaDestino.getNumero().ordinal() - cartasAMover.get(0).getNumero().ordinal() == 1;
        }// Movimiento válido
    }

    public static boolean validarMovimientoAFundacion(Carta cartaColumna, Carta cartaAIngresar) {//se arregla el incumplimiento de DRY
        if (cartaAIngresar == null && cartaColumna.getNumero() == Valor.AS) {
            return true;
        }

        return cartaAIngresar != null &&
                cartaColumna.getPalo() == cartaAIngresar.getPalo() &&
                cartaColumna.getNumero().ordinal() == cartaAIngresar.getNumero().ordinal() + 1;
    }


    public static boolean validarMovimientoAColumna(Carta cartaColumna, Carta cartaAIngresar) {//se arregla el incumplimiento de DRY
        if (cartaColumna == null || cartaAIngresar == null || cartaColumna.getColor() == cartaAIngresar.getColor()) {
            return false;
        }

        return cartaAIngresar.getNumero().ordinal() + 1 == cartaColumna.getNumero().ordinal();
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

    public  static boolean validarExistenciaCarta(Carta carta)
    {
        if (carta == null){
            return false;
        }
        return true;
    }

}