package com.mycompany.monopoly;

public class CasillaIrMazmorra extends Casilla {
    
    private final int posicionMazmorra;

    public CasillaIrMazmorra(String nombre, int posicion, int posicionMazmorra) {
        super(nombre, posicion);
        this.posicionMazmorra = posicionMazmorra;
    }

    @Override
    public void hacerAccion(Jugador j) {
        j.setCasilla(this.posicionMazmorra);  // Mueve al jugador a la cárcel
        j.jugadorEnCarcel();              // Lo deja sin turno
        System.out.println("El clan rival te acusa de traicion. Cumpliras condena en la mazmorra");
    }
}
