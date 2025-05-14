package com.mycompany.monopoly;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Partida {

    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private Dado dado;

    public Partida() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Has entrado en las tierras del Shogun. Solo los mas astutos alcanzaran la gloria.");
        
        // para obtener los nombres de los jugadores
        String nombre1 = pedirNombreJugador(sc, "primer"); // le pasamos un string distinto según el orden que tenga en la partida
        jugador1 = new Jugador(nombre1);

        String nombre2 = pedirNombreJugador(sc, "segundo");
        jugador2 = new Jugador(nombre2);

        // creamos los objetos necesarios para la partida
        this.tablero = new Tablero();
        this.dado = new Dado();
    }

    private String pedirNombreJugador(Scanner sc, String o) {
        String nombre = "";
        while (nombre.isEmpty()) { //se repite mientras que el nombre esté vacío
            System.out.print("Escribe el nombre del " + o + " jugador: ");
            nombre = sc.nextLine();
            if (nombre.isEmpty()) {// si el nombre que introduce está vacío, muestra lo siguiente y se repite
                System.out.println("El nombre no puede estar vacio.");
            }
        }
        return nombre;
    }

    public void jugar() { //aquí definimos el bucle que va a tener el juego
        
        Scanner sc = new Scanner(System.in);
        boolean finalDePartida = false; //se usa para ver cuando se termina la partida
        int turno = 0;

        try {
            while (!finalDePartida) { //mientras que la partida no haya terminado
                Jugador j; //creamos un jugador (de forma temporal) para saber a quién le toca

                if (turno % 2 == 0) { // si es par juega jugador 1 y si es impar juega jugador 2
                    j = jugador1;
                } else {
                    j = jugador2;
                }
                if (!j.getMazmorra()) { // si mazmorra es false, no está en la cárcel
                    System.out.println("");
                    System.out.println("Turno de " + j.getNombre());
                    System.out.println("Dinero disponible: " + j.getDinero() + " ME.");
                    tablero.mostrarTableroConsola(jugador1, jugador2);

                    System.out.println("");
                    System.out.print("Pulsa ENTER para lanzar el dado...");
                    sc.nextLine();

                    int tirada = dado.lanzarDado(); //el jugador lanza el dado
                    System.out.println("Has sacado un " + tirada);

                    int casillaActual = j.getCasilla();
                    int nuevaPos = casillaActual + tirada;

                    if (nuevaPos >= tablero.totalCasillas()) { //esto garantiza que cuando se pase por la casilla de salida (no que caiga encima) se gane 20 ME
                        j.modificarDinero(20);
                        System.out.println("Has regresado al Templo de la Fortuna. Recibes 20 ME.");
                        nuevaPos -= tablero.totalCasillas(); // Restamos el total de casillas al valor de la nueva posición, para que se restablezca y así pueda desplazarse con normalidad
                    }
                    j.setCasilla(nuevaPos); //actualizamos la posicion en la que está el jugador
                    Casilla casilla = tablero.getCasilla(nuevaPos);
                    System.out.println("");
                    System.out.println("Has caido en " + casilla.getNombre()); //se muestra dónde ha caído

                    casilla.hacerAccion(j); //el jugador decide qué hacer con el dominio

                    if (j.getDinero() <= 0) { // si el jugador está en bancarrota, se termina la partida
                        finalDePartida = true;
                        System.out.println(j.getNombre() + " ha quedado en bancarrota.");

                        Jugador ganador;

                        if (j == jugador1) { //y el otro jugador es el ganador
                            ganador = jugador2;
                        } else {
                            ganador = jugador1;
                        }
                        System.out.println("Bajo la luna del ultimo dia, " + ganador.getNombre() + " alzo su estandarte. El imperio reconoce su reinado.");
                        guardarGanadorBD(ganador);
                        mostrarRanking();
                    }
                } else { // si está en la carcel, se le resta condena pero pierde turno
                    System.out.println("");
                    System.out.println(j.getNombre() + " esta en la mazmorra y pierde el turno.");
                    j.restarCondena();
                }
                turno++;
            }
        } catch (IndexOutOfBoundsException e) { //si hay algún problema con el array de casillas
            System.out.println("Error: Acceso fuera de los limites del tablero.");
        } catch (NullPointerException e) { // en el caso de que un objeto tenga una referencia que sea nula
            System.out.println("Error: Se encontro una referencia nula en la partida.");
        } catch (InputMismatchException e) { // si el usuario introduce un valor que no es válido
            System.out.println("Error: Dato invalido introducido por jugador.");
        } catch (Exception e) {
            System.out.println("Error desconocido: " + e.getMessage());
        } finally {
            sc.close();
            System.out.println("Partida finalizada.");
        }
    }

    private void guardarGanadorBD(Jugador g) {
        String url = "jdbc:mysql://localhost:3306/ranking_monopoly";
        String usuario = "root";
        String contraseña = "Medac";
        String sql = "INSERT INTO ranking (nombre, dinero) VALUES (?, ?)"; //las ? se usan para indicar la posición que se reemplazarán con los valores de stmt

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, g.getNombre()); //le pasamos nombre que será el primer ? de la sentencia SQL
            stmt.setInt(2, (int) g.getDinero()); // y el dinero que será el segundo ?
            stmt.executeUpdate(); // ejecutamos la sentencia
            stmt.close();
            conn.close();
            System.out.println("");
            System.out.println("El ganador se ha guardado en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar en la base de datos. ");
        } catch (Exception e) {
            System.out.println("Error desconocido: " + e.getMessage());
        }
    }

    private void mostrarRanking() { //para mostrar el ranking, nos conectamos a la BD
        String url = "jdbc:mysql://localhost:3306/ranking_monopoly";
        String usuario = "root";
        String contraseña = "Medac";

        String sql = "SELECT nombre, dinero FROM ranking ORDER BY dinero DESC"; //me muestra los datos de la BD en orden descendente del dinero del jugador

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña); 
                Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("");
            System.out.println("-------- RANKING DE GANADORES --------");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int dinero = rs.getInt("dinero");
                System.out.println(nombre + " - " + dinero + ": ME");
            }
            rs.close();
            stmt.close();
            conn.close(); //cerramos conexión
        } catch (SQLException e) {
            System.out.println("Error al mostrar el ranking." + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error desconocido." + e.getMessage());
        }
    }
}
