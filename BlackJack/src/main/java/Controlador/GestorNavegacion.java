package Controlador;

import Modelo.ConsultasBD;
import Vista.*;
import javax.swing.JOptionPane;

/**
 * Clase de utilidad encargada de gestionar la navegación centralizada entre las
 * distintas pantallas de la aplicación y la gestión de recursos del juego.
 * Implementa el patrón de navegación para desacoplar las vistas entre sí.
 */
public class GestorNavegacion {

    /**
     * Instancia la vista del Menú Principal y su controlador, haciéndolos visibles.
     * @param modelo Instancia de ConsultasBD compartida en la aplicación.
     */
    public static void irAlMenu(ConsultasBD modelo) {
        MenuPrincipal vista = new MenuPrincipal();
        new ControladorMenu(vista, modelo).iniciar();
    }

    /**
     * Instancia la vista de la Mesa de Juego y su controlador.
     * @param modelo Instancia de ConsultasBD compartida en la aplicación.
     */
    public static void irAMesaJuego(ConsultasBD modelo) {
        MesaJuego vista = new MesaJuego();
        new ControladorMesa(vista, modelo).iniciar();
    }

    /**
     * Instancia la vista de Registro y su controlador, configurando el tipo de cuenta.
     * @param modelo Instancia de ConsultasBD compartida en la aplicación.
     * @param vip    Booleano que indica si se accede en modo de registro VIP.
     */
    public static void irARegistro(ConsultasBD modelo, boolean vip) {
        RegistrarJugador vista = new RegistrarJugador();
        new ControladorRegistro(vista, modelo, vip).iniciar();
    }

    /**
     * Instancia la vista de Historial de Partidas y su controlador.
     * @param modelo Instancia de ConsultasBD compartida en la aplicación.
     */
    public static void irAHistorial(ConsultasBD modelo) {
        HistorialPartidas vista = new HistorialPartidas();
        new ControladorHistorial(vista, modelo).iniciar();
    }

    /**
     * Instancia la vista para eliminar jugadores y su controlador.
     * @param modelo Instancia de ConsultasBD compartida en la aplicación.
     */
    public static void irAEliminar(ConsultasBD modelo) {
        EliminarJugador vistaEliminar = new EliminarJugador();
        new ControladorEliminar(vistaEliminar, modelo).iniciar();
    }

    /**
     * Muestra un diálogo de confirmación para cerrar la aplicación.
     * @param vista  Referencia a la vista actual para centrar el diálogo.
     * @param modelo Instancia de ConsultasBD (para consistencia en la llamada).
     */
    public static void salirJuego(MenuPrincipal vista, ConsultasBD modelo) {
        int confirmacion = JOptionPane.showConfirmDialog(vista,
                "¿Estás seguro de que quieres cerrar el casino?",
                "Salir del juego",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}