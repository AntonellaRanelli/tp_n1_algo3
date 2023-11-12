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
        var loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        var juego = new FXMLLoader(getClass().getResource("solitario.fxml"));

        loader.setController(this);
        VBox ventanaSeleccion = loader.load();
        VBox ventanaJuego = juego.load();
        var sceneSeleccion = new Scene(ventanaSeleccion);
        var scene = new Scene(ventanaJuego);

        stage.setScene(sceneSeleccion);
        stage.show();

        botonKlondike.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

                juegoKlondike(new TableroKlondike(), stage, scene);
            }
        });

        botonSpider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
        });


    }

    void juegoKlondike(TableroKlondike tablero, Stage stage, Scene escena){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Agregar cartas al GridPane
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                // Puedes cargar imágenes de cartas desde archivos o recursos
                // En este ejemplo, se utiliza una imagen de ejemplo (debe ser reemplazada)
                Image image = new Image("/Large/00.png");
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100); // Ajustar según sea necesario
                imageView.setFitHeight(150); // Ajustar según sea necesario

                // Agregar la imagen al GridPane en la posición i, j
                gridPane.add(imageView, j, i);
            }
        }

        StackPane root = (StackPane) escena.getRoot();
        root.getChildren().add(gridPane);
    }
}