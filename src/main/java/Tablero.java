import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Tablero implements Serializable {
    protected List<Carta> baraja = new ArrayList<>();
    protected List<Columna> columnas = new ArrayList<>();
    protected List<Fundacion> fundaciones = new ArrayList<>();
    protected Mazo mazo = new Mazo( new ArrayList<>(), new ArrayList<>());

    protected abstract void iniciarJuego();

    protected abstract List<Carta> crearCartas();

    protected abstract void repartirCartas();

    public Columna getColumnaPorIndice(int indice){
        return columnas.get(indice);
    }

    public Fundacion getFundacionPorIndice(int indice){
        return fundaciones.get(indice);
    }

    public Mazo getMazo(){
        return mazo;
    }

    public abstract boolean moverColumnaAFundacion(Columna columna, Fundacion fundacion);

    //public abstract boolean moverMazoAFundacion(Mazo mazo, Fundacion fundacion); // hay que sacar esta funcion de aca, no sirve para spider

    //public abstract  boolean moverFundacionAColumna(Fundacion fundacion, Columna columna); // Dejo comentada la funcion porque no sirve para spider

    public abstract boolean moverMazoAColumna(Mazo mazo, Columna columna);

    public abstract boolean moverColumnaAColumna(Columna columnaOrigen, Columna columnaDestino, List<Carta> cartasAMover );

    public void serializar(OutputStream tablero) throws IOException {
        ObjectOutputStream datoAGuardar = new ObjectOutputStream(tablero);
        datoAGuardar.writeObject(this);
    }

    public static Tablero deserializar(InputStream entrada) throws IOException, ClassNotFoundException {
        ObjectInputStream tableroGuardado = new ObjectInputStream(entrada);
        return (Tablero) tableroGuardado.readObject();
    }
}
