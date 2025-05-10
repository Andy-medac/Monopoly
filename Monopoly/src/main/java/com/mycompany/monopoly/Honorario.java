package com.mycompany.monopoly;

public class Honorario extends Casilla {

    private int cantidad;

    public Honorario(String nombre, int posicion, int cantidad) {
        super(nombre, posicion);
        this.cantidad = cantidad;
    }

    @Override
    public void hacerAccion(Jugador j) {
        j.modificarDinero(this.cantidad);
        System.out.println("Los aldeanos agradecen tu proteccion. Te entregan un honorario de " + this.cantidad + " ME.");
    }
}
