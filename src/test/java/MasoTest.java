import org.junit.Test;

import static org.junit.Assert.*;

public class MasoTest {
    @Test
    public void testMasoVacio()
    {
        Maso nuevoMaso = new Maso();

        assertTrue(nuevoMaso.masoVacio());
    }

    @Test
    public void testIngresarCarta()
    {
        Maso nuevoMaso = new Maso();
        Carta nuevaCarta = new Carta(ColorCarta.ROJO, Palo.PICAS, Valor.UNO, false);

        nuevoMaso.apilarCarta(nuevaCarta);

        assertEquals(nuevoMaso.obtenerCantidadActual(), 1);
    }

    @Test
    public void testDesapilar()
    {
        Maso nuevoMaso = new Maso();
        Carta nuevaCarta = new Carta(ColorCarta.ROJO, Palo.PICAS, Valor.UNO, false);
        Carta cartaAuxiliar;

        nuevoMaso.apilarCarta(nuevaCarta);
        cartaAuxiliar = nuevoMaso.desapilarCarta();

        boolean resultado = cartaAuxiliar instanceof Carta;

        assertTrue(resultado);
        assertTrue(nuevoMaso.masoVacio());
    }
}