import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
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
        boolean esperado = true;

        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        listadoCarta.add(carta);
        columna.agregarCartas(listadoCarta);

        boolean resultado = tablero.hacerMovimientoCaF(columna, fundacion);

        assertEquals(esperado, resultado);
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
        boolean esperado = false;

        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DIEZ);
        listadoCarta.add(carta);
        columna.agregarCartas(listadoCarta);

        boolean resultado = tablero.hacerMovimientoCaF(columna, fundacion);

        assertEquals(esperado, resultado);
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
        tablero.hacerMovimientoCaF(columna, fundacion);

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

        boolean esperado = false;
        boolean resultado = tablero.hacerMovimientoCaF(columna, fundacion);

        assertEquals(esperado, resultado);
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

        tablero.hacerMovimientoCaF(columna, fundacion);

        boolean resultado = Reglas.verificarJuegoGanado(fundaciones);
        boolean esperado = false;

        assertEquals(esperado, resultado);
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

        tablero.hacerMovimientoCaF(columna, fundacion);

        boolean resultado = Reglas.verificarJuegoGanado(fundaciones);
        boolean esperado = true;

        assertEquals(esperado, resultado);
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

        boolean resultado = Reglas.verificarJuegoGanado(fundaciones);
        boolean esperado = false;

        assertEquals(esperado, resultado);
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

        tablero.hacerMovimientoCaF(columna, fundacion);
        boolean resultado = tablero.hacerMovimientoCaF(columna, fundacion);
        boolean esperado = true;
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

        tablero.hacerMovimientoCaF(columna, fundacion);
        boolean resultado = tablero.hacerMovimientoCaF(columna, fundacion);
        boolean esperado = true;
    }
}