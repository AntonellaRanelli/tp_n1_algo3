import org.junit.Test;

import static org.junit.Assert.*;

public class CartaTest {
    @Test
    public void testEstadoCarta()
    {
        boolean estadoInicial = false;
        Carta nuevaCarta = new Carta("rojo", "picas", "1", estadoInicial);
        boolean resultado = nuevaCarta.cartaVolteada();

        assertEquals(resultado, estadoInicial);
    }

    @Test
    public void testVoltearCartaArriba()
    {
        boolean estadoInicial = false;
        boolean estadoModificado = true;
        Carta nuevaCarta = new Carta("rojo", "picas", "1", estadoInicial);
        boolean resultado = nuevaCarta.voltearCartaCara();

        assertEquals(resultado, estadoModificado);
    }

    @Test
    public void testVoltearCartaAbajo()
    {
        boolean estadoInicial = true;
        boolean estadoModificado = false;
        Carta nuevaCarta = new Carta("rojo", "picas", "1", estadoInicial);
        boolean resultado = nuevaCarta.voltearCartaReverso();

        assertEquals(resultado, estadoModificado);
    }
}