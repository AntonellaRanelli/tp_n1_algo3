import Base.*;
import Klondike.TableroKlondike;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.util.List;

public class VistaTablero {
    private static TableroKlondike tablero;
    @FXML
    Pane contenedor;
    @FXML
    Pane columnasPane;
    @FXML
    Pane mazo;
    @FXML
    Pane descarte;

    Carta cartaSeleccionada = null;
    Columna columnaSeleccionada = null;
    Columna columnaDestino = null;
    Fundacion fundacionActual = null;
    Mazo mazoTablero = null;
    List<Carta> cartasAMover = null;

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
                        if (mazoTablero == null && cartasAMover.getFirst().getNumero() == Valor.K) {
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

            if (!fundacion.fundacionVacia()) {
                VistaCarta vistaCartaFundacion = new VistaCarta(fundacion.obtenerUltimaCarta());
                ImageView cartaFundacion = new ImageView(vistaCartaFundacion.getImagenCarta());
                setAtributosImagen(pane, cartaFundacion);
            }

            pane.setOnMousePressed(event -> {
                if (cartaSeleccionada != null && columnaSeleccionada != null){
                    fundacionActual = fundacion;
                    tablero.moverColumnaAFundacion(columnaSeleccionada, fundacionActual);
                }

                if (cartaSeleccionada!=null && mazoTablero!= null){
                    fundacionActual = fundacion;
                    tablero.moverMazoAFundacion(mazoTablero, fundacion);
                }

                reiniciarVariables();
                if(tablero.verificarJuegoGanado()){

                }
                mostrarCartas();
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
}
