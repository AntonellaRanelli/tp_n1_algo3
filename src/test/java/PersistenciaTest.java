import Klondike.ReglasKlondike;
import Klondike.TableroKlondike;
import Spider.TableroSpider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Base.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.*;

public class PersistenciaTest {

    public Tablero juegoPruebas(Tablero tablero){
        Carta cartaAs = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        Carta CartaDiez = new Carta(ColorCarta.ROJO, Palo.CORAZON, Valor.DIEZ);

        Columna columnaUno = tablero.getColumnaPorIndice(1);
        Fundacion fundacion = tablero.getFundacionPorIndice(0);

        columnaUno.agregarCarta(CartaDiez);
        fundacion.agregarCarta(cartaAs);

        return tablero;
    }

    public void comprobarClase(Tablero tablero, Class<?> clase){
        try{
            tablero.serializar(new FileOutputStream("datos.java"));
        }catch (IOException ex){
            ex.printStackTrace();
        }

        try{
            Tablero tableroPrueba = Tablero.deserializar(new FileInputStream("datos.java"));
            assertTrue(clase.isInstance(tableroPrueba));
        }catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public void comprobarEstado(Tablero tablero){

        try{
            tablero.serializar(new FileOutputStream("datos.java"));
        }catch (IOException ex){
            ex.printStackTrace();
        }

        try{
            Tablero tableroPrueba = Tablero.deserializar(new FileInputStream("datos.java"));
            Columna columnaUno = tableroPrueba.getColumnaPorIndice(1);
            Fundacion fundacion = tableroPrueba.getFundacionPorIndice(0);

            Carta cartaAEvaluar = columnaUno.obtenerUltimaCartaRevelada();
            Carta cartaAEvaluarDos = fundacion.obtenerUltimaCarta();

            assertTrue((cartaAEvaluarDos.getPalo() == Palo.PICAS)
                    && (cartaAEvaluarDos.getNumero() == Valor.AS));
            assertTrue((cartaAEvaluar.getPalo() == Palo.CORAZON)
                    && (cartaAEvaluar.getNumero() == Valor.DIEZ));

        }catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void comprobarClaseJuegoGuardadoKlondike(){
        ReglasKlondike reglas = new ReglasKlondike();
        TableroKlondike tablero = (TableroKlondike) juegoPruebas(TableroKlondike.crearJuegoVacioParaTest(reglas));
        comprobarClase(tablero, TableroKlondike.class);
    }

    @Test
    public void comprobarClaseJuegoGuardadoSpider(){
        TableroSpider tablero = (TableroSpider) juegoPruebas(TableroSpider.crearJuegoVacioParaTest());
        comprobarClase(tablero, TableroSpider.class);
    }

    @Test
    public void comprobarEstadoJuegoGuardadoKlondike(){
        ReglasKlondike reglas = new ReglasKlondike();
        TableroKlondike tablero = (TableroKlondike) juegoPruebas(TableroKlondike.crearJuegoVacioParaTest(reglas));
        comprobarEstado(tablero);
    }

    @Test
    public void comprobarEstadoJuegoGuardadoSpider(){
        TableroSpider tablero = (TableroSpider) juegoPruebas(TableroSpider.crearJuegoVacioParaTest());
        comprobarEstado(tablero);
    }
}
