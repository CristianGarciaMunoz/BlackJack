package Controlador;

import Modelo.ConsultasBD;
import Vista.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador principal que gestiona la navegación desde el menú de la aplicación.
 * Centraliza la transición entre las diferentes pantallas del juego y la salida del sistema.
 * * @author Cristiangm05
 */
public class ControladorMenu implements ActionListener {

    private final MenuPrincipal vista;
    private final ConsultasBD modelo;

    /**
     * Constructor que inicializa el controlador, asigna los listeners a los botones
     * de navegación y establece la relación con el modelo de datos.
     * * @param vista  Instancia de la ventana MenuPrincipal.
     * @param modelo Instancia de ConsultasBD necesaria para la navegación entre módulos.
     */
    public ControladorMenu(MenuPrincipal vista, ConsultasBD modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btnJugar.addActionListener(this);
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnHistorial.addActionListener(this);
        this.vista.btnEliminarJugador.addActionListener(this);
        this.vista.btnSalirJuego.addActionListener(this);
        this.vista.btnRegistrarVIP.addActionListener(this);
    }

    /**
     * Hace visible la ventana del menú principal al usuario.
     */
    public void iniciar() {
        vista.setVisible(true);
    }

    /**
     * Gestiona las acciones de navegación disparadas por los botones del menú.
     * Cierra la ventana actual y delega la apertura de la nueva ventana al GestorNavegacion.
     * * @param e Evento de acción capturado desde la interfaz.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnJugar) {
            vista.dispose();
            GestorNavegacion.irAMesaJuego(modelo);
        }

        if (e.getSource() == vista.btnRegistrar) {
            vista.dispose();
            GestorNavegacion.irARegistro(modelo, false);
        }

        if (e.getSource() == vista.btnHistorial) {
            vista.dispose();
            GestorNavegacion.irAHistorial(modelo);
        }

        if (e.getSource() == vista.btnEliminarJugador) {
            vista.dispose();
            GestorNavegacion.irAEliminar(modelo);
        }

        if (e.getSource() == vista.btnSalirJuego) {
            GestorNavegacion.salirJuego(vista, modelo);
        }

        if (e.getSource() == vista.btnRegistrarVIP) {
            vista.dispose();
            GestorNavegacion.irARegistro(modelo, true);
        }
    }
}