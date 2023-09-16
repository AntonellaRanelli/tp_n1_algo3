import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.*;

public class CartaTest {
    @Test
    public void testEstadoCarta()
    {
        boolean estadoInicial = false;
        Carta nuevaCarta = new Carta(ColorCarta.ROJO, Palo.PICAS, Valor.UNO, estadoInicial);
        boolean resultado = nuevaCarta.cartaVolteada();

        assertEquals(resultado, estadoInicial);
    }

    @Test
    public void testVoltearCartaArriba()
    {
        boolean estadoInicial = false;
        boolean estadoModificado = true;
        Carta nuevaCarta = new Carta(ColorCarta.ROJO, Palo.PICAS, Valor.UNO, estadoInicial);
        boolean resultado = nuevaCarta.voltearCartaCara();

        assertEquals(resultado, estadoModificado);
    }

    @Test
    public void testVoltearCartaAbajo()
    {
        boolean estadoInicial = true;
        boolean estadoModificado = false;
        Carta nuevaCarta = new Carta(ColorCarta.ROJO, Palo.PICAS, Valor.UNO, estadoInicial);
        boolean resultado = nuevaCarta.voltearCartaReverso();

        assertEquals(resultado, estadoModificado);
    }
}