import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ReglasTest {
    @Test
    public void testMovimientoValido() {
        Maso maso = new Maso();
        Carta cartaUno = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.CINCO, true);
        Carta cartaDos = new Carta(ColorCarta.ROJO, Palo.CORAZON, Valor.CUATRO, true);
        boolean esperado = true;

        maso.apilarCarta(cartaUno);

        assertEquals(esperado, Reglas.movimientoValido(maso, cartaDos));
        assertEquals(2, maso.obtenerCantidadActual());
    }

    @Test
    public void testColorInvalido() {
        Maso maso = new Maso();
        Carta cartaUno = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.CINCO, true);
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.CUATRO, true);
        boolean esperado = false;

        maso.apilarCarta(cartaUno);

        assertEquals(esperado, Reglas.movimientoValido(maso, cartaDos));
        assertEquals(maso.obtenerCantidadActual(), 1);
    }

    @Test
    public void testValorInvalidoMayor() {
        Maso maso = new Maso();
        Carta cartaUno = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.CINCO, true);
        Carta cartaDos = new Carta(ColorCarta.ROJO, Palo.CORAZON, Valor.DIEZ, true);

        boolean esperado = false;

        maso.apilarCarta(cartaUno);

        assertEquals(esperado, Reglas.movimientoValido(maso, cartaDos));
    }

    @Test
    public void testValorInvalidoMenor() {
        Maso maso = new Maso();
        Carta cartaUno = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.CINCO, true);
        Carta cartaTres = new Carta(ColorCarta.ROJO, Palo.CORAZON, Valor.DOS, true);
        boolean esperado = false;

        maso.apilarCarta(cartaUno);

        assertEquals(esperado, Reglas.movimientoValido(maso, cartaTres));
    }

    @Test
    public void testMovimientoFoundation(){
        Maso masoFoundation = new Maso();
        Carta cartaUno = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS, true);
        boolean esperado = true;

        assertEquals(esperado, Reglas.movimientoValidoFoundation(masoFoundation, cartaUno));
    }

    @Test
    public void testMovimientoFoundationApilarValidos(){
        Maso masoFoundation = new Maso();
        Carta cartaUno = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS, true);
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.UNO, true);
        boolean esperado = true;

        masoFoundation.apilarCarta(cartaUno);

        assertEquals(esperado, Reglas.movimientoValidoFoundation(masoFoundation, cartaDos));
    }

    @Test
    public void testMasoVacioCartaMayorAs(){
        Maso masoFoundation = new Maso();
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.UNO, true);
        boolean esperado = false;

        assertEquals(esperado, Reglas.movimientoValidoFoundation(masoFoundation, cartaDos));
    }

    @Test
    public void testMovimientoFoundationNumeroInvalido(){
        Maso masoFoundation = new Maso();
        Carta cartaUno = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.CINCO, true);
        boolean esperado = false;

        assertEquals(esperado, Reglas.movimientoValidoFoundation(masoFoundation, cartaUno));
    }

    @Test
    public void testMovimientoFoundationColorInvalido(){
        Maso masoFoundation = new Maso();
        Carta cartaUno = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS, true);
        Carta cartaDos = new Carta(ColorCarta.ROJO, Palo.CORAZON, Valor.UNO, true);
        boolean esperado = false;

        masoFoundation.apilarCarta(cartaUno);

        assertEquals(esperado, Reglas.movimientoValidoFoundation(masoFoundation, cartaDos));
    }

    @Test
    public void testMovimientoFoundationPaloInvalido(){
        Maso masoFoundation = new Maso();
        Carta cartaUno = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS, true);
        Carta cartaDos = new Carta(ColorCarta.NEGRO, Palo.TREBOL, Valor.UNO, true);
        boolean esperado = false;

        masoFoundation.apilarCarta(cartaUno);

        assertEquals(esperado, Reglas.movimientoValidoFoundation(masoFoundation, cartaDos));
    }

    @Test
    public void testIniciarJuego(){
        List<Carta> cartas = Reglas.iniciarJuego();
        System.out.println(cartas);
    }
}