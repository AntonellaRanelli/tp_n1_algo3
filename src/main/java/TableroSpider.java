import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TableroSpider extends Tablero{
    private List<Carta> baraja;
    private List<Columna> columnas;
    private List<Fundacion> fundaciones;
    private List<Mazo> mazos;

    public TableroSpider(List<Columna> columnas, List<Fundacion> fundaciones, List<Mazo> mazo)
    {
        this.columnas = columnas;
        this.fundaciones = fundaciones;
        this.mazos = mazo;
    }

    public TableroSpider() { //Constructor
        iniciarJuego();
    }

    @Override
    protected void iniciarJuego() { // iniciador de juego con semilla aleatoria; semilla tipo long
        Random random = new Random();
        baraja = crearCartas();
        columnas = crearColumnas();
        fundaciones = crearFundaciones();
        mazos = crearMazos();
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
                    cartas.add(new Carta(ColorCarta.ROJO, palo, valor));
                }else {
                    cartas.add(new Carta(ColorCarta.NEGRO, palo, valor));
                    cartas.add(new Carta(ColorCarta.NEGRO, palo, valor));
                }
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

    protected static List<Mazo> crearMazos(){
        int cantidadMazos = 4;
        List<Mazo> mazos = new ArrayList<>();

        for (int i=0; i<cantidadMazos; i++)
            mazos.add(new Mazo(new ArrayList<>(), new ArrayList<>()));

        return mazos;
    }

    @Override
    protected void repartirCartas() {
        List<Carta> listaAuxiliar = new ArrayList<>();
        Columna columaActual;
        int cartasPorColumna = 6;
        int cantidadColumnas = columnas.size();

        for (int i=0; i<cantidadColumnas; i++) {
            columaActual = columnas.get(i);

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

            if (i==3)
                cartasPorColumna = 5;

            listaAuxiliar.clear();
            cartasPorColumna++;
        }

        // Agregar las cartas restantes a los mazos
        for (Mazo mazo:mazos){
            listaAuxiliar.clear();
            for (int i=0; i<10; i++){
                listaAuxiliar.add(baraja.remove(0));
            }
            mazo.setCartasOcultas(listaAuxiliar);
        }
    }

    public static TableroSpider crearJuegoVacioParaTest(){
        List<Mazo> mazosAuxiliar = crearMazos();
        TableroSpider tablero = new TableroSpider(crearColumnas(), crearFundaciones(), mazosAuxiliar);
        return tablero;
    }

    @Override
    public Columna getColumnaPorIndice(int indice){
        return columnas.get(indice);
    }

    @Override
    public Fundacion getFundacionPorIndice(int indice){
        return fundaciones.get(indice);
    }

    @Override //Rehacer
    public Mazo getMazo(){
        return null;
    }

    //Rehacer
    @Override
    public boolean moverColumnaAFundacion(Columna columna, Fundacion fundacion)
    {
        Carta cartaAuxiliar = columna.obtenerUltimaCartaRevelada();

        if (!Reglas.validarExistenciaCarta(cartaAuxiliar)) //Unifico validaciones en Reglas issue 9
            return false;

        List<Carta> arregloAuxiliar = new ArrayList<>();
        arregloAuxiliar.add(cartaAuxiliar);

        if (Reglas.validarMovimientoAFundacion(cartaAuxiliar, fundacion.obtenerUltimaCarta()))
        {
            fundacion.agregarCarta(cartaAuxiliar);
            columna.sacarCartas(arregloAuxiliar);
            return true;
        }

        return false;
    }

    //Rehacer
    @Override
    public boolean moverMazoAFundacion(Mazo mazo, Fundacion fundacion){
        Carta cartaAuxiliar = mazo.obtenerUltimaCartaRevelada();

        if (!Reglas.validarExistenciaCarta(cartaAuxiliar))
            return false;


        if (Reglas.validarMovimientoAFundacion(cartaAuxiliar, fundacion.obtenerUltimaCarta()))
        {
            fundacion.agregarCarta(cartaAuxiliar);
            mazo.entregarCarta();
            return true;
        }
        return false;
    }

    //Rehacer
    @Override
    public  boolean moverFundacionAColumna(Fundacion fundacion, Columna columna)
    {
        Carta ultimaCartaFundacion = fundacion.obtenerUltimaCarta();
        Carta ultimaCartaColumna = columna.obtenerUltimaCartaRevelada();

        if (Reglas.validarMovimientoAColumna(ultimaCartaColumna, ultimaCartaFundacion)){
            fundacion.eliminarUltimaCarta();
            columna.agregarCarta(ultimaCartaFundacion);

            return true;
        }
        return false;
    }

    //Rehacer
    @Override
    public boolean moverMazoAColumna(Mazo mazo, Columna columna){
        Carta ultimaCartaMazo = mazo.obtenerUltimaCartaRevelada();
        Carta ultimaCartaColumna = columna.obtenerUltimaCartaRevelada();

        if (Reglas.validarMovimientoAColumna(ultimaCartaColumna, ultimaCartaMazo)){
            columna.agregarCarta(ultimaCartaMazo);
            mazo.entregarCarta();
            return true;
        }
        return false;
    }

    //Rehacer
    @Override
    public boolean moverColumnaAColumna(Columna columnaOrigen, Columna columnaDestino, List<Carta> cartasAMover )
    {
        Carta ultimaCartaRCD = columnaDestino.obtenerUltimaCartaRevelada();

        if(Reglas.validarMovimientoEntreColumnas(cartasAMover, ultimaCartaRCD))
        {
            columnaDestino.agregarCarta(columnaDestino.getCartasReveladas());
            columnaOrigen.sacarCartas(cartasAMover);
            return true;
        }
        return false;
    }

}