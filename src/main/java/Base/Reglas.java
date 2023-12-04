package Base;

import Base.Carta;
import Base.Fundacion;

import java.io.Serializable;
import java.util.List;

public interface Reglas extends Serializable {

    boolean validarMovimientoEntreColumnas(List<Carta> cartasAMover, Carta cartaColumnaDestino);
    boolean validarMovimientoAFundacion(Carta cartaColumna, Carta cartaAIngresar);
    boolean verificarJuegoGanado(List<Fundacion> fundaciones);
    boolean validarExistenciaCarta(Carta carta);

}
