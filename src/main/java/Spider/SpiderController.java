package Spider;

import Base.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpiderController {
    @FXML
    private Pane anchorPane;

    @FXML
    private Pane mazoPane;

    @FXML
    private Pane columnasPane;

    @FXML
    private Pane fundacionesPane;

    private static TableroSpider tablero;
    private Carta cartaSeleccionada;
    private List<Carta> cartasIncluidasEnSeleccionada;
    private Columna columnaSeleccionada;

    public void initialize() {
        redibujarTablero();

        mazoPane.setOnMousePressed(this::clickMazo);
    }

    public static void setTableroSpider(TableroSpider tableroSpider){
        tablero = tableroSpider;
    }

    public static Tablero getTablero(){
        return (Tablero) tablero;
    }
    private void setAtributosImagen(Pane pane, ImageView imagen){
        pane.getChildren().add(imagen);
    }

    private void clickMazo(MouseEvent event) {
        tablero.repartirMano();
        redibujarTablero();
    }

    private void redibujarTablero() {
        for (int indexFund = 0; indexFund < fundacionesPane.getChildren().size(); indexFund++) {
            Pane fundacionPane = (Pane) fundacionesPane.getChildren().get(indexFund);
            fundacionPane.getChildren().clear();
            Fundacion fundacionDatos = tablero.getFundacionPorIndice(indexFund);

            if(!fundacionDatos.fundacionVacia()) {
                List<Pane> cartasFundacion = obtenerCartasFundacion(fundacionDatos);
                fundacionPane.getChildren().addAll(cartasFundacion);
            }
        }

        for (int indexCol = 0; indexCol < columnasPane.getChildren().size(); indexCol++) {
            Pane columnaPane = (Pane) columnasPane.getChildren().get(indexCol);
            columnaPane.getChildren().clear();
            Columna columnaDatos = tablero.getColumnaPorIndice(indexCol);

            int indexCartasTotal = 0;

            Pane columnaVaciaPane = new Pane();
            columnaVaciaPane.getStyleClass().add("columna-vacia");
            columnaVaciaPane.setOnMouseClicked(event -> {
                if(cartaSeleccionada != null && tablero.moverColumnaAColumna( columnaSeleccionada, columnaDatos, cartasIncluidasEnSeleccionada)) {
                    redibujarTablero();
                    recalcularTrasMovimiento(columnaDatos);
                    cartaSeleccionada = null;
                    columnaSeleccionada = null;
                    cartasIncluidasEnSeleccionada = null;
                }

            });
            columnaPane.getChildren().add(columnaVaciaPane);

            for (int indexCarta = 0; indexCarta < columnaDatos.cantidadCartasOcultas(); indexCarta++) {
                ImageView cartaOculta = obtenerCartaOculta(indexCartasTotal);
                columnaPane.getChildren().add(cartaOculta);
                indexCartasTotal++;
            }

            for (int indexCarta = 0; indexCarta < columnaDatos.cantidadCartasReveladas(); indexCarta++) {
                Carta cartaDato = columnaDatos.getCartasReveladas().get(indexCarta);
                ArrayList<Carta> cartasPrevias = new ArrayList<>();
                for(int indexPrevias = indexCarta; indexPrevias < columnaDatos.cantidadCartasReveladas(); indexPrevias++) {
                    cartasPrevias.add(columnaDatos.getCartasReveladas().get(indexPrevias));
                }
                Pane nuevaCarta = obtenerCartaRevelada(indexCartasTotal, cartaDato, cartasPrevias, columnaDatos);
                columnaPane.getChildren().add(nuevaCarta);
                indexCartasTotal++;
            }
        }

        mazoPane.setVisible(!tablero.getMazo().cartasOcultasVacio());
    }

    private ImageView obtenerCartaOculta(int offsetY) {
        ImageView cartaOculta = new ImageView();
        cartaOculta.setFitWidth(101.75);
        cartaOculta.setFitHeight(140.0);
        cartaOculta.setLayoutY(offsetY * 23);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Large/999.png")));
        cartaOculta.setImage(image);
        cartaOculta.setOnMouseClicked(event -> {
            redibujarTablero();
            cartaSeleccionada = null;
            columnaSeleccionada = null;
            cartasIncluidasEnSeleccionada = null;
        });
        return cartaOculta;
    }

    private List<Pane> obtenerCartasFundacion(Fundacion fundacion) {
        List<Pane> cartas = new ArrayList<>();
        for(int i = 0; i < fundacion.getCartas().size(); i++) {
            Carta carta = fundacion.getCartas().get(i);
            VistaCartaSpider vistaCarta = new VistaCartaSpider(carta);
            ImageView revelada = new ImageView(vistaCarta.getImagenCarta());


            Pane cartaRevelada = new Pane();
            cartaRevelada.setPrefHeight(140);
            cartaRevelada.setPrefWidth(101.75);
            setAtributosImagen(cartaRevelada, revelada);

            cartas.add(cartaRevelada);
        }

        return cartas;
    }

    private Pane obtenerCartaRevelada(int offsetY, Carta carta, List<Carta> cartasPrevias, Columna columna) {
        VistaCartaSpider vistaCarta = new VistaCartaSpider(carta);
        ImageView revelada = new ImageView(vistaCarta.getImagenCarta());
        Pane cartaRevelada = new Pane();
        cartaRevelada.setPrefHeight(140);
        cartaRevelada.setPrefWidth(101.75);
        setAtributosImagen(cartaRevelada, revelada);
        cartaRevelada.setLayoutY(offsetY * 24);
        cartaRevelada.setOnMousePressed(event -> {
            if(cartaSeleccionada == null) {
                cartaSeleccionada = carta;
                columnaSeleccionada = columna;
                cartasIncluidasEnSeleccionada = cartasPrevias;
                cartaRevelada.setOpacity(0.8);
                cartaRevelada.getStyleClass().add("carta-seleccionada");
                return;
            } else if(tablero.moverColumnaAColumna( columnaSeleccionada, columna, cartasIncluidasEnSeleccionada)) {
                recalcularTrasMovimiento(columna);
            }

            redibujarTablero();
            cartaSeleccionada = null;
            columnaSeleccionada = null;
            cartasIncluidasEnSeleccionada = null;

        });
        return cartaRevelada;
    }

    private void recalcularTrasMovimiento(Columna columna) {
        int cantidadReveladas = columna.cantidadCartasReveladas();
        if(cantidadReveladas > 12) {
            List<Carta> ultimas13Cartas = columna.getCartasReveladas().subList(cantidadReveladas-13, cantidadReveladas);
            tablero.moverCartasAFundacion(columna, ultimas13Cartas);
        }
        if(tablero.finalizarJuego()) {
            Pane panelVictoria = new Pane();
            panelVictoria.getStyleClass().add("panel-victoria");
            panelVictoria.setLayoutY(20);
            panelVictoria.setLayoutX(40);
            Label textoVictoria = new Label("¡Ganaste! \uD83C\uDF89");
            textoVictoria.getStyleClass().add("texto-victoria");
            textoVictoria.setLayoutY(440);
            textoVictoria.setLayoutX(650);
            Button botonVolver = new Button();
            botonVolver.setText("Volver al menú");

            botonVolver.setOnAction(actionEvent -> {
                try {
                    var menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
                    VBox ventanaSeleccion = menuLoader.load();
                    Stage stage = (Stage) anchorPane.getScene().getWindow();
                    var sceneSeleccion = new Scene(ventanaSeleccion);
                    stage.close();
                    stage.setScene(sceneSeleccion);
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            panelVictoria.getChildren().add(textoVictoria);

            anchorPane.getChildren().add(panelVictoria);
        }
    }
}
