package Controlador;

import Modelo.ConsultasBD;
import Modelo.Jugador;
import Vista.RegistrarJugador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Controlador responsable de la lógica de registro de nuevos jugadores.
 * Maneja la validación de los datos de entrada y la persistencia en el modelo, 
 * diferenciando entre cuentas estándar y VIP.
 * * @author Cristiangm05
 */
public class ControladorRegistro implements ActionListener {

    private final RegistrarJugador vista;
    private final ConsultasBD modelo;
    private boolean esVip;

    /**
     * Constructor que inicializa el controlador, configura el modo de registro (VIP o estándar)
     * y asigna los listeners a los botones de la vista.
     * * @param vista  La interfaz gráfica de registro.
     * @param modelo El objeto de consultas para interactuar con la base de datos.
     * @param vip    Booleano que indica si se está registrando a un jugador VIP.
     */
    public ControladorRegistro(RegistrarJugador vista, ConsultasBD modelo, boolean vip) {
        this.vista = vista;
        this.modelo = modelo;
        this.esVip = vip;

        this.vista.btnGuardarJugador.addActionListener(this);
        this.vista.btnVolverRegistro.addActionListener(this);
        this.vista.configurarModo(esVip);
    }

    /**
     * Hace visible la ventana de registro al usuario.
     */
    public void iniciar() {
        vista.setVisible(true);
    }

    /**
     * Gestiona las acciones de usuario, incluyendo el proceso de guardado y la navegación hacia atrás.
     * * @param e El evento de acción capturado desde la interfaz.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardarJugador) {
            String nombre = vista.txtNombre.getText().trim();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "¡Debes introducir un nombre para el jugador!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double saldoInicial = esVip ? 500.0 : 100.0;

            Jugador nuevoJugador = crearJugador(nombre, saldoInicial);

            boolean exito = modelo.registrarJugador(nuevoJugador);

            if (exito) {
                String tipo = esVip ? "VIP (500€)" : "Estándar (100€)";
                JOptionPane.showMessageDialog(vista, "¡Jugador " + nombre + " registrado con éxito como " + tipo + "!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                vista.txtNombre.setText("");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vista.btnVolverRegistro) {
            vista.dispose();
            GestorNavegacion.irAlMenu(modelo);
        }
    }
    
    /**
     * Helper para instanciar y poblar un objeto Jugador con los datos proporcionados.
     * * @param nombre       El nombre del nuevo jugador.
     * @param saldoInicial El saldo con el que empieza el jugador según su tipo.
     * @return Un objeto Jugador configurado.
     */
    public Jugador crearJugador(String nombre, double saldoInicial) {
            Jugador nuevoJugador = new Jugador();
            nuevoJugador.setNombre(nombre);
            nuevoJugador.setSaldo(saldoInicial);
            nuevoJugador.setEsVip(esVip);
            return nuevoJugador;
    }
}