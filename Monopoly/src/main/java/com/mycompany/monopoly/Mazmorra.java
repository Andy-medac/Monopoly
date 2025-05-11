package com.mycompany.monopoly;

public class Mazmorra extends Casilla {

    public Mazmorra(String nombre, int posicion) {
        super(nombre, posicion);
    }

@Override
    public void hacerAccion(Jugador jugador) {
        if (jugador.getMazmorra()) {
            System.out.println("Estás cumpliendo tu castigo en la mazmorra del Shogun.");
        } else {
            System.out.println("Has llegado a la mazmorra del Shogun, pero solo estás de visita.");
        }
    }
}
