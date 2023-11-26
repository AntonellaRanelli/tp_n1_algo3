package Base;

import java.io.Serializable;


import Base.Carta;
import Base.Fundacion;

import java.util.List;

public interface Reglas extends Serializable {

    boolean validarMovimientoEntreColumnas(List<Carta> cartasAMover, Carta cartaColumnaDestino);
    boolean validarMovimientoAFundacion(Carta cartaColumna, Carta cartaAIngresar);
    boolean verificarJuegoGanado(List<Fundacion> fundaciones);
    boolean validarExistenciaCarta(Carta carta);

}
