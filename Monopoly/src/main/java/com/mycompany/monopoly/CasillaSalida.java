package com.mycompany.monopoly;

public class CasillaSalida extends Casilla{

    public CasillaSalida(String nombre, int posicion, int recompensa) {
        super(nombre, posicion);

    }

    @Override
    public void hacerAccion(Jugador j) { // en el caso de que caiga justo en la casilla, recibe 20 ME
        System.out.println("Has pasado por el Templo de la Fortuna. Recibes 20 ME.");
        j.modificarDinero(20);
    }
}
