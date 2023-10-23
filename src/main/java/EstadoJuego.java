import java.io.*;
public class EstadoJuego {
    private String usuario;
    private String sistemaOperativo;
    private String nombreArchivo;
    private String directorioDatos;

    public EstadoJuego(){
        usuario = System.getProperty("user.name").toLowerCase();
        sistemaOperativo = System.getProperty("os.name").toLowerCase();
        nombreArchivo = "datos.java";

        if (sistemaOperativo.contains("linux"))
            directorioDatos = "/home/" + usuario + "/Documentos/" + nombreArchivo;
        if (sistemaOperativo.contains("windows"))
            directorioDatos = "C:/" + usuario + "/Documentos/" + nombreArchivo;
    }

    public Tablero buscarPartidaGuardada() throws IOException, ClassNotFoundException {
        Tablero tablero;
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(directorioDatos));
        tablero = (Tablero) entrada.readObject();
        entrada.close();
        return tablero;
    }

    public void guardarPartida(Tablero tablero) throws IOException {
        ObjectOutputStream datoAGuardar = new ObjectOutputStream(new FileOutputStream(directorioDatos));
        datoAGuardar.writeObject(tablero);
        datoAGuardar.close();
    }
}