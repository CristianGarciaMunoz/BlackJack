package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase que extiende de <code>ConexionBD</code> para hacer las consultas sobre
 * la base de datos
 *
 * @author Cristiangm05
 * @version 1.0 02/06/2026
 */
public class ConsultasBD extends ConexionBD {

    /**
     * Realiza un insert en la base de datos con los datos del jugador pasado
     * por parámetros
     *
     * @param j Rpresenta al <code>Jugador</code> al que se registra en la base
     * de datos
     * @throws <code>SQLException</code> Controla posibles errores con la base
     * de datos
     * @return Devuelve <code>true</code> en el caso de que se haya registrado
     * el jugador en la base de datos de manera exitosa y <code>false</code> en
     * caso de error al registrar en la base de datos al jugador
     * @since 1.0
     */
    public boolean registrarJugador(Jugador j) {
        String sql = "INSERT INTO JUGADOR (nombre, saldo, es_vip) VALUES (?, ?, ?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, j.getNombre());
            ps.setDouble(2, j.getSaldo());
            ps.setBoolean(3, j.isEsVip());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Realiza una select en la base de datos para recuperar a los jugadores
     * existentes y agregarlos a la lista que se devuelve
     *
     * @return Devuelve un Array de tipo <code>Jugador</code> con los jugadores
     * existentes en la base de datos
     * @since 1.0
     * @throws <code>SQLException</code> Controla que la conexión a la base de
     * datos sea exitosa
     */
    public ArrayList<Jugador> obtenerJugadores() {
        ArrayList<Jugador> lista = new ArrayList<>();
        Connection conexion = getConexion();
        String sql = "SELECT * FROM JUGADOR";

        try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Jugador j = new Jugador();
                j.setIdJugador(rs.getInt(1));
                j.setNombre(rs.getString(2));
                j.setSaldo(rs.getDouble(3));
                j.setEsVip(rs.getBoolean(4));
                lista.add(j);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener jugadores: " + e.getMessage());
        }
        return lista;
    }

    /**
     *
     *
     * @param idJugador Representa el ID del jugador al que le afecta el cambio
     * de saldo
     * @param nuevoSaldo Representa el nuevo saldo que se actualizará del
     * jugador
     * @return Devuelve <code>true</code> si todo funcionó correctamente, y
     * <code>false</code> si existe algún error con la base de datos
     * @since 1.0
     * @throws <code>SQLException</code> Controla que la conexión a la base de
     * datos sea exitosa
     */
    public boolean actualizarSaldo(int idJugador, double nuevoSaldo) {
        Connection con = getConexion();
        String sql = "UPDATE JUGADOR SET saldo = ? WHERE id_jugador = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, nuevoSaldo);
            ps.setInt(2, idJugador);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar saldo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Realiza el insert en la tabla <code>Partida</code> y recoge el ID que se
     * generó automáticamente para esa partida en la base de datos
     *
     * @return Realiza el insert en la tabla partida y evuelve el ID que se
     * insertó en ese insert
     * @since 1.0
     * @throws <code>SQLException</code> Controla que la conexión a la base de datos sea exitosa
     */
    public int insertarPartida() {
        Connection con = getConexion();
        String sql = "INSERT INTO PARTIDA (ganador) VALUES (DEFAULT)";
        int idGenerado = -1;

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al crear partida en la BD: " + e.getMessage());
        }
        return idGenerado;
    }

    /**
     * Registra una nueva jugada en el historial de la base de datos.
     *
     * @param hj Objeto de tipo HistorialJugada que contiene los datos de la partida, el jugador, la apuesta y el resultado obtenido.
     * @return Devuelve <code>true</code> si el insert se realizó correctamente y <code>false</code> si ocurrió un error en la base de datos.
     * @see HistorialJugada
     * @since 1.0
     * @throws <code>SQLException</code> Controla que la conexión a la base de datos sea exitosa
     */
    public boolean insertarHistorial(HistorialJugada hj) {
        Connection con = getConexion();
        String sql = "INSERT INTO HISTORIAL_JUGADA (id_partida, id_jugador, apuesta, resultado) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, hj.getIdPartida());
            ps.setInt(2, hj.getIdJugador());
            ps.setDouble(3, hj.getApuesta());
            ps.setString(4, hj.getResultado());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar historial: " + e.getMessage());
            return false;
        }
    }

    /**
     * Realiza una select para rellenar la tabla de la vista <code>HistorialPartidas</code>
     * 
     * @return Devuelve un ArrayList con las filas que debe añadir a la tabla
     * @since 1.0
     * @see HistorialPartidas
     */
    public ArrayList<Object[]> obtenerHistorialTabla() {
        ArrayList<Object[]> lista = new ArrayList<>();
        Connection con = getConexion();

        // Select que recoge datos de las tablas PARTIDA e HISTORIAL_JUGADA para rellenar los campos de las filas
        String sql = "SELECT p.id_partida, p.fecha_partida, j.nombre, h.apuesta, h.resultado "
                + "FROM PARTIDA p "
                + "INNER JOIN HISTORIAL_JUGADA h ON p.id_partida = h.id_partida "
                + "INNER JOIN JUGADOR j ON h.id_jugador = j.id_jugador "
                + "ORDER BY p.id_partida DESC";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getDouble(4) + "€";
                fila[4] = rs.getString(5);
                lista.add(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al mapear la tabla de historial: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Cuenta con una select las filas que existen en la tabla <code>PARTIDA</code> para obtener el número de partidas totales
     * 
     * @return Devuelve el número de partidas registradas en la base de datos
     * @since 1.0
     * 
     */
    public int obtenerTotalPartidas() {
        Connection con = getConexion();
        String sql = "SELECT COUNT(*) AS total FROM PARTIDA";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Error al contar partidas: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Recoge el máximo de la columna de apuestas de la tabla <code>HISTORIAL_JUGADA</code> para devolver el valor de la apuesta más grande
     * 
     * @return Devuelve la cifra de la apuesta más alta que se realizó
     * @since 1.0
     */
    public double obtenerApuestaMaxima() {
        Connection con = getConexion();
        String sql = "SELECT MAX(apuesta) AS max_apuesta FROM HISTORIAL_JUGADA";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("max_apuesta");
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar la apuesta máxima: " + e.getMessage());
        }
        return 0.0;
    }

    /**
     * Actualiza la fila de la tabla <code>HISTORIAL_PARTIDA</code> con el ganador original
     * 
     * @param idPartida Representa el ID de de la partida que hay que actualizar
     * @param ganador Representa el nombre del ganador de la partida
     * @since 1.0
     */
    public void actualizarGanadorPartida(int idPartida, String ganador) {
        Connection con = getConexion();
        String sql = "UPDATE PARTIDA SET ganador = ? WHERE id_partida = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ganador);
            ps.setInt(2, idPartida);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el ganador de la partida: " + e.getMessage());
        }
    }

    /**
     * Elimina al jugador con el ID que se pase por parámetros
     * 
     * @param idJugador Representa el ID del jugador al que se va a eliminar
     * @return Devuelve <code>true</code> si se elimina al jugador y <code>false</code> si no se eliminó, 
     * ya sea porque no existe ninguno con ese ID o por otro error relacionado con la base de datos
     * @since 1.0
     */
    public boolean eliminarJugador(int idJugador) {
        String sql = "DELETE FROM JUGADOR WHERE id_jugador = ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, idJugador);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar jugador: " + e.getMessage());
            return false;
        }
    }
}
