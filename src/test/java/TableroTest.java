import Base.*;
import Klondike.ReglasKlondike;
import Klondike.TableroKlondike;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TableroTest {
    @Test
    public void testMovimientoAfundacionValido() {
        //Verifico que si una fundacion esta vacia, puedo ingresar una AS.
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        List<Carta> listadoCarta = new ArrayList<>();
        Columna columnaAuxiliar = tablero.getColumnaPorIndice(0);
        Fundacion fundacionAuxiliar = tablero.getFundacionPorIndice(0);

        listadoCarta.add(carta);
        columnaAuxiliar.agregarCarta(listadoCarta);

        assertTrue(tablero.moverColumnaAFundacion(columnaAuxiliar, fundacionAuxiliar));
    }

    @Test
    public void testMovimientoAfundacionInvalido() {
        //Verifico que si una fundacion esta vacia, no puedo ingresar una carta distinta a un AS.
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DIEZ);
        List<Carta> listadoCarta = new ArrayList<>();
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);

        listadoCarta.add(carta);
        columna.agregarCarta(listadoCarta);

        assertFalse(tablero.moverColumnaAFundacion(columna, fundacion));
    }

    @Test
    public void testPaloMovimientoAfundacion() {
        //Verifico que despues de ingresar un AS en una fundacion vacia, el palo es el correcto.
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        List<Carta> listadoCarta = new ArrayList<>();
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        Palo esperado = Palo.PICAS;

        listadoCarta.add(carta);
        columna.agregarCarta(listadoCarta);
        tablero.moverColumnaAFundacion(columna, fundacion);

        assertEquals(esperado, fundacion.getPalo());
    }

    @Test
    public void testMovimientoAFundacionColumnaVacia() {
        //Intentar mover de una columna vacia devuelve error.
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        List<Carta> listadoCarta = new ArrayList<>();

        columna.agregarCarta(listadoCarta);

        assertFalse(tablero.moverColumnaAFundacion(columna, fundacion));
    }

    @Test
    public void testJuegoGanadoInvalido() {
        //Verifico que si no estan dadas las condiciones, juego ganado devuelve false.
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        List<Fundacion> fundaciones = new ArrayList<>();
        for(int i=0; i<4; i++){
            fundaciones.add(tablero.getFundacionPorIndice(i));
        }
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        List<Carta> listadoCarta = new ArrayList<>();
        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);

        listadoCarta.add(carta);
        columna.agregarCarta(listadoCarta);
        tablero.moverColumnaAFundacion(columna, fundacion);

        assertFalse(reglas.verificarJuegoGanado(fundaciones));
    }

    @Test
    public void testJuegoGanadoValido() {
        //Inicio el juego con todas las cartas en las fundaciones y verifico que esta ganado.
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        List<Fundacion> fundaciones = new ArrayList<>();
        for(int i=0; i<4; i++){
            fundaciones.add(tablero.getFundacionPorIndice(i));
        }

        int posicion = 0;
        Palo[] palos = Palo.values();
        Valor[] valores = Valor.values();

        for (Fundacion fundacion: fundaciones){
            for(Valor valor: valores)
                if (posicion < 2)
                    fundacion.agregarCarta(new Carta(ColorCarta.ROJO, palos[posicion], valor));
                else
                    fundacion.agregarCarta(new Carta(ColorCarta.NEGRO, palos[posicion], valor));
            posicion++;
        }

        assertTrue(reglas.verificarJuegoGanado(fundaciones));
    }

    @Test
    public void testJuegoGanadoInValidoConUnaCarta() {
        //Inicio el juego con todas las cartas en las fundaciones excepto una.
        //Verifico que el juego aun no esta ganado.
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        List<Fundacion> fundaciones = new ArrayList<>();
        for(int i=0; i<4; i++){
            fundaciones.add(tablero.getFundacionPorIndice(i));
        }

        int posicion = 0;
        Palo[] palos = Palo.values();
        Valor[] valores = Valor.values();

        for (Fundacion fundacion: fundaciones){
            for(Valor valor: valores)
                if (posicion < 2)
                    fundacion.agregarCarta(new Carta(ColorCarta.ROJO, palos[posicion], valor));
                else
                    fundacion.agregarCarta(new Carta(ColorCarta.NEGRO, palos[posicion], valor));
            posicion++;
        }

        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        fundacion.eliminarUltimaCarta();

        assertFalse(reglas.verificarJuegoGanado(fundaciones));
    }

    @Test
    public void testCartaValidaAFundacion(){
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        List<Carta> listadoCarta = new ArrayList<>();
        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        Carta cartaAuxiliar = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        listadoCarta.add(cartaAuxiliar);
        listadoCarta.add(carta);

        columna.agregarCarta(listadoCarta);
        tablero.moverColumnaAFundacion(columna, fundacion);

        assertTrue(tablero.moverColumnaAFundacion(columna, fundacion));
    }

    @Test
    public void testCartaInValidaAFundacion(){
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        List<Carta> listadoCarta = new ArrayList<>();
        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        Carta cartaAuxiliar = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DIEZ);
        listadoCarta.add(cartaAuxiliar);
        listadoCarta.add(carta);

        columna.agregarCarta(listadoCarta);
        tablero.moverColumnaAFundacion(columna, fundacion);

        assertFalse(tablero.moverColumnaAFundacion(columna, fundacion));
    }

    @Test
    public void testFundacionAColumnaInvalido(){
        ReglasKlondike reglas = new ReglasKlondike();
        TableroKlondike tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);

        fundacion.agregarCarta(carta);

        assertFalse(tablero.moverFundacionAColumna(fundacion, columna));

    }

    @Test
    public void testFundacionAColumnaValido(){
        ReglasKlondike reglas = new ReglasKlondike();
        TableroKlondike tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        Carta cartaTres = new Carta(ColorCarta.ROJO, Palo.DIAMANTE, Valor.TRES);

        fundacion.agregarCarta(carta);
        fundacion.agregarCarta(cartaDos);
        columna.agregarCarta(cartaTres);

        assertTrue(tablero.moverFundacionAColumna(fundacion, columna));
    }

    @Test
    public void testColumnaAColumnaValido(){
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Columna columna = tablero.getColumnaPorIndice(0);
        Columna columna1 = tablero.getColumnaPorIndice(1);
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        Carta cartaTres = new Carta(ColorCarta.ROJO, Palo.DIAMANTE, Valor.TRES);
        List<Carta> cartasAMover = new ArrayList<>();

        columna1.agregarCarta(cartaDos);
        cartasAMover.add(cartaDos);
        columna.agregarCarta(cartaTres);

        assertTrue(tablero.moverColumnaAColumna(columna1, columna, cartasAMover));
    }

    @Test
    public void testColumnaAColumnaVaciaValido(){
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Columna columna = tablero.getColumnaPorIndice(0);
        Columna columna1 = tablero.getColumnaPorIndice(1);
        Carta cartaK = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.K);
        List<Carta> cartasAMover = new ArrayList<>();

        columna1.agregarCarta(cartaK);
        cartasAMover.add(cartaK);

        assertTrue(tablero.moverColumnaAColumna(columna1, columna, cartasAMover));
    }

    @Test
    public void testColumnaAColumnaInvalido(){
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Columna columna = tablero.getColumnaPorIndice(0);
        Columna columna1 = tablero.getColumnaPorIndice(1);
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        Carta cartaTres = new Carta(ColorCarta.ROJO, Palo.DIAMANTE, Valor.TRES);
        List<Carta> cartasAMover = new ArrayList<>();

        columna1.agregarCarta(cartaDos);
        cartasAMover.add(cartaTres);
        columna.agregarCarta(cartaTres);

        assertFalse(tablero.moverColumnaAColumna(columna, columna1, cartasAMover));
    }

    @Test
    public void testColumnaAColumnaVaciaInvalido(){
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Columna columna = tablero.getColumnaPorIndice(0);
        Columna columna1 = tablero.getColumnaPorIndice(1);
        Carta cartaTres = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.TRES);
        List<Carta> cartasAMover = new ArrayList<>();

        columna1.agregarCarta(cartaTres);
        cartasAMover.add(cartaTres);

        assertFalse(tablero.moverColumnaAColumna(columna1, columna, cartasAMover));
    }

    @Test
    public void testMazoaFundacionValido(){
        ReglasKlondike reglas = new ReglasKlondike();
        TableroKlondike tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        List<Fundacion> fundaciones = new ArrayList<>();
        for(int i=0; i<4; i++){
            fundaciones.add(tablero.getFundacionPorIndice(i));
        }
        Mazo mazo = tablero.getMazo();
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        Carta cartaTres = new Carta(ColorCarta.ROJO, Palo.DIAMANTE, Valor.TRES);
        List<Carta> cartasMazo = new ArrayList<>();
        cartasMazo.add(cartaTres);
        cartasMazo.add(cartaDos);

        mazo.setCartasOcultas(cartasMazo);
        mazo.revelarCarta();
        fundacion.agregarCarta(carta);

        assertTrue(tablero.moverMazoAFundacion(mazo, fundacion));
    }

    @Test
    public void testMazoaFundacionInvalido(){
        ReglasKlondike reglas = new ReglasKlondike();
        TableroKlondike tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        List<Fundacion> fundaciones = new ArrayList<>();
        for(int i=0; i<4; i++){
            fundaciones.add(tablero.getFundacionPorIndice(i));
        }
        Mazo mazo = tablero.getMazo();
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        Carta cartaTres = new Carta(ColorCarta.ROJO, Palo.DIAMANTE, Valor.TRES);
        List<Carta> cartasMazo = new ArrayList<>();
        cartasMazo.add(cartaDos);
        cartasMazo.add(cartaTres);

        mazo.setCartasOcultas(cartasMazo);
        mazo.revelarCarta();
        fundacion.agregarCarta(carta);

        assertFalse(tablero.moverMazoAFundacion(mazo, fundacion));
    }

    @Test
    public void testMazoaColumnaValido(){
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Mazo mazo = tablero.getMazo();
        Columna columna = tablero.getColumnaPorIndice(0);
        Carta cartaColumna = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.CUATRO);
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        Carta cartaTres = new Carta(ColorCarta.ROJO, Palo.DIAMANTE, Valor.TRES);
        List<Carta> cartasMazo = new ArrayList<>();
        cartasMazo.add(cartaDos);
        cartasMazo.add(cartaTres);

        mazo.setCartasOcultas(cartasMazo);
        mazo.revelarCarta();
        columna.agregarCarta(cartaColumna);

        assertTrue(tablero.moverMazoAColumna(mazo, columna));
    }

    @Test
    public void testMazoaColumnaInvalido(){
        ReglasKlondike reglas = new ReglasKlondike();
        Tablero tablero = TableroKlondike.crearJuegoVacioParaTest(reglas);
        Mazo mazo = tablero.getMazo();
        Columna columna = tablero.getColumnaPorIndice(0);
        Carta cartaColumna = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.CUATRO);
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        Carta cartaTres = new Carta(ColorCarta.ROJO, Palo.DIAMANTE, Valor.TRES);
        List<Carta> cartasMazo = new ArrayList<>();
        cartasMazo.add(cartaTres);
        cartasMazo.add(cartaDos);

        mazo.setCartasOcultas(cartasMazo);
        mazo.revelarCarta();
        columna.agregarCarta(cartaColumna);

        assertFalse(tablero.moverMazoAColumna(mazo, columna));
    }
}