public class Tablero {

     private Maso stockPila;
     private Maso discarPila;
     private Maso foundationPilaUno;
     private Maso foundationPilaDos;
     private Maso foundationPilaTres;
     private Maso foundationPilaCuatro;
     private Maso pilaCartasUno;
     private Maso pilaCartasDos;
     private Maso pilaCartasTres;
     private Maso pilaCartasCuatro;
     private Maso pilaCartasCinco;
     private Maso pilaCartasSeis;
     private Maso pilaCartasSiete;

     public Tablero()
     {
          stockPila = new Maso();
          discarPila = new Maso();
          foundationPilaUno = new Maso();
          foundationPilaDos = new Maso();
          foundationPilaTres = new Maso();
          foundationPilaCuatro = new Maso();
          pilaCartasUno = new Maso();
          pilaCartasDos = new Maso();
          pilaCartasTres = new Maso();
          pilaCartasCuatro = new Maso();
          pilaCartasCinco = new Maso();
          pilaCartasSeis = new Maso();
          pilaCartasSiete = new Maso();
     }

     public Maso obtenerStockPila()
     {
          return stockPila;
     }

     public Maso obtenerDiscardPila()
     {
          return discarPila;
     }

     public Maso obtenerFoundationPilaUno()
     {
          return foundationPilaUno;
     }

     public Maso obtenerFoundationMasoDos() {
          return foundationPilaDos;
     }

     public Maso obtenerFoundationMasoTres() {
          return foundationPilaTres;
     }

     public Maso obtenerFoundationMasoCuatro() {
          return foundationPilaCuatro;
     }


}
