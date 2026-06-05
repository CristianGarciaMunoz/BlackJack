package Controlador;

import Modelo.*;
import Vista.MesaJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Controlador principal que gestiona la lógica de la partida de Blackjack.
 * Maneja el flujo de turnos, apuestas, interacción con la base de datos y actualización de la vista.
 */
public class ControladorMesa implements ActionListener {

    private final MesaJuego vista;
    private final ConsultasBD modelo;
    private final Mazo mazo;

    private ArrayList<Jugador> jugadoresEnMesa = new ArrayList<>();
    private int indiceTurno = 0;
    private ArrayList<Integer> manoBanca = new ArrayList<>();
    private int idPartidaActual = -1;

    private ArrayList<Double> apuestasEnMesa = new ArrayList<>();

    /**
     * Constructor del controlador que inicializa la vista, el modelo, el mazo y los listeners de los componentes.
     * @param vista  Instancia de la ventana MesaJuego.
     * @param modelo Instancia de ConsultasBD para las operaciones de base de datos.
     */
    public ControladorMesa(MesaJuego vista, ConsultasBD modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.mazo = new Mazo();

        this.vista.btnPedir.addActionListener(this);
        this.vista.btnPlantarse.addActionListener(this);
        this.vista.btnRepartir.addActionListener(this);
        this.vista.btnSalirMesa.addActionListener(this);
        this.vista.btnInfo.addActionListener(this);

        javax.swing.JComboBox[] combos = {vista.comboJugador1, vista.comboJugador2, vista.comboJugador3, vista.comboJugador4};
        for (javax.swing.JComboBox combo : combos) {
            combo.addActionListener(e -> actualizarSaldoVisual());
        }
    }

    /**
     * Método auxiliar para gestionar de forma segura el estado de los botones.
     */
    private void setEstadoBotones(boolean repartir, boolean juego) {
        vista.btnRepartir.setEnabled(repartir);
        vista.btnPedir.setEnabled(juego);
        vista.btnPlantarse.setEnabled(juego);
    }

    /**
     * Carga los jugadores disponibles en los combos de la interfaz.
     */
    private void cargarJugadoresEnAsientos() {
        ArrayList<Jugador> listaBD = modelo.obtenerJugadores();
        javax.swing.JComboBox[] combos = {vista.comboJugador1, vista.comboJugador2, vista.comboJugador3, vista.comboJugador4};

        for (javax.swing.JComboBox combo : combos) {
            for (ActionListener al : combo.getActionListeners()) {
                combo.removeActionListener(al);
            }
            combo.removeAllItems();
            combo.addItem("--- VACÍO ---");
            for (Jugador j : listaBD) {
                combo.addItem(j.getIdJugador() + " - " + j.getNombre());
            }
            combo.addActionListener(e -> actualizarSaldoVisual());
        }
    }

    /**
     * Actualiza la visualización de los saldos de los jugadores seleccionados en los combos.
     */
    private void actualizarSaldoVisual() {
        if (vista == null) return;
        javax.swing.JComboBox[] combos = {vista.comboJugador1, vista.comboJugador2, vista.comboJugador3, vista.comboJugador4};
        javax.swing.JLabel[] lblSaldos = {vista.lblSaldo1, vista.lblSaldo2, vista.lblSaldo3, vista.lblSaldo4};

        for (int i = 0; i < combos.length; i++) {
            try {
                Jugador j = procesarAsiento(combos[i]);
                lblSaldos[i].setText(j != null ? String.format("%.2f €", j.getSaldo()) : "---");
            } catch (Exception e) {
                lblSaldos[i].setText("---");
            }
        }
    }

    /**
     * Prepara la mesa y hace visible la ventana de juego.
     */
    public void iniciar() {
        limpiarMesa();
        vista.setVisible(true);
        cargarJugadoresEnAsientos();
        setEstadoBotones(true, false);
    }

    /**
     * Gestiona las acciones de usuario sobre los botones de la mesa.
     * @param e Evento de acción capturado desde la interfaz.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnRepartir) {
            iniciarRonda();
        } else if (e.getSource() == vista.btnPedir) {
            if (indiceTurno < jugadoresEnMesa.size()) {
                Jugador actual = jugadoresEnMesa.get(indiceTurno);
                actual.recibirCarta(mazo.robarCarta());
                actualizarInterfaz();
                if (actual.isEliminado()) avanzarTurno();
            }
        } else if (e.getSource() == vista.btnPlantarse) {
            if (indiceTurno < jugadoresEnMesa.size()) {
                jugadoresEnMesa.get(indiceTurno).setSePlanto(true);
                avanzarTurno();
            }
        } else if (e.getSource() == vista.btnSalirMesa) {
            limpiarMesa();
            vista.dispose();
            GestorNavegacion.irAlMenu(modelo);
        } else if (e.getSource() == vista.btnInfo) {
            JOptionPane.showMessageDialog(vista, leerInstrucciones("comoJugar.txt"), "Cómo Jugar - Blackjack", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Lee un archivo de texto con las instrucciones del juego.
     * @param rutaArchivo Ruta del archivo de texto.
     * @return El contenido del archivo o un mensaje de error.
     */
    public String leerInstrucciones(String rutaArchivo) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) contenido.append(linea).append("\n");
        } catch (IOException e) {
            return "Error al cargar las instrucciones.";
        }
        return contenido.toString();
    }

    /**
     * Inicializa una nueva ronda de Blackjack, procesando apuestas y repartiendo cartas.
     */
    private void iniciarRonda() {
        jugadoresEnMesa.clear();
        apuestasEnMesa.clear();
        for (int i = 0; i < 4; i++) { jugadoresEnMesa.add(null); apuestasEnMesa.add(0.0); }

        javax.swing.JComboBox[] combos = {vista.comboJugador1, vista.comboJugador2, vista.comboJugador3, vista.comboJugador4};
        JTextField[] txts = {vista.txtApuesta1, vista.txtApuesta2, vista.txtApuesta3, vista.txtApuesta4};

        for (int i = 0; i < combos.length; i++) {
            Jugador j = procesarAsiento(combos[i]);
            if (j != null) {
                try {
                    double apuesta = Double.parseDouble(txts[i].getText());
                    if (apuesta > j.getSaldo() || apuesta <= 0) throw new Exception();
                    j.setSaldo(j.getSaldo() - apuesta);
                    modelo.actualizarSaldo(j.getIdJugador(), j.getSaldo());
                    jugadoresEnMesa.set(i, j);
                    apuestasEnMesa.set(i, apuesta);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Apuesta inválida en Asiento " + (i + 1));
                    setEstadoBotones(true, false);
                    return;
                }
            }
        }

        if (jugadoresEnMesa.stream().noneMatch(j -> j != null)) {
            setEstadoBotones(true, false);
            return;
        }

        idPartidaActual = modelo.insertarPartida();
        indiceTurno = 0;
        while (indiceTurno < 4 && jugadoresEnMesa.get(indiceTurno) == null) indiceTurno++;

        mazo.inicializarMazo();
        manoBanca.clear();
        manoBanca.add(mazo.robarCarta());
        for (Jugador j : jugadoresEnMesa) {
            if (j != null) {
                j.reiniciarMano();
                j.recibirCarta(mazo.robarCarta());
                j.recibirCarta(mazo.robarCarta());
            }
        }
        setEstadoBotones(false, true);
        actualizarInterfaz();
    }

    /**
     * Avanza el turno al siguiente jugador activo o a la banca.
     */
    private void avanzarTurno() {
        int siguiente = indiceTurno + 1;
        while (siguiente < 4 && jugadoresEnMesa.get(siguiente) == null) siguiente++;
        if (siguiente >= 4) ejecutarTurnoBanca();
        else { indiceTurno = siguiente; actualizarInterfaz(); }
    }

    /**
     * Actualiza los elementos gráficos de la vista de la mesa.
     */
    private void actualizarInterfaz() {
        vista.lblBancaPuntos.setText(Integer.toString(calcularPuntosBanca()));
        JLabel[] lblSaldos = {vista.lblSaldo1, vista.lblSaldo2, vista.lblSaldo3, vista.lblSaldo4};
        JLabel[] lblCartas = {vista.lblCartas1, vista.lblCartas2, vista.lblCartas3, vista.lblCartas4};

        for (int i = 0; i < 4; i++) {
            Jugador j = jugadoresEnMesa.get(i);
            if (j != null) {
                lblSaldos[i].setText(String.format("%.2f €", j.getSaldo()));
                lblCartas[i].setText("Pts: " + j.getPuntuacionMano());
            } else {
                lblSaldos[i].setText("---");
                lblCartas[i].setText("---");
            }
        }
        if (indiceTurno < jugadoresEnMesa.size() && jugadoresEnMesa.get(indiceTurno) != null) {
            vista.lblMensajeTurno.setText("Turno: " + jugadoresEnMesa.get(indiceTurno).getNombre());
        } else {
            vista.lblMensajeTurno.setText("Esperando turno...");
        }
    }

    /**
     * Ejecuta el turno de la banca, comparando manos y calculando resultados.
     */
    private void ejecutarTurnoBanca() {
        setEstadoBotones(false, false);
        int puntosBanca = calcularPuntosBanca();
        while (puntosBanca < 17) {
            manoBanca.add(mazo.robarCarta());
            puntosBanca = calcularPuntosBanca();
        }

        ArrayList<String> nombresGanadores = new ArrayList<>();
        for (int i = 0; i < jugadoresEnMesa.size(); i++) {
            Jugador j = jugadoresEnMesa.get(i);
            if (j != null) {
                double apuesta = apuestasEnMesa.get(i);
                String res;
                if (j.isEliminado() || (puntosBanca <= 21 && j.getPuntuacionMano() < puntosBanca)) {
                    res = "Perdió";
                } else if (j.getPuntuacionMano() == puntosBanca) {
                    res = "Empate";
                    j.setSaldo(j.getSaldo() + apuesta);
                } else {
                    res = "Ganó";
                    j.setSaldo(j.getSaldo() + (apuesta * 2));
                    nombresGanadores.add(j.getNombre());
                }
                modelo.actualizarSaldo(j.getIdJugador(), j.getSaldo());
                modelo.insertarHistorial(new HistorialJugada(0, idPartidaActual, j.getIdJugador(), apuesta, res));
            }
        }

        String textoGanadores = nombresGanadores.isEmpty() ? "Banca" : String.join(", ", nombresGanadores);
        modelo.actualizarGanadorPartida(idPartidaActual, textoGanadores);
        actualizarInterfaz();
        JOptionPane.showMessageDialog(vista, "Ronda finalizada. Ganador(es): " + textoGanadores);
        setEstadoBotones(true, false);
    }

    /**
     * Calcula la puntuación actual de la banca.
     * @return Puntuación total.
     */
    private int calcularPuntosBanca() {
        int total = 0, as = 0;
        for (int c : manoBanca) {
            total += c;
            if (c == 11) as++;
        }
        while (total > 21 && as > 0) { total -= 10; as--; }
        return total;
    }

    /**
     * Procesa la selección de un asiento para identificar al jugador.
     * @param combo ComboBox del asiento.
     * @return Objeto Jugador asociado o null si está vacío.
     */
    private Jugador procesarAsiento(javax.swing.JComboBox combo) {
        String sel = (String) combo.getSelectedItem();
        if (sel == null || sel.equals("--- VACÍO ---")) return null;
        int id = Integer.parseInt(sel.split(" - ")[0]);
        for (Jugador j : modelo.obtenerJugadores()) {
            if (j.getIdJugador() == id) return j;
        }
        return null;
    }

    /**
     * Limpia los datos de la mesa para reiniciar el estado de la partida.
     */
    public void limpiarMesa() {
        jugadoresEnMesa.clear();
        apuestasEnMesa.clear();
        indiceTurno = 0;
        manoBanca.clear();
        vista.lblMensajeTurno.setText("Mesa reiniciada");
    }
}