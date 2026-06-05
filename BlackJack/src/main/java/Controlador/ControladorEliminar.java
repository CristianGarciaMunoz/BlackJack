package Controlador;

import Modelo.*;
import Vista.EliminarJugador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Controlador para gestionar la eliminación de jugadores en la interfaz.
 * Gestiona la carga de datos en el combo box y la interacción de borrado en la base de datos.
 */
public class ControladorEliminar implements ActionListener {

    private final EliminarJugador vista;
    private final ConsultasBD modelo;

    /**
     * Constructor que inicializa el controlador, asigna los listeners y carga la lista de jugadores.
     * 
     * @param vista  Instancia de la vista EliminarJugador.
     * @param modelo Instancia de ConsultasBD para las operaciones de base de datos.
     */
    public ControladorEliminar(EliminarJugador vista, ConsultasBD modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btnBorrar.addActionListener(this);
        this.vista.btnCerrar.addActionListener(this);
        
        cargarComboJugadores();
    }

    /**
     * Obtiene los jugadores desde el modelo y actualiza el componente JComboBox de la vista.
     */
    private void cargarComboJugadores() {
        ArrayList<Jugador> lista = modelo.obtenerJugadores();
        vista.comboEliminar.removeAllItems();
        vista.comboEliminar.addItem("--- SELECCIONE JUGADOR ---");
        for (Jugador j : lista) {
            vista.comboEliminar.addItem(j.getIdJugador() + " - " + j.getNombre());
        }
    }

    /**
     * Gestiona los eventos de clic en los botones de la vista.
     * * @param e El evento de acción generado por los componentes.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBorrar) {
            eliminarJugador();
        } else if (e.getSource() == vista.btnCerrar) {
            vista.dispose();
            GestorNavegacion.irAlMenu(modelo);
        }
    }

    /**
     * Procesa la eliminación de un jugador tras validar la selección y confirmar la acción del usuario.
     */
    private void eliminarJugador() {
        String seleccion = (String) vista.comboEliminar.getSelectedItem();
        
        if (seleccion == null || seleccion.equals("--- SELECCIONE JUGADOR ---")) {
            JOptionPane.showMessageDialog(vista, "Por favor, selecciona un jugador válido primero.");
            return;
        }

        try {
            int id = Integer.parseInt(seleccion.split(" - ")[0]);
            String nombre = seleccion.split(" - ")[1];

            int opcion = JOptionPane.showConfirmDialog(vista, 
                "¿Estás seguro de que quieres eliminar a " + nombre + "?\nEsta acción es irreversible.", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);

            if (opcion == JOptionPane.YES_OPTION) {
                if (modelo.eliminarJugador(id)) {
                    JOptionPane.showMessageDialog(vista, "Jugador eliminado correctamente.");
                    cargarComboJugadores(); // Refrescamos la lista automáticamente
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al conectar con la base de datos.");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al procesar la selección.");
            ex.printStackTrace();
        }
    }
    
    /**
     * Hace visible la ventana de eliminación.
     */
    public void iniciar() {
        vista.setVisible(true);
    }
}