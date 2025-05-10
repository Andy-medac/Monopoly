package com.mycompany.monopoly;

public class Tributo extends Casilla {

    private int cantidad;

    public Tributo(String nombre, int posicion, int cantidad) {
        super(nombre, posicion);
        this.cantidad = cantidad;
    }

    @Override
    public void hacerAccion(Jugador j) {
        j.modificarDinero(-this.cantidad);
        System.out.println("Tributo en " + this.nombre + ": pierdes " + this.cantidad + " ME.");
    }
}
