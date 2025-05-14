package com.mycompany.monopoly;

import java.util.ArrayList;

public class Tablero {

    private final ArrayList<Casilla> casillas; // aquí se guardarán todas las casillas del tablero

    public Tablero() {
        casillas = new ArrayList<>();
        aniadirCasillas(); //para añadir las casillas al array
    }

    private void aniadirCasillas() { //se crean las 40 casillas
        casillas.add(new CasillaSalida("Templo de la Fortuna", 0, 20)); // SALIDA
        casillas.add(new Dominio("Dojo de Kyoto", 1, 10));
        casillas.add(new Honorario("Donación Imperial", 2, 10));                   // HONORARIO
        casillas.add(new Dominio("Aldea Hanamura", 3, 12));
        casillas.add(new Tributo("Tributo al Daimyo", 4, 10));                     // TRIBUTO
        casillas.add(new Dominio("Mercado de Fushimi", 5, 14));
        casillas.add(new Dominio("Castillo Edo", 6, 15));
        casillas.add(new Honorario("Donación Imperial", 7, 10));                   // HONORARIO
        casillas.add(new Dominio("Gran Puerta Torii", 8, 16));
        casillas.add(new Dominio("Puerto de Nagasaki", 9, 18));
        casillas.add(new Mazmorra("Cárcel del Shogun", 10));                        // MAZMORRA
        casillas.add(new Dominio("Pagoda de los Susurros", 11, 20));
        casillas.add(new Dominio("Torre del Tigre", 12, 22));
        casillas.add(new Dominio("Almacén de Sake", 13, 21));
        casillas.add(new Dominio("Cabaña de Cazadores", 14, 23));
        casillas.add(new Dominio("Taller de Katanas", 15, 25));
        casillas.add(new Dominio("Mercado de Osaka", 16, 26));
        casillas.add(new Honorario("Donación Imperial", 17, 10));                  // HONORARIO
        casillas.add(new Dominio("Dojo del Viento", 18, 28));
        casillas.add(new Dominio("Castillo Himeji", 19, 30));
        casillas.add(new Santuario("Santuario del Clan", 20));                     // SANTUARIO
        casillas.add(new Dominio("Establos del Daimyo", 21, 25));
        casillas.add(new Dominio("Jardines Imperiales", 22, 28));
        casillas.add(new Tributo("Tributo al Daimyo", 23, 15));                    // TRIBUTO
        casillas.add(new Dominio("Templo de los Cinco Anillos", 24, 32));
        casillas.add(new Dominio("Castillo del Zorro Blanco", 25, 34));
        casillas.add(new Honorario("Milagro del Buda", 26, 12));
        casillas.add(new Dominio("Dojo del Loto Azul", 27, 30));
        casillas.add(new Dominio("Templo de Amaterasu", 28, 36));
        casillas.add(new Dominio("Bosque de los Susurros", 29, 32));
        casillas.add(new CasillaIrMazmorra("Ir a la Mazmorra", 30, 10));           // IR A LA MAZMORRA
        casillas.add(new Dominio("Mercado del Lago", 31, 33));
        casillas.add(new Dominio("Refugio del Ronin", 32, 35));
        casillas.add(new Honorario("Milagro del Buda", 33, 10));                   // HONORARIO
        casillas.add(new Dominio("Torre Shinano", 34, 37));
        casillas.add(new Dominio("Jardín de los Cerezos", 35, 40));
        casillas.add(new Dominio("Callejón de la Luna", 36, 38));
        casillas.add(new Dominio("Faro de Okinawa", 37, 42));
        casillas.add(new Tributo("Tributo al Daimyo", 38, 20));                    // TRIBUTO
        casillas.add(new Dominio("Palacio Imperial", 39, 50));
    }

    public Casilla getCasilla(int posicion) { //para adquirir la casilla según una posición
        return casillas.get(posicion);
    }

    public int totalCasillas() { //para saber cuantas casillas hay
        return casillas.size();
    }

public void mostrarTableroConsola(Jugador jugador1, Jugador jugador2) {// para mostrar el tablero en todo su conjunto (y así formar un cuadrado)
    System.out.println("---------------- TABLERO ----------------");

    // Parte superior (0 al 10)
    for (int i = 0; i <= 10; i++) {
        imprimirCasillaSola(i, jugador1, jugador2);
    }
    System.out.println();

    // Lados (izquierda 39 a 31, derecha 11 a 19)
    for (int i = 0; i < 9; i++) {
        int izq = 39 - i;
        int der = 11 + i;
        imprimirCasillasVertical(izq, der, jugador1, jugador2);
    }

    // Parte inferior (30 al 20, en orden descendente para que así siga el orden correcto de casillas)
    for (int i = 30; i >= 20; i--) {
        imprimirCasillaSola(i, jugador1, jugador2);
    }
    System.out.println();
}

    private void imprimirCasillaSola(int posicion, Jugador j1, Jugador j2) { //para imprimir cada casilla de manera individual
        String tipo = obtenerTipo(casillas.get(posicion));
        String jugadores = jugadoresEnCasilla(posicion, j1, j2);
        System.out.print("[" + tipo + jugadores + "] ");
    }

    private void imprimirCasillasVertical(int izq, int der, Jugador j1, Jugador j2) { //para imprimir las casillas verticales (izq y der)
        String casillaIzq = "[" + obtenerTipo(casillas.get(izq)) + jugadoresEnCasilla(izq, j1, j2) + "]";
        String casillaDer = "[" + obtenerTipo(casillas.get(der)) + jugadoresEnCasilla(der, j1, j2) + "]";
        System.out.println(casillaIzq + "                                        " + casillaDer);
    }

//para mostrar los jugadores en las casillas
    private String jugadoresEnCasilla(int pos, Jugador j1, Jugador j2) {
        String jugadores = "";
        if (j1.getCasilla() == pos) {
            jugadores += "J1";
        }
        if (j2.getCasilla() == pos) {
            jugadores += "J2";
        }
        return jugadores;
    }

    private String obtenerTipo(Casilla c) { //para imprimir el tipo de casilla que es cada una
        if (c instanceof Dominio) {
            return "D";
        }
        if (c instanceof Honorario) {
            return "H";
        }
        if (c instanceof Tributo) {
            return "T";
        }
        if (c instanceof Mazmorra) {
            return "C";
        }
        if (c instanceof CasillaIrMazmorra) {
            return "X";
        }
        if (c instanceof CasillaSalida) {
            return "S";
        }
        return "R"; // Recolectar (Parking)
    }
}
