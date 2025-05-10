package com.mycompany.monopoly;

public class Dominio extends Casilla { // o zona en español

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
        if (j.getDinero() >= this.precio) {
            j.modificarDinero(-this.precio);
            this.propietario = j;
            System.out.println("Con tu oro y tu espada, has reclamado " + this.nombre + " por " + this.precio + " ME como tuyo.");
        } else {
            System.out.println("Tus cofres estan vacios. No puedes reclamar estas tierras aún.");
        }
    }

    @Override
    public void hacerAccion(Jugador j) {
        if (estaDisponible()) { //si es true, está sin conquistar.
            System.out.println("Has llegado a " + this.nombre + ". Puedes conquistarlo por " + this.precio + " ME.");
        } else if (this.propietario != j) { // si es false, tienes que pagar a su propietario el 10% de su precio.
            int pago = this.precio / 10;
            j.modificarDinero(-pago);
            propietario.modificarDinero(pago);
            System.out.println("Este dominio pertenece a " + this.propietario.getNombre() + ". Pagas " + pago + " ME.");
        } else {
            System.out.println("¡Descansa y recupera fuerzas! Llegaste a " + this.nombre);
        }
    }
}
