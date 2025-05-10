package com.mycompany.monopoly;

public class Dado {
    
    public Dado () {
        // no se necesita inicializar nada
    }
    
    public int lanzarDado() {
        int num = (int) (Math.random() * 6) + 1;
        return num;
    }
}
