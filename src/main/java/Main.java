import Base.Columna;
import Base.Fundacion;
import Base.Tablero;
import Klondike.TableroKlondike;
import Spider.TableroSpider;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @FXML
    Button botonKlondike;
    @FXML
    Button botonSpider;

    @Override
    public void start(Stage stage) throws Exception {
        var menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        var spiderLoader = new FXMLLoader(getClass().getResource("solitarioSpider.fxml"));
        var klondikeLoader = new FXMLLoader(getClass().getResource("solitarioSpider.fxml"));

        menuLoader.setController(this);
        VBox ventanaSeleccion = menuLoader.load();

        var sceneSeleccion = new Scene(ventanaSeleccion);


        stage.setScene(sceneSeleccion);
        stage.show();

        botonKlondike.setOnAction(actionEvent -> {
            try {
                VBox ventanaJuego = klondikeLoader.load();
                iniciarJuego(stage, ventanaJuego);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        botonSpider.setOnAction(actionEvent -> {
            try {
                VBox ventanaJuego = spiderLoader.load();
                iniciarJuego(stage, ventanaJuego);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }

    private void iniciarJuego(Stage stage, VBox ventanaJuego) {
        var scene = new Scene(ventanaJuego);
        stage.close();
        stage.setScene(scene);
        stage.setResizable(false);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.show();
    }
}