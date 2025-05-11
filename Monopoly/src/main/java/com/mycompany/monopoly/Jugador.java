package com.mycompany.monopoly;

public class Jugador {

    private String nombre;
    private int casilla;
    private int dinero;
    private boolean mazmorra;
    private int condena;

    //solo recibe el nombre ya que los demás atributos tienen valores predeterminados
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.casilla = 0;
        this.dinero = 100;
        this.mazmorra = false;
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

    public void modificarDinero(int cantidad) {
        this.dinero += cantidad;
    }

    public boolean getMazmorra() {
        return this.mazmorra;
    }

    public void setCarcel(boolean carcel) {
        this.mazmorra = carcel;
    }

    public int getCondena() {
        return this.condena;
    }

    public void setCondena(int turnosSinJugar) {
        this.condena = turnosSinJugar;
    }

    @Override
    public String toString() {
        String siONo = "";
        if (mazmorra){
           siONo = "Si"; 
        } else{
            siONo = "No";
        }
        return "Nombre: " + this.nombre + "/ Posición en tablero: " + this.casilla + " / Dinero: " + this.dinero + " ME / Esta en la carcel: " + siONo;
    }

    public void jugadorEnMazmorra() { // Si el jugador cae en la casilla cárcel, se activa el atributo y tiene 1 turno sin jugar
        this.mazmorra = true;
        this.condena = 1;
    }

    public void restarCondena() {
        if (condena > 0) { // Si la condena es mayor que 0, le resta un turno sin jugar.
            condena--;
            if (condena == 0) { // Si la condena ya es 0, entonces el jugador sale de la cárcel.
                mazmorra = false;
            }
        }
    }
}
