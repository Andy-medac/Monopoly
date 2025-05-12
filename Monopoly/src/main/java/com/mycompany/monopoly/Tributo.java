package com.mycompany.monopoly;

public class Tributo extends Casilla {

    private int cantidad;

    public Tributo(String nombre, int posicion, int cantidad) {
        super(nombre, posicion);
        this.cantidad = cantidad;
    }

    @Override
    public void hacerAccion(Jugador j) {
        j.modificarDinero(-cantidad);
        Santuario.añadirAlBote(cantidad); // se acumula en la casilla del santuario
        System.out.println("Pagas un tributo de " + cantidad + " ME. Este dinero se envía al Santuario del Clan.");
    }
}

