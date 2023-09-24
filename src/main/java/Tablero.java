public class Tablero {

     private Mazo mazo;
     private Columna columnaUno;
     private Columna columnaDos;
     private Columna columnaTres;
     private Columna columnaCuatro;
     private Columna columnaCinco;
     private Columna columnaSeis;
     private Columna columnaSiete;
     private Fundacion fundacionUno;
     private Fundacion fundacionDos;
     private Fundacion fundacionTres;
     private Fundacion fundacionCuatro;
//     private Maso pilaCartasSiete;

     public Tablero()
     {
          mazo = new Mazo();
          columnaUno = new Columna();
          columnaDos = new Columna();
          columnaTres = new Columna();
          columnaCuatro = new Columna();
          columnaCinco = new Columna();
          columnaSeis = new Columna();
          columnaSiete = new Columna();
          fundacionUno = new Fundacion();
          fundacionDos = new Fundacion();
          fundacionTres = new Fundacion();
          fundacionCuatro = new Fundacion();
     }

     public void inicializarJuegoAleatorio()
     {
          mazo.crearMazo();
          mazo.mezclarMazo();

     }
//
//     public Maso obtenerDiscardPila()
//     {
//          return discarPila;
//     }
//
//     public Maso obtenerFoundationPilaUno()
//     {
//          return foundationPilaUno;
//     }
//
//     public Maso obtenerFoundationMasoDos() {
//          return foundationPilaDos;
//     }
//
//     public Maso obtenerFoundationMasoTres() {
//          return foundationPilaTres;
//     }
//
//     public Maso obtenerFoundationMasoCuatro() {
//          return foundationPilaCuatro;
//     }


}
