package com.mycompany.monopoly;

public class Santuario extends Casilla {

    private static int bote;

    public Santuario(String nombre, int posicion) {
        super(nombre, posicion);
    }

    public static void añadirAlBote(int cantidad) {
        bote += cantidad;
    }

    @Override
    public void hacerAccion(Jugador j) {
        if (bote > 0) {
            j.modificarDinero(bote);
            System.out.println("Has llegado al Santuario del Clan. Recoges " + bote + " ME de ofrendas acumuladas.");
            bote = 0;
        } else {
            System.out.println("Has llegado al Santuario del Clan, pero no hay ofrendas en este momento.");
        }
    }
}

