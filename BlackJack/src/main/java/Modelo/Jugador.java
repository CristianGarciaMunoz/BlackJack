package Modelo;

import java.util.ArrayList;

/**
 * Clase que representa a un jugador
 * 
 * @author Cristiangm05
 * @version 1.0 02/06/2026
 */
public class Jugador {

    private int idJugador;
    private String nombre;
    private double saldo;
    private boolean esVip;

    /**
     * Representa las cartas actuales de la mano del jugador
     */
    private ArrayList<Integer> mano;
    private boolean sePlanto;
    /**
     * Será <code>true</code> cuando se pase de 21
     */
    private boolean eliminado;

    public Jugador() {
        this.idJugador = 0;
        this.nombre = "";
        this.saldo = 100.00;
        this.esVip = false;
        this.mano = new ArrayList<>();
        this.sePlanto = false;
        this.eliminado = false;
    }

    /**
     * Constructor por parámetros que inicializa a un jugador
     * 
     * @param idJugador Representa el id del jugador
     * @param nombre Representa el nombre del jugador
     * @param saldo Representa el saldo del jugador
     */
    public Jugador(int idJugador, String nombre, double saldo) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.saldo = saldo;
        this.mano = new ArrayList<>();
        this.sePlanto = false;
        this.eliminado = false;
    }
    
    // Getters y Setters
    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isSePlanto() {
        return sePlanto;
    }

    public void setSePlanto(boolean sePlanto) {
        this.sePlanto = sePlanto;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public boolean isEsVip() {
        return esVip;
    }

    public void setEsVip(boolean esVip) {
        this.esVip = esVip;
    }

    // Sobrescribimos el toString para que se muestre el nombre en los JComboBox de la interfaz
    @Override
    public String toString() {
        return this.nombre + " (" + this.saldo + "€)";
    }
    
    // Métodos para la lógica del Blackjack
    /**
     * Añade un valor de una carta recibida por parámetros, a la mano del jugador, y comprueba si se pasa 
     * de 21 llamando al método <code>getPuntuacionMano()</code>
     * 
     * @param valorCarta Representa el valor de la carta que le tocó de la baraja al jugador
     * @since 1.0
     * @see #getPuntuacionMano
     */
    public void recibirCarta(int valorCarta) {
        this.mano.add(valorCarta);
        if (getPuntuacionMano() > 21) {
            this.eliminado = true;
        }
    }

    /**
     * Hace el recuento de las cartas de la mano del jugador, comprobando el caso de que la carta sea un As.
     * 
     * @return Devuelve el valor de las cartas de la mano del jugador
     * @since 1.0
     */
    public int getPuntuacionMano() {
        int total = 0;
        int asCount = 0;

        for (int carta : mano) {
            total += carta;
            if (carta == 11) { // Suponemos que el As inicialmente vale 11
                asCount++;
            }
        }

        // Si se pasa de 21 y tiene Ases, estos pasan a valer 1 en lugar de 11
        while (total > 21 && asCount > 0) {
            total -= 10;
            asCount--;
        }

        return total;
    }

    /**
     * Reinicia los valores de la mano (dejando el ArrayList vacío), y de los booleanos 
     * <code>sePlantó y eliminado</code> (poniendo sus valores a false)
     */
    public void reiniciarMano() {
        this.mano.clear();
        this.sePlanto = false;
        this.eliminado = false;
    }
}
