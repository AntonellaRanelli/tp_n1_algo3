import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//TODO: Para solucionar este error hay que sacar el metodo mover mazo a fundacion ya que no es comun a ambos juegos de Tablero.
public class TableroSpider extends Tablero{

    public TableroSpider() { //Constructor
        iniciarJuego();
    }

    private ReglasSpider reglas;
    public TableroSpider(List<Columna> columnas, List<Fundacion> fundaciones, Mazo mazo, ReglasSpider reglas)
    {
        this.columnas = columnas;
        this.fundaciones = fundaciones;
        this.mazo = mazo;
        this.reglas = reglas;
    }

    @Override
    protected void iniciarJuego() { // iniciador de juego con semilla aleatoria; semilla tipo long
        Random random = new Random();
        baraja = crearCartas();
        columnas = crearColumnas();
        fundaciones = crearFundaciones();
        Collections.shuffle(baraja, random);
        repartirCartas();
    }

    @Override
    protected List<Carta> crearCartas()
    {
        List<Carta> cartas = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (Valor valor : Valor.values()) {
                cartas.add(new Carta(ColorCarta.NEGRO, Palo.PICAS, valor));
            }
        }
        return cartas;
    }

    protected static List<Columna> crearColumnas()
    {
        List<Columna> columnasAuxiliar = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Columna columna = new Columna(new ArrayList<>(), new ArrayList<>());
            columnasAuxiliar.add(columna);
        }
        return columnasAuxiliar;
    }

    protected static List<Fundacion> crearFundaciones()
    {
        List<Fundacion> fundacionesAuxiliar = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Fundacion fundacion = new Fundacion();
            fundacionesAuxiliar.add(fundacion);
        }
        return fundacionesAuxiliar;
    }


    @Override
    protected void repartirCartas() {
        List<Carta> listaAuxiliar = new ArrayList<>();
        Columna columaActual;
        int cartasPorColumna = 6;
        int cantidadColumnas = columnas.size();

        for (int i=0; i<cantidadColumnas; i++) {
            columaActual = columnas.get(i);

            if (i>=4)
                cartasPorColumna = 5;

            for (int u = 0; u < cartasPorColumna - 1; u++) {

                if (!baraja.isEmpty()) {
                    Carta cartaOculta = baraja.remove(0);
                    listaAuxiliar.add(cartaOculta);
                }
            }
            columaActual.setCartasOcultas(listaAuxiliar);

            // Asignar una carta revelada
            if (!baraja.isEmpty()) {
                Carta cartaRevelada = baraja.remove(0);
                columaActual.agregarCarta(cartaRevelada);
            }

            listaAuxiliar.clear();
            cartasPorColumna++;
        }

        // Agregar las cartas restantes al mazo
        mazo.setCartasOcultas(new ArrayList<>(baraja));
    }

    public static Tablero crearJuegoVacioParaTest(){
        Mazo mazoAuxiliar = new Mazo(new ArrayList<>(), new ArrayList<>());
        Tablero tablero = new TableroSpider(crearColumnas(), crearFundaciones(), mazoAuxiliar);
        return tablero;
    }


    //TODO: no se me ocurrio como modificarlo, deberiamos ver si tenemos que crear un metodo que sea if columnacompleta osea las 13 cartas ordenadas y que ahi se muevan todas automaticamente
    //Rehacer
    @Override
    public boolean moverColumnaAFundacion(Columna columna, Fundacion fundacion)
    {
        Carta cartaAuxiliar = columna.obtenerUltimaCartaRevelada();

        if (!reglas.validarExistenciaCarta(cartaAuxiliar)) //Unifico validaciones en Reglas issue 9
            return false;

        List<Carta> arregloAuxiliar = new ArrayList<>();
        arregloAuxiliar.add(cartaAuxiliar);

        if (reglas.validarMovimientoAFundacion(cartaAuxiliar, fundacion.obtenerUltimaCarta()))
        {
            fundacion.agregarCarta(cartaAuxiliar);
            columna.sacarCartas(arregloAuxiliar);
            return true;
        }

        return false;
    }


    //Rehice
    @Override
    public boolean moverMazoAColumna(Mazo mazo, Columna columna){
        int cantidadColumnas = columnas.size();

        for (int i = 0; i < cantidadColumnas; i++) {
            Columna columnaActual = columnas.get(i);
            if (!mazo.cartasOcultas.isEmpty()) {
                Carta ultimaCartaMazo = mazo.obtenerUltimaCartaRevelada();
                columnaActual.agregarCarta(ultimaCartaMazo);
                mazo.entregarCarta();
            }
        }
        return true;
    }


    //Rehice
    @Override
    public boolean moverColumnaAColumna(Columna columnaOrigen, Columna columnaDestino, List<Carta> cartasAMover )
    {
        Carta ultimaCartaRCD = columnaDestino.obtenerUltimaCartaRevelada();

        if(reglas.validarMovimientoEntreColumnas(cartasAMover, ultimaCartaRCD))
        {
            columnaDestino.agregarCarta(columnaDestino.getCartasReveladas());
            columnaOrigen.sacarCartas(cartasAMover);
            return true;
        }
        return false;
    }

}
