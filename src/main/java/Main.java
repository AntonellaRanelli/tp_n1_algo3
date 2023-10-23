import java.io.IOException;
public class Main {
    public static void main(String[] args){
        Tablero tablero;
        EstadoJuego estadoTablero = new EstadoJuego();
        try{
            tablero = estadoTablero.buscarPartidaGuardada();
        }catch (ClassNotFoundException | IOException ex){
            tablero = new TableroKlondike();
        }
    }
}