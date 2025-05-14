package com.mycompany.monopoly;

public class CasillaSalida extends Casilla{

    public CasillaSalida(String nombre, int posicion, int recompensa) {
        super(nombre, posicion);
    }
    
    @Override
    public void hacerAccion(Jugador j) { //en caso de que caiga justo en la casilla de salida
        System.out.println("Los ancestros honran tu regreso al punto de partida.");
    }
}
