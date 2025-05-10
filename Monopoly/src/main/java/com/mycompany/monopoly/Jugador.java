package com.mycompany.monopoly;

public class Jugador {

    private String nombre;
    private int casilla;
    private int dinero;
    private boolean carcel;
    private int condena;

    //solo recibe el nombre ya que los demás atributos tienen valores predeterminados
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.casilla = 0;
        this.dinero = 100;
        this.carcel = false;
        this.condena = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCasilla() {
        return this.casilla;
    }

    public void setCasilla(int casilla) {
        this.casilla = casilla;
    }

    public int getDinero() {
        return this.dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public boolean isCarcel() {
        return this.carcel;
    }

    public void setCarcel(boolean carcel) {
        this.carcel = carcel;
    }

    public int getTurnosSinJugar() {
        return this.condena;
    }

    public void setTurnosSinJugar(int turnosSinJugar) {
        this.condena = condena;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + "/ Posición en tablero: " + this.casilla + " / Dinero: " + this.dinero + " ME / Esta en la carcel: " + this.carcel;
    }

    public void jugadorEnCarcel() { // Si el jugador cae en la casilla cárcel, se activa el atributo y tiene 1 turno sin jugar
        this.carcel = true;
        this.condena = 1;
    }

    public void restarCondena() {
        if (condena > 0) { // Si la condena es mayor que 0, le resta un turno sin jugar.
            condena--;
            if (condena == 0) { // Si la condena ya es 0, entonces el jugador sale de la cárcel.
                carcel = false;
            }
        }
    }
}
