package com.mycompany.monopoly;

public class Monopoly {

    public static void main(String[] args) {
        
        System.out.println("Bienvenido al Monopoly de Andy!");
               Jugador jugador1 = new Jugador("Jugador 1");
        Dado dado = new Dado();

        System.out.println(jugador1);
        int tirada = dado.lanzarDado();
        System.out.println("Tirada: " + tirada);

        jugador1.setCasilla(jugador1.getCasilla() + tirada);
        System.out.println("Casilla: " + jugador1.getCasilla());
    }
}
