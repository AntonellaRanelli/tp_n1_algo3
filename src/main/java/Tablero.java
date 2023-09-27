import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tablero {
    private List<Carta> baraja;
    private List<Columna> columnas;
    private List<Fundacion> fundaciones;
    private Mazo mazo;

    public Tablero() { //Constructor
        baraja = new ArrayList<>();
        columnas = new ArrayList<>();
        fundaciones = new ArrayList<>();
        mazo = new Mazo(new ArrayList<>(), new ArrayList<>());

        iniciarJuego();
    }

    public Tablero(List<Columna> columnas, List<Fundacion> fundaciones, Mazo mazo)
    {
        this.columnas = columnas;
        this.fundaciones = fundaciones;
        this.mazo = mazo;
    }

    public void iniciarJuego() { // iniciador de juego con semilla aleatoria; semilla tipo long
        Random random = new Random();
        baraja = crearCartas();
        crearColumnas();
        crearFundaciones();
        Collections.shuffle(baraja, random);
        repartirCartas();
    }

    private List<Carta> crearCartas()
    {
        List<Carta> cartas = new ArrayList<>();

        for (Palo palo: Palo.values())
        {
            for(Valor valor: Valor.values())
            {
                if (palo.ordinal() < 2)
                {
                    cartas.add(new Carta(ColorCarta.ROJO, palo, valor));
                }else {
                    cartas.add(new Carta(ColorCarta.NEGRO, palo, valor));
                }
            }
        }
        return cartas;
    }

    private void crearColumnas()
    {
        for (int i = 0; i < 7; i++) {
            Columna columna = new Columna(new ArrayList<>(), new ArrayList<>());
            columnas.add(columna);
        }
    }

    private void crearFundaciones()
    {
        for (int i = 0; i < 4; i++) {
            Fundacion fundacion = new Fundacion();
            fundaciones.add(fundacion);
        }
    }

    private void repartirCartas() { //reparte las carta entre las columnas el resto va al mazo
        int cartasPorColumna = 1;
        List<Carta> listaAuxiliar = new ArrayList<>();

        // Reiniciar el mazo
        mazo.resetearMazo();

        for (Columna columna : columnas) {
            // Asignar una carta revelada
            if (!baraja.isEmpty()) {
                Carta cartaRevelada = baraja.remove(0);
                listaAuxiliar.add(cartaRevelada);
                columna.agregarCartas(listaAuxiliar);

                listaAuxiliar.clear();
            }

            // Asignar las cartas ocultas
            for (int i = 0; i < cartasPorColumna - 1; i++) {
                if (!baraja.isEmpty()) {
                    Carta cartaOculta = baraja.remove(0);
                    columna.setCartasOcultas(cartaOculta);
                }
            }
            cartasPorColumna++;
        }

        // Agregar las cartas restantes al mazo
        mazo.setCartasOcultas(new ArrayList<>(baraja));
    }

    public List<Columna> getColumnas() {
        return columnas;
    }

    public Columna getColumnaPorIndice(int indice){
        return columnas.get(indice);
    }

    public List<Fundacion> getFundaciones() {
        return fundaciones;
    }

    public Fundacion getFundacionPorIndice(int indice){
        return fundaciones.get(indice);
    }

    public Mazo getMazo() {
        return mazo;
    }

    public boolean hacerMovimientoCaF(Columna columna, Fundacion fundacion)
    {
        Carta cartaAuxiliar = columna.obtenerUltimaCartaRevelada();

        if (cartaAuxiliar == null)
            return false;

        List<Carta> arregloAuxiliar = new ArrayList<>();
        arregloAuxiliar.add(cartaAuxiliar);

        if (Reglas.validarMovimientoAFundacion(cartaAuxiliar, fundacion.obtenerUltimaCarta()))
        {
            fundacion.agregarCarta(cartaAuxiliar);
            columna.sacarCartas(arregloAuxiliar);
            return true;
        }

        return false;
    }

    public boolean hacerMovimientoMaF(Mazo mazo, Fundacion fundacion){
        Carta cartaAuxiliar = mazo.obtenerUltimaCartaRevelada();

        if (cartaAuxiliar == null)
            return false;

        if (Reglas.validarMovimientoAFundacion(cartaAuxiliar, fundacion.obtenerUltimaCarta()))
        {
            fundacion.agregarCarta(cartaAuxiliar);
            mazo.entregarCarta();
            return true;
        }
        return false;
    }


    public  boolean hacerMovimientoFaC (Columna columna, Fundacion fundacion)
    {
        Carta ultimaCartaFundacion = fundacion.obtenerUltimaCarta();
        Carta ultimaCartaColumna = columna.obtenerUltimaCartaRevelada();

        if (Reglas.validarMovimientoAColumna(ultimaCartaColumna, ultimaCartaFundacion)){
            fundacion.eliminarUltimaCarta();
            columna.agregarCarta(ultimaCartaFundacion);

            return true;
        }
        return false;
    }

    public boolean hacerMovimientoMaC(Columna columna, Mazo mazo){
        Carta ultimaCartaMazo = mazo.obtenerUltimaCartaRevelada();
        Carta ultimaCartaColumna = columna.obtenerUltimaCartaRevelada();


        if (Reglas.validarMovimientoAColumna(ultimaCartaColumna, ultimaCartaMazo)){
            columna.agregarCarta(ultimaCartaMazo);
            mazo.entregarCarta();
            return true;
        }
        return false;
    }

    public boolean hacerMovimientoCaC(Columna columnaOrigen, Columna columnaDestino, List<Carta> cartasAMover )
    {
        Carta ultimaCartaRCD = columnaDestino.obtenerUltimaCartaRevelada();

        if(Reglas.validarMovimientoEntreColumnas(cartasAMover, ultimaCartaRCD))
        {
            columnaDestino.agregarCartas(columnaDestino.getCartasReveladas());
            columnaOrigen.sacarCartas(cartasAMover);
            return true;
        }
        return false;
    }

    public void mostrarEstadoColumnas() {
        for (int i = 0; i < columnas.size(); i++) {
            Columna columna = columnas.get(i);
            List<Carta> cartasReveladas = columna.getCartasReveladas();
            List<Carta> cartasOcultas = columna.getCartasOcultas();

            System.out.println("Columna " + i + ":");
            System.out.println("Cartas Reveladas:");
            for (Carta carta : cartasReveladas) {
                System.out.println(carta.getPalo() + " " + carta.getNumero());
            }

            System.out.println("Cartas Ocultas:");
            for (Carta carta : cartasOcultas) {
                System.out.println(carta.getPalo() + " " + carta.getNumero());
            }
        }

    }

}