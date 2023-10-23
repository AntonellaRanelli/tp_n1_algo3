import java.io.Serializable;
import java.util.List;

public abstract class Tablero implements Serializable {
    protected abstract void iniciarJuego();

    protected abstract List<Carta> crearCartas();

    protected abstract void repartirCartas();

    public abstract Columna getColumnaPorIndice(int indice);

    public abstract Fundacion getFundacionPorIndice(int indice);

    public abstract Mazo getMazo();

    public abstract boolean moverColumnaAFundacion(Columna columna, Fundacion fundacion);

    public abstract boolean moverMazoAFundacion(Mazo mazo, Fundacion fundacion);

    public abstract  boolean moverFundacionAColumna(Fundacion fundacion, Columna columna);

    public abstract boolean moverMazoAColumna(Mazo mazo, Columna columna);

    public abstract boolean moverColumnaAColumna(Columna columnaOrigen, Columna columnaDestino, List<Carta> cartasAMover );

}
