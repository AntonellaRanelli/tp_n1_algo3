package Spider;

import Base.Carta;
import Base.Fundacion;
import Base.Reglas;
import Base.Valor;

import java.util.List;

public class ReglasSpider implements Reglas {
    @Override
    public boolean validarMovimientoEntreColumnas(List<Carta> cartasAMover, Carta cartaColumnaDestino) {
        // Verificar si la cartaColumnaDestino es NULL
        if (cartaColumnaDestino == null) {
            // La primera carta debe ser 13
            return true;
        } else {
            // Verificar si el número de la cartaColumnaDestino es mayor por 1
            boolean sigueElOrden = cartaColumnaDestino.getNumero().ordinal() - cartasAMover.get(0).getNumero().ordinal() == 1;
            boolean rompePatron = false;
            // Verificar que todas las cartas a mover mantengan el patrón
            for(int i = 0; i < cartasAMover.size(); i++) {
                if(i + 1 < cartasAMover.size()) {
                    Carta cartaActual = cartasAMover.get(i);
                    Carta proximaCarta = cartasAMover.get(i+1);
                    if(cartaActual.getNumero().ordinal() - proximaCarta.getNumero().ordinal()  != 1) {
                        rompePatron = true;
                    }
                }
            }

            return sigueElOrden && !rompePatron;
        }// Movimiento válido
    }

    @Override
    public boolean validarMovimientoAFundacion(Carta cartaColumna, Carta cartaAIngresar) {//se arregla el incumplimiento de DRY
        if (cartaAIngresar == null && cartaColumna.getNumero() == Valor.AS) {
            return true;
        }

        return cartaAIngresar != null &&
                cartaColumna.getNumero().ordinal() == cartaAIngresar.getNumero().ordinal() + 1;
    }


    //TODO : hay que corregir los test
    @Override
    public boolean verificarJuegoGanado(List<Fundacion> fundaciones) {
        int cantidadMaximaCartas = 13;
        for (Fundacion fundacion : fundaciones) {
            List<Carta> cartas = fundacion.getCartas();
            if (cartas.size() != cantidadMaximaCartas) {
                return false;
            }
        }
        return true; // Todas las fundaciones tienen 13 cartas, el juego está ganado
    }

    @Override
    public boolean validarExistenciaCarta(Carta carta)
    {
        if (carta == null){
            return false;
        }
        return true;
    }
}