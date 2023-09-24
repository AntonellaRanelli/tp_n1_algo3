import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    }



    public void iniciarJuego(long semillaAleatoria) { // iniciador de juego con semilla aleatoria; semilla tipo long
        for (int numero = 1; numero <= 13; numero++) {
            for (String palo : new String[]{"Picas", "Corazones", "Diamantes", "Tréboles"}) {
                String color;
                if (palo.equals("Picas") || palo.equals("Tréboles")) {
                    color = "Negro";
                } else {
                    color = "Rojo";
                }
                Carta carta = new Carta(color, palo, numero);
                baraja.add(carta);
            }
        }

        Random random = new Random(semillaAleatoria);
        Collections.shuffle(baraja, random);
    }


    public List<Columna> getColumnas() {
        return columnas;
    }

    public List<Fundacion> getFundaciones() {
        return fundaciones;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public List<Carta> getBaraja() {
        return baraja;
    }

    /*public void repartirCartas(List<Carta> barajaDesordenada, List<Columna> columnas) {
        int cartasPorColumna = 1;
        for (Columna columna : columnas) {
            // Asignar una carta revelada
            if (!barajaDesordenada.isEmpty()) {
                Carta cartaRevelada = barajaDesordenada.remove(0);
                columna.getCartasReveladas().add(cartaRevelada);
            }

            // Asignar las cartas ocultas
            for (int i = 0; i < cartasPorColumna - 1; i++) {
                if (!barajaDesordenada.isEmpty()) {
                    Carta cartaOculta = barajaDesordenada.remove(0);
                    columna.getCartasOcultas().add(cartaOculta);
                }
            }

            cartasPorColumna++;
        }
    }




    public void mostrarEstadoColumnas(List<Columna> columnas) {
        for (int i = 0; i < columnas.size(); i++) {
            Columna columna = columnas.get(i);
            int cartasReveladas = columna.getCartasReveladas().size();
            int cartasOcultas = columna.getCartasOcultas().size();
            System.out.println("Columna " + i + ": Cartas Reveladas = " + cartasReveladas + ", Cartas Ocultas = " + cartasOcultas);
        }
    }
*/

    public void repartirCartas(List<Carta> barajaDesordenada, List<Columna> columnas, Mazo mazo) { //reparte las carta entre las columnas el resto va al mazo
        int cartasPorColumna = 1;

        // Reiniciar el mazo
        mazo.resetearMazo();

        for (Columna columna : columnas) {
            // Asignar una carta revelada
            if (!barajaDesordenada.isEmpty()) {
                Carta cartaRevelada = barajaDesordenada.remove(0);
                columna.getCartasReveladas().add(cartaRevelada);
            }

            // Asignar las cartas ocultas
            for (int i = 0; i < cartasPorColumna - 1; i++) {
                if (!barajaDesordenada.isEmpty()) {
                    Carta cartaOculta = barajaDesordenada.remove(0);
                    columna.getCartasOcultas().add(cartaOculta);
                }
            }

            cartasPorColumna++;
        }

        // Agregar las cartas restantes al mazo
        mazo.setCartasOcultas(new ArrayList<>(barajaDesordenada));
    }

    public boolean validarMovimientoEntreColumnas(List<Carta> cartasAMover, Carta cartaColumnaDestino) {
        // Verificar si la cartaColumnaDestino es NULL
        if (cartaColumnaDestino == null) {
            // La primera carta debe ser 13
            if (cartasAMover.isEmpty() || cartasAMover.get(0).getNumero() != 13) {
                return false;
            }
        } else {
            // Verificar si el color de la cartaColumnaDestino no es igual a la primera carta de cartasAMover
            if (!cartaColumnaDestino.getColor().equals(cartasAMover.get(0).getColor())) {
                return false;
            }

            // Verificar si el número de la cartaColumnaDestino es menor por 1
            if (cartaColumnaDestino.getNumero() != cartasAMover.get(0).getNumero() - 1) {
                return false;
            }
        }

        return true; // Movimiento válido
    }




    public boolean validarMovimientoColumnaAFundacion(List<Carta> cartasReveladasColumna, Carta cartaReveladaFundacion) {
        // Verificar si la fundación es null
        if (cartaReveladaFundacion == null) {
            // La última carta de la lista de cartas reveladas de columna debe ser 1
            if (!cartasReveladasColumna.isEmpty() && cartasReveladasColumna.get(cartasReveladasColumna.size() - 1).getNumero() == 1) {
                return true;
            }
        } else {
            // Verificar si la última carta de la lista de cartas reveladas de columna es de igual palo que la carta revelada de fundación
            if (!cartasReveladasColumna.isEmpty() && cartasReveladasColumna.get(cartasReveladasColumna.size() - 1).getPalo().equals(cartaReveladaFundacion.getPalo())) {
                // Verificar si el número de la última carta revelada de la lista de columna es mayor por 1 que la carta fundación
                if (cartasReveladasColumna.get(cartasReveladasColumna.size() - 1).getNumero() == cartaReveladaFundacion.getNumero() + 1) {
                    return true;
                }
            }
        }

        return false; // Movimiento no válido
    }

    public boolean validarMovimientoMazoAColumna( List<Carta> cartasReveladasColumna, Carta ultimaCartaReveladaMazo) {
        // Verificar si la lista de cartas reveladas de columna es null o vacía
        if (cartasReveladasColumna == null || cartasReveladasColumna.isEmpty()) {
            return false;
        }

        // Verificar si la última carta revelada del mazo no es del mismo color que la última carta de la lista de columna
        if (!ultimaCartaReveladaMazo.getColor().equals(cartasReveladasColumna.get(cartasReveladasColumna.size() - 1).getColor())) {
            // Verificar si el número de la última carta revelada del mazo es menor por 1 que el de la última carta de la lista de columna
            if (ultimaCartaReveladaMazo.getNumero() == cartasReveladasColumna.get(cartasReveladasColumna.size() - 1).getNumero() - 1) {
                return true;
            }
        }

        return false; // Movimiento no válido
    }

    public boolean validarMovimientoMazoAFundacion( List<Carta> ultimaCartaReveladaFundacion, Carta ultimaCartaReveladaMazo) {
        // Verificar si la lista de última carta revelada de fundación es null
        if (ultimaCartaReveladaFundacion == null) {
            // La última carta revelada de mazo debe ser igual a 1
            if (ultimaCartaReveladaMazo != null && ultimaCartaReveladaMazo.getNumero() == 1) {
                return true;
            }
        } else {
            // Asegurarse de que la lista de última carta revelada de fundación contenga solo una carta
            if (ultimaCartaReveladaFundacion.size() == 1) {
                Carta cartaFundacion = ultimaCartaReveladaFundacion.get(0);

                // Verificar si la última carta revelada de mazo es del mismo palo que la última carta de la lista de fundación
                if (ultimaCartaReveladaMazo != null && ultimaCartaReveladaMazo.getPalo().equals(cartaFundacion.getPalo())) {
                    // Verificar si el número de la última carta revelada de mazo es mayor por 1 que el de la última carta de la lista de fundación
                    if (ultimaCartaReveladaMazo.getNumero() == cartaFundacion.getNumero() + 1) {
                        return true;
                    }
                }
            }
        }

        return false; // Movimiento no válido
    }

    public boolean verificarJuegoGanado(List<Fundacion> fundaciones) {
        for (Fundacion fundacion : fundaciones) {
            List<Carta> cartas = fundacion.getCartas();
            if (cartas.size() != 13) {
                return false;
            }
        }
        return true; // Todas las fundaciones tienen 13 cartas, el juego está ganado
    }

    public void mostrarEstadoColumnas(List<Columna> columnas) {
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

    public void hacerMovimientoCaF(Columna columna, Fundacion fundacion, List<Carta> cartasReveladasColumna, Carta cartaReveladaFundacion)
    {


        if (validarMovimientoColumnaAFundacion(cartasReveladasColumna,cartaReveladaFundacion))
        {
            fundacion.agregarCarta(cartaReveladaFundacion);
            columna.sacarCarta(columna.getCartasReveladas().size()-1);
        }
    }

    public  void hacerMovimientoCaC(Columna columnaOrigen, Columna columnaDestino, List<Carta> cartasReveladasCO, List<Carta> cartasReveladasCD )
    {
        Carta ultimaCartaRCD = cartasReveladasCD.get(cartasReveladasCD.size()-1);

        if(validarMovimientoEntreColumnas(cartasReveladasCO, ultimaCartaRCD))
        {
            columnaDestino.agregarCartas(cartasReveladasCO);
            columnaOrigen.sacarCartas(cartasReveladasCO);

        }
    }

    public  void hacerMovimientoFaC (Columna columna, Fundacion fundacion, List<Carta> cartasReveladasColumna, List<Carta> cartasReveladasFundacion )
    {
        Carta ultimaCartaRCD = fundacion.obtenerUltimaCarta();

        //Falta metodo validar de Fundacion  a Columna
    }
















}



