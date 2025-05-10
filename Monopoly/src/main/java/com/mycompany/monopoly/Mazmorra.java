package com.mycompany.monopoly;

public class Mazmorra extends Casilla {

    public Mazmorra(String nombre, int posicion) {
        super(nombre, posicion);
    }

    @Override
    public void hacerAccion(Jugador j) {
        j.jugadorEnCarcel();
        System.out.println("El clan rival te acusa de traicion. Cumpliras condena en la mazmorra.");
    }
}
