import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FundacionTest {

    @Test
    public void paloCorrecto() {
        Carta cartaAuxiliar = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        Fundacion fundacion = new Fundacion();
        fundacion.agregarCarta(cartaAuxiliar);

        Palo esperado = Palo.PICAS;

        assertEquals(esperado, fundacion.getPalo());
    }

    @Test
    public void paloNoSeteado() {
        Carta cartaAuxiliar = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        Fundacion fundacion = new Fundacion();
        fundacion.agregarCarta(cartaAuxiliar);

        assertNull(fundacion.getPalo());
    }

    @Test
    public void testObtenerCarta(){
        Carta cartaAuxiliar = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        Carta cartaAuxiliarDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DIEZ);
        Carta cartaAuxiliarTres = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.Q);
        Fundacion fundacion = new Fundacion();

        fundacion.agregarCarta(cartaAuxiliar);
        fundacion.agregarCarta(cartaAuxiliarDos);
        fundacion.agregarCarta(cartaAuxiliarTres);

        assertTrue(cartaAuxiliarTres.equals(fundacion.obtenerUltimaCarta()));
    }

    @Test
    public void cartasVacio(){
        Fundacion fundacion = new Fundacion();

        assertTrue(fundacion.fundacionVacia());
    }
}