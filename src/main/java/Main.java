import Base.*;
import Klondike.ControladorTablero;
import Klondike.TableroKlondike;
import Spider.SpiderController;
import Spider.TableroSpider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    static Tablero tablero;
    @Override
    public void start(Stage stage) throws Exception {
        checkJuegoGuardado(stage);
    }

    public void checkJuegoGuardado(Stage stage) {
        try{
            FileInputStream juego = new FileInputStream("datos.java");
            continuarJuegoGuardado(stage, juego);
        }catch (IOException ex){
            seleccionarJuegoNuevo(stage);
        }
    }

    public void continuarJuegoGuardado(Stage stage, FileInputStream juego){
        try {
            var menuJuegoGuardado = new FXMLLoader(getClass().getResource("juegoGuardado.fxml"));
            VBox ventanaJuegoGuardado = menuJuegoGuardado.load();
            Button botonSi = (Button) ventanaJuegoGuardado.lookup("#botonSi");
            Button botonNo = (Button) ventanaJuegoGuardado.lookup("#botonNo");

            var escena = new Scene(ventanaJuegoGuardado);
            stage.setScene(escena);
            stage.show();

            botonSi.setOnAction(actionEvent -> {
                stage.close();
                cargarJuegoGuardado(juego, stage);
            });

            botonNo.setOnAction(actionEvent -> {
                stage.close();
                seleccionarJuegoNuevo(stage);
            });
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    void seleccionarJuegoNuevo(Stage stage){
        FXMLLoader juegoKlondikeFXML = new FXMLLoader(getClass().getResource("solitarioKlondike.fxml"));
        FXMLLoader juegoSpiderFXML = new FXMLLoader(getClass().getResource("solitarioSpider.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        try {
            VBox ventanaSeleccion = loader.load();
            var sceneSeleccion = new Scene(ventanaSeleccion);
            stage.setScene(sceneSeleccion);
            stage.show();

            Button botonKlondike = (Button) ventanaSeleccion.lookup("#botonKlondike");
            Button botonSpider = (Button) ventanaSeleccion.lookup("#botonSpider");

            botonKlondike.setOnAction(actionEvent -> {
                try {
                    stage.close();
                    TableroKlondike nuevoTablero = new TableroKlondike();
                    ControladorTablero.setTableroKlondike(nuevoTablero);
                    tablero = (Tablero) nuevoTablero;
                    VBox ventanaJuego = juegoKlondikeFXML.load();
                    juegoKlondike(stage, ventanaJuego);
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            });

            botonSpider.setOnAction(actionEvent -> {
                try {
                    stage.close();
                    TableroSpider nuevoTablero = new TableroSpider();
                    SpiderController.setTableroSpider(nuevoTablero);
                    tablero = (Tablero) nuevoTablero;
                    VBox ventanaJuego = juegoSpiderFXML.load();
                    juegoSpider(stage, ventanaJuego);
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            });

        }catch (IOException ex){
        }
    }

    boolean cargarJuegoGuardado(FileInputStream juego, Stage stage){
        //FXMLLoader vistaJuego = new FXMLLoader(getClass().getResource("solitarioKlondike.fxml"));
        FXMLLoader juegoKlondikeFXML = new FXMLLoader(getClass().getResource("solitarioKlondike.fxml"));
        FXMLLoader juegoSpiderFXML = new FXMLLoader(getClass().getResource("solitarioSpider.fxml"));
        try {
            stage.close();
            Tablero nuevoTablero = Tablero.deserializar(juego);
            tablero = nuevoTablero;
            if (nuevoTablero instanceof TableroKlondike){
                ControladorTablero.setTableroKlondike((TableroKlondike) nuevoTablero);
                VBox ventanaJuego = juegoKlondikeFXML.load();
                juegoKlondike(stage, ventanaJuego);
            }else{
                //juegoSpider((TableroSpider) nuevoTablero);
                SpiderController.setTableroSpider((TableroSpider) nuevoTablero);
                VBox ventanaJuego = juegoSpiderFXML.load();
                juegoSpider(stage, ventanaJuego);
            }
        }catch (IOException | ClassNotFoundException ex){
            return false;
        }
        return true;
    }

    public static void juegoKlondike(Stage stage, VBox ventanaJuego){
        Scene escena = new Scene(ventanaJuego);
        stage.setScene(escena);
        stage.setResizable(true);
        stage.show();
    }

    public static void juegoSpider(Stage stage, VBox ventanaJuego){
        Scene escena = new Scene(ventanaJuego);
        stage.setScene(escena);
        stage.setResizable(true);
        stage.show();

    }

    @Override
    public void stop(){
        try{
            if (tablero instanceof TableroKlondike) {
                //falta agregar para que se serialice el otro modo tambien esto no me esta funcionando
                tablero = ControladorTablero.getTablero();
            } else{
                tablero = SpiderController.getTablero();
            }
            tablero.serializar(new FileOutputStream("datos.java"));
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}