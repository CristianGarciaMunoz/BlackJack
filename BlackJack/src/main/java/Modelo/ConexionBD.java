package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que crea la conexión con la base de datos
 * 
 * @author Cristiangm05
 * @version 1.1 02/06/2026
 */
public class ConexionBD {
    private final String bd = "blackjack_db";
    private final String user = "root";
    private final String password = ""; 
    
    private final String url = "jdbc:mysql://localhost/" + bd + "?serverTimezone=UTC";
    private Connection conexion = null;

    /**
     * Se encarga de crear la conexión para devolverla, y controlar posibles errores con la conexión
     * 
     * @return Devuelve la conexión con la base de datos
     * @since 1.1
     * @throws <code>SQLException</code> Controla que la conexión a la base de datos sea exitosa
     */
    public Connection getConexion() {
        try {
            conexion = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            System.err.println("Error de conexión a la base de datos: " + e.getMessage());
        }
        return conexion;
    }
}