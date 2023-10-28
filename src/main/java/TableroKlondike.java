import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TableroKlondike extends Tablero {

    public TableroKlondike() { //Constructor
        iniciarJuego();
    }

    private ReglasKlondike reglas;
    public TableroKlondike(List<Columna> columnas, List<Fundacion> fundaciones, Mazo mazo, ReglasKlondike reglas)
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

        for (Palo palo: Palo.values())
        {
            for(Valor valor: Valor.values())
            {
                if (palo.ordinal() < 2)
                {
                    cartas.add(new Carta(ColorCarta.ROJO, palo, valor));
                }else {
                    cartas.add(new Carta(ColorCarta.NEGRO, palo, valor));
                }
            }
        }
        return cartas;
    }

    private static List<Columna> crearColumnas()
    {
        List<Columna> columnasAuxiliar = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Columna columna = new Columna(new ArrayList<>(), new ArrayList<>());
            columnasAuxiliar.add(columna);
        }
        return columnasAuxiliar;
    }

    private static List<Fundacion> crearFundaciones()
    {
        List<Fundacion> fundacionesAuxiliar = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Fundacion fundacion = new Fundacion();
            fundacionesAuxiliar.add(fundacion);
        }
        return fundacionesAuxiliar;
    }

    @Override
    protected void repartirCartas() { //reparte las carta entre las columnas el resto va al mazo
        int cartasPorColumna = 1;
        List<Carta> listaAuxiliar = new ArrayList<>();

        // Reiniciar el mazo
        mazo.resetearMazo();

        for (Columna columna : columnas) {
            //Arreglo el orden en que se reparten las cartas, issue 6

            // Asignar las cartas ocultas
            for (int i = 0; i < cartasPorColumna - 1; i++) {
                if (!baraja.isEmpty()) {
                    Carta cartaOculta = baraja.remove(0);
                    listaAuxiliar.add(cartaOculta);
                }
                columna.setCartasOcultas(listaAuxiliar);
            }

            // Asignar una carta revelada
            if (!baraja.isEmpty()) {
                Carta cartaRevelada = baraja.remove(0);
                columna.agregarCarta(cartaRevelada);
            }

            listaAuxiliar.clear();
            cartasPorColumna++;
        }

        // Agregar las cartas restantes al mazo
        mazo.setCartasOcultas(new ArrayList<>(baraja));
    }

    public static Tablero crearJuegoVacioParaTest(ReglasKlondike reglas){
        Mazo mazoAuxiliar = new Mazo(new ArrayList<>(), new ArrayList<>());
        Tablero tablero = new TableroKlondike(crearColumnas(), crearFundaciones(), mazoAuxiliar, reglas);
        return tablero;
    }

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


    public boolean moverMazoAFundacion(Mazo mazo, Fundacion fundacion){
        Carta cartaAuxiliar = mazo.obtenerUltimaCartaRevelada();

        if (!reglas.validarExistenciaCarta(cartaAuxiliar))
            return false;


        if (reglas.validarMovimientoAFundacion(cartaAuxiliar, fundacion.obtenerUltimaCarta()))
        {
            fundacion.agregarCarta(cartaAuxiliar);
            mazo.entregarCarta();
            return true;
        }
        return false;
    }



    //arreglo issue 1
    public  boolean moverFundacionAColumna(Fundacion fundacion, Columna columna)
    {
        Carta ultimaCartaFundacion = fundacion.obtenerUltimaCarta();
        Carta ultimaCartaColumna = columna.obtenerUltimaCartaRevelada();

        if (reglas.validarMovimientoAColumna(ultimaCartaColumna, ultimaCartaFundacion)){
            fundacion.eliminarUltimaCarta();
            columna.agregarCarta(ultimaCartaFundacion);

            return true;
        }
        return false;
    }

    @Override
    public boolean moverMazoAColumna(Mazo mazo, Columna columna){
        Carta ultimaCartaMazo = mazo.obtenerUltimaCartaRevelada();
        Carta ultimaCartaColumna = columna.obtenerUltimaCartaRevelada();

        if (reglas.validarMovimientoAColumna(ultimaCartaColumna, ultimaCartaMazo)){
            columna.agregarCarta(ultimaCartaMazo);
            mazo.entregarCarta();
            return true;
        }
        return false;
    }

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