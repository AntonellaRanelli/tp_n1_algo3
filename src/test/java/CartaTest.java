import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.*;

public class CartaTest {
    @Test
    public void testCrearCarta()
    {
        String resultado = "Palo: PICAS, Valor: UNO";
        Carta nuevaCarta = new Carta(Palo.PICAS, Valor.AS);
        String cartaCreada = nuevaCarta.toString();

        assertEquals(resultado, cartaCreada);
    }

}