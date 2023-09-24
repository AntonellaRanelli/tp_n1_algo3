import org.junit.Test;

import static org.junit.Assert.*;

public class CartaTest {
    @Test
    public void testColorCarta()
    {
        ColorCarta estadoInicial = ColorCarta.ROJO;
        Carta nuevaCarta = new Carta(ColorCarta.ROJO, Palo.PICAS, Valor.AS);
        ColorCarta resultado = nuevaCarta.getColor();

        assertEquals(resultado, estadoInicial);
    }

    @Test
    public void testValorCarta()
    {
        Valor estadoInicial = Valor.AS;
        Carta nuevaCarta = new Carta(ColorCarta.ROJO, Palo.PICAS, Valor.AS);
        Valor resultado = nuevaCarta.getNumero();

        assertEquals(resultado, estadoInicial);
    }

    @Test
    public void testPaloCarta()
    {
        Palo estadoInicial = Palo.PICAS;
        Carta nuevaCarta = new Carta(ColorCarta.ROJO, Palo.PICAS, Valor.AS);
        Palo resultado = nuevaCarta.getPalo();

        assertEquals(resultado, estadoInicial);
    }
}