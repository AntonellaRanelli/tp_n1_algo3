package Klondike;

import Base.*;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ControladorTablero {
    private static TableroKlondike tablero;
    @FXML
    private Pane contenedor;
    @FXML
    private Pane columnasPane;
    @FXML
    private Pane mazo;
    @FXML
    private Pane descarte;

    private Carta cartaSeleccionada = null;
    private Columna columnaSeleccionada = null;
    private Columna columnaDestino = null;
    private Fundacion fundacionActual = null;
    private Mazo mazoTablero = null;
    private List<Carta> cartasAMover = null;

    public void initialize(){
        mostrarCartas();
        mazo.setOnMousePressed(event -> {
            Mazo mazoTablero = tablero.getMazo();
            if(mazoTablero.revelarCarta() == null){
                descarte.getChildren().clear();
                cartaSeleccionada = null;
            }
            mostrarCartas();
        });
    }

    private void setAtributosImagen(Pane pane, ImageView imagen){
        imagen.fitWidthProperty().bind(pane.widthProperty());
        imagen.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(imagen);
    }

    private void mostrarCartasMazo(Pane ventanaJuego, TableroKlondike tablero){

        mazo.getChildren().clear();
        Mazo mazoActual = tablero.getMazo();
        ImageView reverso = new ImageView(new Image("/Large/999.png"));
        setAtributosImagen(mazo, reverso);

        reverso.setOnMousePressed(event -> {
            reiniciarVariables();
        });

        Pane pane = (Pane) ventanaJuego.lookup("#descarte");
        pane.getChildren().clear();

        if (mazoActual.cantidadCartasReveladas() != 0){

            VistaCarta cartaRevelada = new VistaCarta(mazoActual.obtenerUltimaCartaRevelada());
            ImageView revelada = new ImageView(cartaRevelada.getImagenCarta());
            setAtributosImagen(pane, revelada);

            revelada.setOnMousePressed(event -> {
                if(cartaSeleccionada == null) {
                    columnaSeleccionada = null;
                    columnaDestino = null;
                    mazoTablero = mazoActual;
                    cartaSeleccionada = cartaRevelada.getCarta();
                    revelada.setOpacity(0.8);
                    return;
                }

                if (cartaSeleccionada == cartaRevelada.getCarta()){
                    reiniciarVariables();
                    revelada.setOpacity(1);
                }
            });
        }
        if(mazoActual.cantidadCartasOcultas() == 0)
            mazo.getChildren().clear();
    }

    private void mostrarCartasColumnas(){
        Pane pane;
        Columna columnaActual;

        int cantidadCartasOcultas = 0;
        int cantidadReveladas = 0;
        int cantidadColumnas = 7;

        for (int i = 0; i < cantidadColumnas; i++)
        {
            columnaActual = tablero.getColumnaPorIndice(i);
            cantidadCartasOcultas = columnaActual.cantidadCartasOcultas();
            pane = (Pane) columnasPane.getChildren().get(i);
            pane.getChildren().clear();

            for (int u = 0; u < cantidadCartasOcultas; u++)
            {
                int ultimoElemento = pane.getChildren().size() - 1;
                double posY = 0;

                if (ultimoElemento >= 0)
                    posY = pane.getChildren().get(ultimoElemento).getBoundsInParent().getMinY();

                ImageView reverso = new ImageView(new Image("/Large/999.png"));
                reverso.setTranslateY(posY + 20);
                setAtributosImagen(pane, reverso);
            }

            Columna finalColumnaActual3 = columnaActual;
            Pane finalPane = pane;
            pane.setOnMousePressed(event -> {
                if (finalPane.getChildren().isEmpty()){
                    if (cartasAMover!= null) {
                        if (mazoTablero == null && cartasAMover.get(0).getNumero() == Valor.K) {
                            columnaDestino = finalColumnaActual3;
                            tablero.moverColumnaAColumna(columnaSeleccionada, columnaDestino, cartasAMover);
                            reiniciarVariables();
                            mostrarCartas();
                            return;
                        }
                    }
                    if (cartaSeleccionada != null){
                        if (cartaSeleccionada.getNumero() == Valor.K && mazoTablero != null) {
                            columnaDestino = finalColumnaActual3;
                            tablero.moverMazoAColumna(mazoTablero, columnaDestino);
                        }
                    }
                    reiniciarVariables();
                    mostrarCartas();
                }
            });

            cantidadReveladas = columnaActual.cantidadCartasReveladas();
            List<Carta> cartasReveladas = columnaActual.getCartasReveladas();

            for(int u=0; u<cantidadReveladas; u++)
            {
                double posY = 0;
                int ultimoElemento = pane.getChildren().size() - 1;

                if(ultimoElemento >=0)
                    posY = pane.getChildren().get(ultimoElemento).getBoundsInParent().getMinY();

                VistaCarta vistaCarta = new VistaCarta(cartasReveladas.get(u));
                ImageView revelada = new ImageView(vistaCarta.getImagenCarta());

                revelada.setTranslateY(posY+30);
                setAtributosImagen(pane, revelada);

                Columna finalColumnaActual = columnaActual;
                int posCarta = u;
                int finalCantidadReveladas = cantidadReveladas;

                revelada.setOnMousePressed(event -> {
                    if(cartaSeleccionada == null && mazoTablero == null) {
                        cartasAMover = cartasReveladas.subList(posCarta, finalCantidadReveladas);
                        cartaSeleccionada = vistaCarta.getCarta();
                        columnaSeleccionada = finalColumnaActual;
                        revelada.setOpacity(0.8);
                        return;
                    }

                    if (columnaSeleccionada == finalColumnaActual){
                        reiniciarVariables();
                        revelada.setOpacity(1);
                        return;
                    }

                    if (cartasAMover != null && mazoTablero == null){
                        columnaDestino = finalColumnaActual;
                        tablero.moverColumnaAColumna(columnaSeleccionada, columnaDestino, cartasAMover);
                        reiniciarVariables();
                        mostrarCartas();
                        return;
                    }

                    if (cartaSeleccionada != null && mazoTablero!=null){
                        columnaDestino = finalColumnaActual;
                        tablero.moverMazoAColumna(mazoTablero, finalColumnaActual);
                        reiniciarVariables();
                        mostrarCartas();
                        return;
                    }

                    if (cartaSeleccionada != null && fundacionActual != null){
                        tablero.moverFundacionAColumna(fundacionActual, finalColumnaActual);
                        reiniciarVariables();
                        mostrarCartas();
                    }
                });
            }
        }
    }

    private void mostrarCartasFundacion()
    {
        Pane pane;

        for (int i=0; i<4; i++)
        {
            Fundacion fundacion = tablero.getFundacionPorIndice(i);
            pane = (Pane) contenedor.lookup("#fundacion"+i);
            pane.getChildren().clear();
            if (!fundacion.fundacionVacia()) {
                VistaCarta vistaCartaFundacion = new VistaCarta(fundacion.obtenerUltimaCarta());
                ImageView cartaFundacion = new ImageView(vistaCartaFundacion.getImagenCarta());
                setAtributosImagen(pane, cartaFundacion);

                cartaFundacion.setOnMousePressed(event -> {

                    if (cartaSeleccionada == null && fundacionActual == null){
                        cartaSeleccionada = vistaCartaFundacion.getCarta();
                        fundacionActual = fundacion;
                        cartaFundacion.setOpacity(0.8);
                        return;
                    }

                    if (fundacionActual != null){
                        reiniciarVariables();
                        mostrarCartas();
                    }
                });
            }

            pane.setOnMousePressed(event -> {
                if (cartaSeleccionada != null && columnaSeleccionada != null){
                    fundacionActual = fundacion;
                    tablero.moverColumnaAFundacion(columnaSeleccionada, fundacionActual);
                    reiniciarVariables();
                    mostrarCartas();
                    if(tablero.verificarJuegoGanado()){
                        manejarJuegoGanado();
                    }
                    return;
                }

                if (cartaSeleccionada!=null && mazoTablero!= null){
                    fundacionActual = fundacion;
                    tablero.moverMazoAFundacion(mazoTablero, fundacion);
                    reiniciarVariables();
                    mostrarCartas();
                    if(tablero.verificarJuegoGanado()){
                        manejarJuegoGanado();
                    }
                }
            });
        }
    }

    private void reiniciarVariables(){
        mazoTablero = null;
        columnaSeleccionada = null;
        fundacionActual = null;
        cartaSeleccionada = null;
        cartasAMover = null;
    }

    private void mostrarCartas(){
        mostrarCartasColumnas();
        mostrarCartasMazo(contenedor, tablero);
        mostrarCartasFundacion();
    }

    public static void setTableroKlondike(TableroKlondike tableroKlondike){
        tablero = tableroKlondike;
    }

    public static Tablero getTablero(){
        return (Tablero) tablero;
    }

    private void manejarJuegoGanado() {
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
                Stage stage = (Stage) contenedor.getScene().getWindow();
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

        contenedor.getChildren().add(panelVictoria);
    }
}
