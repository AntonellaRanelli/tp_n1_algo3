import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ColumnaTest {

    @Test
    public void crearColumna() {
        Columna columna = new Columna(new ArrayList<Carta>(), new ArrayList<Carta>());
        assertTrue(columna.getCartasOcultas().isEmpty());
        assertTrue(columna.getCartasReveladas().isEmpty());
    }

    @Test
    public void sacarCartas() {
        Columna columna = new Columna(new ArrayList<Carta>(), new ArrayList<Carta>());
    }

}