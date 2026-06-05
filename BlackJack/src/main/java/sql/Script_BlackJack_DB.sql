/**
 * Author:  Cristiangm05
 * Created: 29 may 2026
 */

-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS blackjack_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE blackjack_db;

-- 1. Tabla Jugador
CREATE TABLE IF NOT EXISTS JUGADOR (
    id_jugador INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    saldo DECIMAL(10,2) DEFAULT 100.00,
    es_vip BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id_jugador)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- 2. Tabla Partida
CREATE TABLE IF NOT EXISTS PARTIDA (
    id_partida INT AUTO_INCREMENT,
    fecha_partida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ganador VARCHAR(50) DEFAULT 'Banca',
    PRIMARY KEY (id_partida)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

-- 3. Tabla Historial
CREATE TABLE IF NOT EXISTS HISTORIAL_JUGADA (
    id_historial INT AUTO_INCREMENT,
    id_partida INT,
    id_jugador INT,
    apuesta DECIMAL(10,2) NOT NULL,
    resultado VARCHAR(20),
    PRIMARY KEY (id_historial),
    FOREIGN KEY (id_partida) REFERENCES PARTIDA(id_partida) ON DELETE CASCADE,
    FOREIGN KEY (id_jugador) REFERENCES JUGADOR(id_jugador) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;