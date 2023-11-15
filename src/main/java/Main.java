import Base.Tablero;
import Klondike.TableroKlondike;
import Spider.TableroSpider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    //Ver esta variable. Para que stop pueda guardar el juego cuando se cierra la ventana
    //usa esta variable, donde se guarda el tablero una vez creado o cargado.
    Tablero tablero;
    @Override
    public void start(Stage stage) throws Exception {
        checkJuegoGuardado(stage);
    }

    //busca que exista un archivo llamado datos en la carpeta por defecto.
    //La direccion de la carpeta la maneja la maquina virtual.
    public void checkJuegoGuardado(Stage stage) {
        try{
            FileInputStream juego = new FileInputStream("datos.java");
            continuarJuegoGuardado(stage, juego);
        }catch (IOException ex){
            seleccionarJuegoNuevo(stage);
        }
    }

    //Carga el cuadro de aviso de juego guardado.
    public void continuarJuegoGuardado(Stage stage, FileInputStream juego){
        try {
            var menuJuegoGuardado = new FXMLLoader(getClass().getResource("juegoGuardado.fxml"));
            menuJuegoGuardado.setController(this);
            VBox ventanaJuegoGuardado = menuJuegoGuardado.load();
            Button botonSi = (Button) ventanaJuegoGuardado.lookup("#botonSi");
            Button botonNo = (Button) ventanaJuegoGuardado.lookup("#botonNo");

            var escena = new Scene(ventanaJuegoGuardado);
            stage.setScene(escena);
            stage.show();

            botonSi.setOnAction(actionEvent -> {
                cargarJuegoGuardado(juego);
                stage.close();
            });

            botonNo.setOnAction(actionEvent -> {
                stage.close();
                seleccionarJuegoNuevo(stage);
            });
        }catch (IOException ex){
        }
    }

    //Carga el cuadro de seleccion de juego.
    // Dentro de la logica de los botones
    void seleccionarJuegoNuevo(Stage stage){
        try {
            var loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            VBox ventanaSeleccion = loader.load();
            var sceneSeleccion = new Scene(ventanaSeleccion);
            loader.setController(this);
            stage.setScene(sceneSeleccion);
            stage.show();

            Button botonKlondike = (Button) ventanaSeleccion.lookup("#botonKlondike");
            Button botonSpider = (Button) ventanaSeleccion.lookup("#botonSpider");

            botonKlondike.setOnAction(actionEvent -> {
                var juego = new FXMLLoader(getClass().getResource("solitarioKlondike.fxml"));
                try {
                    VBox ventanaJuego = juego.load();
                    var scene = new Scene(ventanaJuego);
                    stage.close();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    TableroKlondike nuevoTablero = new TableroKlondike();
                    tablero = (Tablero) nuevoTablero;
                } catch (IOException e) {}

            });

            botonSpider.setOnAction(actionEvent -> {
                var juego = new FXMLLoader(getClass().getResource("solitarioSpider.fxml"));
                try{
                    VBox ventanaJuego = juego.load();
                    var scene = new Scene(ventanaJuego);
                    stage.close();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    TableroSpider nuevoTablero = new TableroSpider();
                    tablero = (Tablero) nuevoTablero;
                }catch (IOException e){}
            });

        }catch (IOException ex){
        }

    }

    //Cuando se encuentra el archivo datos.java
    //Se llama esta funcion, que dependiendo que instancia del tablero sea
    //Llama al constructor de juego iniciado de esa clase.
    //ver si vale la pena aplicar Bridge.
    boolean cargarJuegoGuardado(FileInputStream juego){

        FileInputStream juegoGuardado = juego;
        try {
            Tablero nuevoTablero = Tablero.deserializar(juegoGuardado);
            tablero = (Tablero) nuevoTablero;
            if (nuevoTablero instanceof TableroKlondike){
                juegoKlondike((TableroKlondike) nuevoTablero);
            }else{
                juegoSpider((TableroSpider) nuevoTablero);
            }
        }catch (IOException | ClassNotFoundException ex){
            return false;
        }
        return true;
    }

    // Implementar Lo que hace una vez creado un juego nuevo
    public static void juegoKlondike(TableroKlondike tablero){

    }
    public static void juegoSpider(TableroSpider tablero){

    }


    @Override
    public void stop(){
        try{
            tablero.serializar(new FileOutputStream("datos.java"));
        }catch (IOException ex){}
    }
}