import static org.junit.Assert.*;
import Base.*;
import Spider.ReglasSpider;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReglasSpiderTest {

    @Test
    public void testValidarMovimientoEntreColumnasPrimeraCartaEsAs() {
        ReglasSpider reglas = new ReglasSpider();
        List<Carta> cartasAMover = new ArrayList<>();
        cartasAMover.add(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS));
        boolean resultado = reglas.validarMovimientoEntreColumnas(cartasAMover, null);
        assertTrue(resultado);
    }

    @Test
    public void testValidarMovimientoEntreColumnasCartaDestinoEsMayorPorUno() {
        ReglasSpider reglas = new ReglasSpider();
        List<Carta> cartasAMover = new ArrayList<>();
        cartasAMover.add(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.CINCO));
        boolean resultado = reglas.validarMovimientoEntreColumnas(cartasAMover, new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.SEIS));
        assertTrue(resultado);
    }

    @Test
    public void testValidarMovimientoEntreColumnasCartaDestinoEsNull() {
        ReglasSpider reglas = new ReglasSpider();
        List<Carta> cartasAMover = new ArrayList<>();
        cartasAMover.add(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.SEIS));
        boolean resultado = reglas.validarMovimientoEntreColumnas(cartasAMover, null);
        assertTrue(resultado);
    }

    @Test
    public void testValidarMovimientoEntreColumnasInvalido() {
        ReglasSpider reglas = new ReglasSpider();
        List<Carta> cartasAMover = new ArrayList<>();
        cartasAMover.add(new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.OCHO));
        boolean resultado = reglas.validarMovimientoEntreColumnas(cartasAMover, new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.SEIS));
        assertFalse(resultado);
    }

    @Test
    public void testValidarMovimientoAFundacionValido() {
        ReglasSpider reglas = new ReglasSpider();
        Carta cartaColumna = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.OCHO);
        Carta cartaAIngresar = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.NUEVE);
        boolean resultado = reglas.validarMovimientoAFundacion(cartaColumna, cartaAIngresar);
        assertFalse(resultado);
    }

    @Test
    public void testValidarMovimientoAFundacionPrimerAs() {
        ReglasSpider reglas = new ReglasSpider();
        Carta cartaColumna = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        boolean resultado = reglas.validarMovimientoAFundacion(cartaColumna, null);
        assertTrue(resultado);
    }

    @Test
    public void testValidarMovimientoAFundacionInvalido() {
        ReglasSpider reglas = new ReglasSpider();
        Carta cartaColumna = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.DIEZ);
        Carta cartaAIngresar = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.OCHO);
        boolean resultado = reglas.validarMovimientoAFundacion(cartaColumna, cartaAIngresar);
        assertFalse(resultado);
    }

    @Test
    public void testVerificarJuegoGanadoTodasLasFundacionesLlenas() {
        ReglasSpider reglas = new ReglasSpider();
        List<Fundacion> fundaciones = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Fundacion fundacion = new Fundacion();
            for (Valor valor : Valor.values()) {
                fundacion.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, valor));
            }
            fundaciones.add(fundacion);
        }
        boolean resultado = reglas.verificarJuegoGanado(fundaciones);
        assertTrue(resultado);
    }

    @Test
    public void testVerificarJuegoGanadoNoTodasLasFundacionesLlenas() {
        ReglasSpider reglas = new ReglasSpider();
        List<Fundacion> fundaciones = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Fundacion fundacion = new Fundacion();
            for (Valor valor : Valor.values()) {
                if (valor.ordinal() < 12) {  // Dejar una carta fuera
                    fundacion.agregarCarta(new Carta(ColorCarta.NEGRO, Palo.PICAS, valor));
                }
            }
            fundaciones.add(fundacion);
        }
        boolean resultado = reglas.verificarJuegoGanado(fundaciones);
        assertFalse(resultado);
    }

    @Test
    public void testValidarExistenciaCarta() {
        ReglasSpider reglas = new ReglasSpider();
        Carta carta = new Carta(ColorCarta.NEGRO, Palo.PICAS, Valor.AS);
        boolean resultado = reglas.validarExistenciaCarta(carta);
        assertTrue(resultado);
    }

    @Test
    public void testValidarExistenciaCartaNula() {
        ReglasSpider reglas = new ReglasSpider();
        Carta carta = null;
        boolean resultado = reglas.validarExistenciaCarta(carta);
        assertFalse(resultado);
    }

}