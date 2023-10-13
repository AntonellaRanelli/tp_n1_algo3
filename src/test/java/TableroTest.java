import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TableroTest {
    @Test
    public void testMovimientoAfundacionValido() {
        //Verifico que si una fundacion esta vacia, puedo ingresar una AS.

        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        List<Carta> listadoCarta = new ArrayList<>();

        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        listadoCarta.add(carta);
        columna.agregarCartas(listadoCarta);


        assertTrue(tablero.moverColumnaAFundacion(columna, fundacion));
    }

    @Test
    public void testMovimientoAfundacionInvalido() {
        //Verifico que si una fundacion esta vacia, no puedo ingresar una carta distinta a un AS.

        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        List<Carta> listadoCarta = new ArrayList<>();

        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DIEZ);
        listadoCarta.add(carta);
        columna.agregarCartas(listadoCarta);


        assertFalse(tablero.moverColumnaAFundacion(columna, fundacion));
    }

    @Test
    public void testPaloMovimientoAfundacion() {
        //Verifico que despues de ingresar un AS en una fundacion vacia, el palo es el correcto.

        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        List<Carta> listadoCarta = new ArrayList<>();

        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        listadoCarta.add(carta);
        columna.agregarCartas(listadoCarta);
        tablero.moverColumnaAFundacion(columna, fundacion);

        Palo esperado = Palo.PICAS;
        Palo resultado = fundacion.getPalo();

        assertEquals(esperado, resultado);
    }

    @Test
    public void testMovimientoAFundacionColumnaVacia() {
        //Intentar mover de una columna vacia devuelve error.

        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        List<Carta> listadoCarta = new ArrayList<>();
        columna.agregarCartas(listadoCarta);


        assertFalse(tablero.moverColumnaAFundacion(columna, fundacion));
    }

    @Test
    public void testJuegoGanadoInvalido() {
        //Verifico que si no estan dadas las condiciones, juego ganado devuelve false.

        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        List<Carta> listadoCarta = new ArrayList<>();

        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        listadoCarta.add(carta);
        columna.agregarCartas(listadoCarta);

        tablero.moverColumnaAFundacion(columna, fundacion);


        assertFalse(Reglas.verificarJuegoGanado(fundaciones));
    }

    @Test
    public void testJuegoGanadoValido() {
        //Inicio el juego con todas las cartas en las fundaciones y verifico que esta ganado.

        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();
        Tablero tablero = new Tablero(columnas, fundaciones, mazo);

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
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


        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        List<Carta> listadoCarta = new ArrayList<>();

        listadoCarta.add(fundacion.obtenerUltimaCarta());
        fundacion.eliminarUltimaCarta();
        columna.agregarCartas(listadoCarta);

        tablero.moverColumnaAFundacion(columna, fundacion);


        assertTrue(Reglas.verificarJuegoGanado(fundaciones));
    }

    @Test
    public void testJuegoGanadoInValidoConUnaCarta() {
        //Inicio el juego con todas las cartas en las fundaciones excepto una.
        //Verifico que el juego aun no esta ganado.

        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();
        Tablero tablero = new Tablero(columnas, fundaciones, mazo);

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
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


        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        List<Carta> listadoCarta = new ArrayList<>();

        listadoCarta.add(fundacion.obtenerUltimaCarta());
        fundacion.eliminarUltimaCarta();
        columna.agregarCartas(listadoCarta);



        assertFalse(Reglas.verificarJuegoGanado(fundaciones));
    }

    @Test
    public void testCartaValidaAFundacion(){
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        List<Carta> listadoCarta = new ArrayList<>();

        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        Carta cartaAuxiliar = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        listadoCarta.add(cartaAuxiliar);
        listadoCarta.add(carta);
        columna.agregarCartas(listadoCarta);

        tablero.moverColumnaAFundacion(columna, fundacion);

        assertTrue(tablero.moverColumnaAFundacion(columna, fundacion));
    }

    @Test
    public void testCartaInValidaAFundacion(){
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);
        List<Carta> listadoCarta = new ArrayList<>();

        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        Carta cartaAuxiliar = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DIEZ);
        listadoCarta.add(cartaAuxiliar);
        listadoCarta.add(carta);
        columna.agregarCartas(listadoCarta);

        tablero.moverColumnaAFundacion(columna, fundacion);

        assertFalse(tablero.moverColumnaAFundacion(columna, fundacion));
    }

    @Test
    public void testFundacionAColumnaInvalido(){
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);

        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        fundacion.agregarCarta(carta);

        assertFalse(tablero.moverFundacionAColumna(fundacion, columna));
    }

    @Test
    public void testFundacionAColumnaValido(){
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);

        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        Carta cartaTres = new Carta(ColorCarta.ROJO, Palo.DIAMANTE, Valor.TRES);

        fundacion.agregarCarta(carta);
        fundacion.agregarCarta(cartaDos);
        columna.agregarCartas(cartaTres);


        assertTrue(tablero.moverFundacionAColumna(fundacion, columna));
    }

    @Test
    public void testColumnaAColumnaValido(){
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);
        Columna columna1 = tablero.getColumnaPorIndice(1);


        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        Carta cartaTres = new Carta(ColorCarta.ROJO, Palo.DIAMANTE, Valor.TRES);

        columna1.agregarCartas(cartaDos);
        List<Carta> cartasAMover = new ArrayList<>();
        cartasAMover.add(cartaDos);
        columna.agregarCartas(cartaTres);

        assertTrue(tablero.moverColumnaAColumna(columna1, columna, cartasAMover));
    }

    @Test
    public void testColumnaAColumnaVaciaValido(){
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);
        Columna columna1 = tablero.getColumnaPorIndice(1);


        Carta cartaK = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.K);

        columna1.agregarCartas(cartaK);
        List<Carta> cartasAMover = new ArrayList<>();
        cartasAMover.add(cartaK);

        assertTrue(tablero.moverColumnaAColumna(columna1, columna, cartasAMover));
    }

    @Test
    public void testColumnaAColumnaInvalido(){
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);
        Columna columna1 = tablero.getColumnaPorIndice(1);


        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        Carta cartaTres = new Carta(ColorCarta.ROJO, Palo.DIAMANTE, Valor.TRES);

        columna1.agregarCartas(cartaDos);
        List<Carta> cartasAMover = new ArrayList<>();
        cartasAMover.add(cartaTres);
        columna.agregarCartas(cartaTres);

        assertFalse(tablero.moverColumnaAColumna(columna, columna1, cartasAMover));
    }

    @Test
    public void testColumnaAColumnaVaciaInvalido(){
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);
        Columna columna1 = tablero.getColumnaPorIndice(1);


        Carta cartaTres = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.TRES);

        columna1.agregarCartas(cartaTres);
        List<Carta> cartasAMover = new ArrayList<>();
        cartasAMover.add(cartaTres);

        assertFalse(tablero.moverColumnaAColumna(columna1, columna, cartasAMover));
    }

    @Test
    public void testMazoaFundacionValido(){
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
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
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
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
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);

        Carta cartaColumna = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.CUATRO);
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        Carta cartaTres = new Carta(ColorCarta.ROJO, Palo.DIAMANTE, Valor.TRES);

        List<Carta> cartasMazo = new ArrayList<>();
        cartasMazo.add(cartaDos);
        cartasMazo.add(cartaTres);
        mazo.setCartasOcultas(cartasMazo);
        mazo.revelarCarta();
        columna.agregarCartas(cartaColumna);

        assertTrue(tablero.moverMazoAColumna(mazo, columna));
    }

    @Test
    public void testMazoaColumnaInvalido(){
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());
        List<Columna> columnas = new ArrayList<>();
        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            columnas.add(i, new Columna(new ArrayList<>(), new ArrayList<>()));
        }

        for(int i=0; i<4; i++){
            fundaciones.add(new Fundacion());
        }

        Tablero tablero = new Tablero(columnas, fundaciones, mazo);
        Columna columna = tablero.getColumnaPorIndice(0);

        Carta cartaColumna = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.CUATRO);
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        Carta cartaTres = new Carta(ColorCarta.ROJO, Palo.DIAMANTE, Valor.TRES);

        List<Carta> cartasMazo = new ArrayList<>();
        cartasMazo.add(cartaTres);
        cartasMazo.add(cartaDos);
        mazo.setCartasOcultas(cartasMazo);
        mazo.revelarCarta();
        columna.agregarCartas(cartaColumna);

        assertFalse(tablero.moverMazoAColumna(mazo, columna));
    }
}