package Modelo;

import java.security.Timestamp;

/**
 * Clase que representa una partida
 * 
 * @author Cristiangm05
 * @version 1.0
 */
public class Partida {

    private int idPartida;
    private Timestamp fechaPartida;
    private String ganador;

    public Partida() {
    }

    /**
     * Constructor por parámetros que inicaliza la partida
     * 
     * @param idPartida Representa el id de la partida
     * @param fechaPartida Representa la fecha en la que se jugó la partida
     * @param ganador Representa el ganador final. Las opciones son <code>Banca, el nombre del jugador(es) o Empate</code>
     */
    public Partida(int idPartida, Timestamp fechaPartida, String ganador) {
        this.idPartida = idPartida;
        this.fechaPartida = fechaPartida;
        this.ganador = ganador;
    }

    // Getters y Setters
    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public Timestamp getFechaPartida() {
        return fechaPartida;
    }

    public void setFechaPartida(Timestamp fechaPartida) {
        this.fechaPartida = fechaPartida;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }
}
