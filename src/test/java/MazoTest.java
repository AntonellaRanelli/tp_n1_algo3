import org.junit.Test;

import static org.junit.Assert.*;

public class MazoTest {
    @Test
    public void testMazoVacio()
    {
        Mazo nuevoMazo = new Mazo();

        assertEquals(0, nuevoMazo.obtenerCantidadActual());
    }

    @Test
    public void testMazoCreado()
    {
        Mazo nuevoMazo = new Mazo();
        nuevoMazo.crearMazo();


        assertEquals(52, nuevoMazo.obtenerCantidadActual());
    }

}