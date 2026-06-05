package Modelo;

/**
 * Clase que representa al historial de una jugada
 * 
 * @author Cristiangm05
 * @version 1.0 02/06/2026
 */
public class HistorialJugada {

    private int idHistorial;
    private int idPartida;
    private int idJugador;
    private double apuesta;
    private String resultado; // 'Victoria', 'Derrota', 'Empate'

    public HistorialJugada() {
    }

    /**
     * Constructor por parámetros que inicializa cada atributo del historial de la jugada
     * 
     * @param idHistorial Representa el id del historial
     * @param idPartida Representa el id de la partida
     * @param idJugador Representa el id del jugador
     * @param apuesta Representa la cifra de la apuesta que se realizó
     * @param resultado Representa el resultado final. Las opciones son <code>Victoria, Derrota o Empate</code>
     * @since 1.0
     */
    public HistorialJugada(int idHistorial, int idPartida, int idJugador, double apuesta, String resultado) {
        this.idHistorial = idHistorial;
        this.idPartida = idPartida;
        this.idJugador = idJugador;
        this.apuesta = apuesta;
        this.resultado = resultado;
    }

    // Getters y Setters
    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public double getApuesta() {
        return apuesta;
    }

    public void setApuesta(double apuesta) {
        this.apuesta = apuesta;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
