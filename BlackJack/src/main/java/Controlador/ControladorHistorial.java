package Controlador;

import Modelo.ConsultasBD;
import Vista.HistorialPartidas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 * Controlador encargado de gestionar la visualización del historial de partidas.
 * Se encarga de recuperar los datos desde la base de datos y presentarlos en la vista.
 * * @author Cristiangm05
 */
public class ControladorHistorial implements ActionListener {

    private final HistorialPartidas vista;
    private final ConsultasBD modelo;

    /**
     * Constructor del controlador que inicializa la vista y el modelo.
     * * @param vista  Instancia de la ventana HistorialPartidas.
     * @param modelo Instancia de ConsultasBD para obtener la información de las partidas.
     */
    public ControladorHistorial(HistorialPartidas vista, ConsultasBD modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btnVolverHistorial.addActionListener(this);
    }

    /**
     * Prepara y hace visible la pantalla de historial. 
     * Ejecuta la carga de estadísticas y el llenado de la tabla al iniciar.
     */
    public void iniciar() {
        vista.setVisible(true);
        cargarEstadisticas();
        cargarTabla();
    }

    /**
     * Recupera estadísticas generales del modelo y las asigna a los componentes 
     * de texto (labels) correspondientes en la vista.
     */
    private void cargarEstadisticas() {
        int totalPartidas = modelo.obtenerTotalPartidas();
        double apuestaMax = modelo.obtenerApuestaMaxima();

        vista.lblPartidasTotales.setText("⭐ Partidas Totales Jugadas: " + totalPartidas);
        vista.lblApuestaMax.setText("💰 Mayor Apuesta Registrada: " + apuestaMax + "€");
    }

    /**
     * Limpia la tabla actual de la vista y la repuebla con el historial de partidas 
     * obtenido desde la base de datos.
     */
    private void cargarTabla() {
        DefaultTableModel modeloTabla = vista.getModeloTabla();
        
        modeloTabla.setRowCount(0);

        ArrayList<Object[]> filas = modelo.obtenerHistorialTabla();

        for (Object[] fila : filas) {
            modeloTabla.addRow(fila);
        }
    }

    /**
     * Gestiona las acciones realizadas por el usuario en la interfaz, como cerrar el historial.
     * * @param e Evento de acción capturado desde la vista.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnVolverHistorial) {
            vista.dispose();
            GestorNavegacion.irAlMenu(modelo);
        }
    }
}