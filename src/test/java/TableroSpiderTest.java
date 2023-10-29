import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TableroSpiderTest {
    private TableroSpider tablero;

    @Before
    public void setUp() {
        // Configuración común para las pruebas
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        ReglasSpider reglas = new ReglasSpider();
        tablero = new TableroSpider(columnas, fundaciones, mazo, reglas);
    }

    @Test
    public void testMoverColumnaAFundacion() {
        // Prueba para mover cartas de una columna a una fundación
        Columna columna = new Columna(new ArrayList<>(), new ArrayList<>());
        Fundacion fundacion = new Fundacion();

        // Agregar cartas a la columna
        columna.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.K));
        columna.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DIEZ));
        columna.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.NUEVE));

        assertFalse(tablero.moverColumnaAFundacion(columna, fundacion));

    }

    @Test
    public void testMoverColumnaAFundacionSecuenciaCompleta() {
        // Prueba para mover una secuencia completa de cartas de una columna a una fundación
        Columna columna = new Columna(new ArrayList<>(), new ArrayList<>());
        Fundacion fundacion = new Fundacion();
        Valor[] secuenciaEsperada = {Valor.AS, Valor.DOS, Valor.TRES, Valor.CUATRO, Valor.CINCO, Valor.SEIS, Valor.SIETE, Valor.OCHO, Valor.NUEVE,Valor.DIEZ, Valor.J, Valor.Q, Valor.K,};

        // Agregar una secuencia completa de cartas a la columna
        for (int i = Valor.values().length - 1; i >= 0; i--) {
            columna.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.values()[i]));


        }

        assertTrue(tablero.moverColumnaAFundacion(columna, fundacion));

    }

    @Test
    public void testMoverColumnaAFundacionSecuenciaIncompleta() {
        // Prueba para intentar mover una secuencia incompleta de cartas
        Columna columna = new Columna(new ArrayList<>(), new ArrayList<>());
        Fundacion fundacion = new Fundacion();

        // Agregar una secuencia incompleta de cartas a la columna
        columna.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.K));
        columna.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DIEZ));

        assertFalse(tablero.moverColumnaAFundacion(columna, fundacion));
        assertEquals(2, columna.getCartasReveladas().size());
        assertEquals(0, fundacion.getCartas().size());
    }

    @Test
    public void testSeCumpleSecuenciaCompleta() {
        // Prueba para verificar una secuencia completa en la columna
        Columna columna = new Columna(new ArrayList<>(), new ArrayList<>());
        List <Carta> cartas = new ArrayList<>();
        cartas = columna.getCartasReveladas();

        // Agregar una secuencia completa de cartas a la columna
        Valor[] secuenciaEsperada = {Valor.K, Valor.Q, Valor.J, Valor.DIEZ, Valor.NUEVE, Valor.OCHO, Valor.SIETE, Valor.SEIS, Valor.CINCO, Valor.CUATRO, Valor.TRES, Valor.DOS, Valor.AS};

        int indice = 0;

        cartas.add(new Carta(ColorCarta.NEGRO, Palo.PICAS,Valor.CINCO));
        cartas.add(new Carta(ColorCarta.NEGRO, Palo.PICAS,Valor.SEIS));
        for (Valor valor : secuenciaEsperada) {

            cartas.add(new Carta(ColorCarta.NEGRO, Palo.PICAS, valor));
        }



        assertTrue(tablero.seCumpleSecuenciaCompleta(columna));
    }

    @Test
    public void testNoSeCumpleSecuenciaCompleta() {
        // Prueba para verificar que una secuencia incompleta en la columna no se cumple
        Columna columna = new Columna(new ArrayList<>(), new ArrayList<>());

        // Agregar una secuencia incompleta de cartas a la columna
        columna.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.K));
        columna.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DIEZ));

        assertFalse(tablero.seCumpleSecuenciaCompleta(columna));
    }

    @Test
    public void testSecuenciaInvertidaNoSeCumple() {
        // Prueba para verificar que una secuencia invertida en la columna no se cumple
        Columna columna = new Columna(new ArrayList<>(), new ArrayList<>());

        // Agregar una secuencia invertida de cartas a la columna
        columna.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS));
        columna.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS));

        assertFalse(tablero.seCumpleSecuenciaCompleta(columna));
    }

    @Test
    public void testColumnaMenosDe13Cartas() {
        // Prueba para verificar que una columna con menos de 13 cartas no cumple la secuencia
        Columna columna = new Columna(new ArrayList<>(), new ArrayList<>());

        // Agregar solo 10 cartas a la columna
        for (Valor valor : Valor.values()) {
            if (valor.ordinal() < 10) {
                columna.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, valor));
            }
        }

        assertFalse(tablero.seCumpleSecuenciaCompleta(columna));
    }



    @Test
    public void testMoverColumnaAColumna() {
        // Prueba para mover cartas de una columna a otra
        Columna columnaOrigen = new Columna(new ArrayList<>(), new ArrayList<>());
        Columna columnaDestino = new Columna(new ArrayList<>(), new ArrayList<>());

        // Agregar cartas a la columna de origen
        columnaOrigen.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.J));
        columnaOrigen.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DIEZ));
        columnaOrigen.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.NUEVE));

        List<Carta> cartasAMover = new ArrayList<>();

        columnaDestino.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.K));
        columnaDestino.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.Q));

        cartasAMover.add(columnaOrigen.getCartasReveladas().get(0));
        cartasAMover.add(columnaOrigen.getCartasReveladas().get(1));
        cartasAMover.add(columnaOrigen.getCartasReveladas().get(2));



        assertTrue(tablero.moverColumnaAColumna(columnaOrigen, columnaDestino, cartasAMover));

    }

    @Test
    public void testMoverColumnaAColumnaInvalido() {
        // Prueba para mover cartas de una columna a otra de manera inválida
        Columna columnaOrigen = new Columna(new ArrayList<>(), new ArrayList<>());
        Columna columnaDestino = new Columna(new ArrayList<>(), new ArrayList<>());

        // Agregar cartas a la columna de origen
        columnaOrigen.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.J));
        columnaOrigen.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DIEZ));
        columnaOrigen.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.NUEVE));

        List<Carta> cartasAMover = new ArrayList<>();

        columnaDestino.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.SEIS));
        columnaDestino.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.CINCO));

        cartasAMover.add(columnaOrigen.getCartasReveladas().get(1));
        cartasAMover.add(columnaOrigen.getCartasReveladas().get(2));


        assertFalse(tablero.moverColumnaAColumna(columnaOrigen, columnaDestino, cartasAMover));
        assertEquals(3, columnaOrigen.getCartasReveladas().size());

    }











}
