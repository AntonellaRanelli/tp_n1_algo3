package Spider;

import Base.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TableroSpider extends Tablero {
    private ReglasSpider reglas;

    public TableroSpider() { //Constructor
        iniciarJuego();
    }

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
    protected List<Carta> crearCartas()// Crea mazo de cartas de picas
    {
        List<Carta> cartas = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (Valor valor : Valor.values()) {
                cartas.add(new Carta(ColorCarta.NEGRO, Palo.PICAS, valor));
            }
        }
        return cartas;
    }

    protected static List<Columna> crearColumnas() // Crea lista de columna size 10
    {
        List<Columna> columnasAuxiliar = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Columna columna = new Columna(new ArrayList<>(), new ArrayList<>());
            columnasAuxiliar.add(columna);
        }
        return columnasAuxiliar;
    }

    protected static List<Fundacion> crearFundaciones() // Crea lista fundaciones size 8
    {
        List<Fundacion> fundacionesAuxiliar = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Fundacion fundacion = new Fundacion();
            fundacionesAuxiliar.add(fundacion);
        }
        return fundacionesAuxiliar;
    }


    @Override
    protected void repartirCartas() { //reparte las cartas y crea el mazo de cartas ocultas
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

    //Habia un error en esta funcion porque faltaba el tablero spider y lo agregue
    public static TableroSpider crearJuegoVacioParaTest(){
        Mazo mazoAuxiliar = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnasAuxiliar = crearColumnas();
        List<Fundacion> fundacionesAuxiliar = crearFundaciones();
        ReglasSpider reglas = new ReglasSpider();
        TableroSpider tablero = new TableroSpider(columnasAuxiliar, fundacionesAuxiliar, mazoAuxiliar, reglas);
        return tablero;
    }

 public boolean moverColumnaAFundacion(Columna columna, Fundacion fundacion)
    {
        List<Carta> cartasOrdenadas = new ArrayList<>();
        List<Carta> cartasDeColumna = new ArrayList<>();

        cartasDeColumna = columna.getCartasReveladas();

        int tamanoLista = cartasDeColumna.size()-1;
        int cantidadUltimasCartas = 12;

        if(seCumpleSecuenciaCompleta(columna))
        {

            for(int i = tamanoLista; i > tamanoLista - cantidadUltimasCartas ; i--)
            {
                Carta cartaAux = cartasDeColumna.get(i);
                cartasOrdenadas.add(cartaAux);


            }

            columna.sacarCartas(cartasOrdenadas);
            fundacion.agregarCarta(cartasOrdenadas);

            return true;
        }
        return false;
    }


    public boolean seCumpleSecuenciaCompleta(Columna columna) {
        List<Carta> cartas = columna.getCartasReveladas();

        if (cartas.size() < 13) {
            return false;
        }
        Valor[] secuenciaEsperada = {Valor.AS, Valor.DOS, Valor.TRES, Valor.CUATRO, Valor.CINCO, Valor.SEIS, Valor.SIETE, Valor.OCHO, Valor.NUEVE, Valor.DIEZ, Valor.J, Valor.Q, Valor.K,};

        int indice = cartas.size()-1;

        for (Valor valor : secuenciaEsperada) {
            Carta carta = cartas.get(indice);

            if (carta.getNumero() != valor) {

                return false;
            }
            indice--;
        }

        return true;
    }





    //Rehice
    @Override
    public boolean moverMazoAColumna(Mazo mazo, Columna columna){
        int cantidadColumnas = columnas.size();

        for (int i = 0; i < cantidadColumnas; i++) {
            Columna columnaActual = columnas.get(i);
            if (!mazo.cartasOcultasVacio()) {
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
