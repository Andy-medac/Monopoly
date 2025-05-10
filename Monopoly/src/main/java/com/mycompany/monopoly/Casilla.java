package com.mycompany.monopoly;

public abstract class Casilla { // la hacemos abstracta para que no se pueda instanciar y sirva de plantilla para otras clases, además de que implementen ciertos métodos.

    protected String nombre;
    protected int posicion;

    public Casilla(String nombre, int posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPosicion() {
        return this.posicion;
    }

    public abstract void hacerAccion(Jugador j); //recibe un objeto jugador para así después aplicarle las propiedades necesarias
}
