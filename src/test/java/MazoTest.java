import static org.junit.Assert.*;

import Base.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MazoTest {
    private Mazo mazo;

    @Before
    public void setUp() {
        // Configuración común para las pruebas
        List<Carta> cartasReveladas = new ArrayList<>();
        List<Carta> cartasOcultas = new ArrayList<>();
        mazo = new Mazo(cartasReveladas, cartasOcultas);
    }


    @Test
    public void testRevelarCartaSinCartasOcultas() {
        // Prueba que revelar una carta cuando no hay cartas ocultas devuelve null
        assertNull(mazo.revelarCarta());
    }

    @Test
    public void testEntregarCarta() {
        // Prueba que entrega una carta correctamente
        List<Carta> cartasReveladas = mazo.getCartasReveladas();
        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS);
        cartasReveladas.add(carta);

        Carta cartaEntregada = mazo.entregarCarta();

        assertEquals(carta, cartaEntregada);
        assertNull(mazo.obtenerUltimaCartaRevelada()); // Comprobar que la carta se ha eliminado de reveladas
    }

    @Test
    public void testEntregarCartaSinCartasReveladas() {
        // Prueba que entregar una carta cuando no hay cartas reveladas devuelve null
        assertNull(mazo.entregarCarta());
    }

    @Test
    public void testResetearMazo() {
        // Prueba que resetea el mazo correctamente
        List<Carta> cartasReveladas = mazo.getCartasReveladas();
        cartasReveladas.add(new Carta(ColorCarta.ROJO, Palo.CORAZON, Valor.AS));
        cartasReveladas.add(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DOS));
        List<Carta> cartasOcultas = mazo.getCartasOcultas();

        mazo.resetearMazo();

        assertTrue(cartasReveladas.isEmpty());
        assertFalse(cartasOcultas.isEmpty());

    }


}