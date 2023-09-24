import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        Mazo mazo = new Mazo(new ArrayList<>(), new ArrayList<>());

        tablero.iniciarJuego(1234567890L);

        List<Columna> columnas = new ArrayList<>();

        List<Fundacion> fundaciones = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            Columna columna = new Columna(new ArrayList<>(), new ArrayList<>());
            columnas.add(columna);
        }

        for (int i = 0; i < 4; i++) {
            Fundacion fundacion = new Fundacion();
            fundaciones.add(fundacion);
        }


        List<Carta> barajaDesordenada = tablero.getBaraja();// creo una baraja que tablero se van a ocupar de llenar

        tablero.repartirCartas(barajaDesordenada, columnas, mazo); // le asigno a cada columna la cantidad de cartas correspondientes
        tablero.mostrarEstadoColumnas(columnas);

        System.out.println("-----------------------------");
        mazo.imprimirCartas();


    }


}
