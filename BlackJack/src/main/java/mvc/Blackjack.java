package mvc;

import Modelo.ConsultasBD;
import Vista.MenuPrincipal;
import Controlador.ControladorMenu;

/**
 * Clase principal de la aplicación.
 * Es el punto de entrada (Main) donde se inicializan el Modelo, la Vista y el 
 * Controlador inicial para arrancar el flujo del programa bajo el patrón MVC.
 * * @author Cristiangm05
 */
public class Blackjack {

    /**
     * Método principal que ejecuta la aplicación.
     * Realiza la inyección de dependencias inicial entre el modelo y la vista 
     * a través del controlador del menú principal.
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Instanciamos la Vista principal y el Modelo de consultas
        MenuPrincipal vistaMenu = new MenuPrincipal();
        ConsultasBD modeloBD = new ConsultasBD();
        
        // El controlador del menú toma el control y arranca la interfaz
        ControladorMenu ctrlMenu = new ControladorMenu(vistaMenu, modeloBD);
        ctrlMenu.iniciar();
    }
}