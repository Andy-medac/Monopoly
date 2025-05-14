package com.mycompany.monopoly;

import java.util.Scanner;

public class Dominio extends Casilla {

    private int precio;
    private Jugador propietario;

    public Dominio(String nombre, int posicion, int precio) {
        super(nombre, posicion);
        this.precio = precio;
        this.propietario = null; // hay que tener en cuenta que no tiene dueño de forma predeterminada.
    }

    public boolean estaDisponible() {
        if (this.propietario == null) {
            return true;
        } else {
            return false;
        }
    }

    public void comprar(Jugador j) {
        if (!estaDisponible()) { //por si acaso volvemos a comprobar si está disponible
            System.out.println("Este dominio ya tiene dueño.");
            return;
        }
        if (j.getDinero() >= this.precio) { // en el caso de que tenga suficiente dinero, se compra
            j.modificarDinero(-this.precio);
            this.propietario = j;
            System.out.println("Con tu oro y tu espada, has reclamado " + this.nombre + " por " + this.precio + " ME como tuyo.");
        } else { // si no tiene dinero no puede comprarla
            System.out.println("Tus cofres estan vacios. No puedes reclamar estas tierras aún.");
        }
        System.out.println("");
    }

    @Override
    public void hacerAccion(Jugador j) {
        Scanner sc = new Scanner(System.in);
        String respuesta = "";

        if (estaDisponible()) {
            System.out.println("Has llegado a " + this.nombre + ". Puedes conquistarlo por " + this.precio + " ME.");

            do {
                System.out.print("¿Quieres conquistarlo? (s/n): ");
                respuesta = sc.nextLine();
                if (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n")) {
                    System.out.println("Por favor, responde solo con s o n.");
                }
            } while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));

            if (respuesta.equals("s")) {    // si introduce s, la compra
                comprar(j);
            } else {
                System.out.println("Decides no reclamar estas tierras... por ahora.");
            }
        } else if (this.propietario != j) { //si es propiedad de otro jugador, debe pagar el 10% de su precio de compra
            int pago = this.precio / 10;
            j.modificarDinero(-pago); //se resta de su cantidad de dinero total
            this.propietario.modificarDinero(pago);
            System.out.println("Este dominio pertenece a " + this.propietario.getNombre() + ". Pagas " + pago + " ME.");
        } else {
            System.out.println("¡Descansa y recupera fuerzas! Llegaste a " + this.nombre);
        }
    }
}
