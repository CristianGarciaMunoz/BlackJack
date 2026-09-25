# BlackJack
Blackjack Java System
¡Hola! Este es mi proyecto de una aplicación de escritorio desarrollada íntegramente en Java, diseñada bajo estrictos estándares de arquitectura de software y con una interfaz gráfica temática muy cuidada.

Sobre el proyecto
El objetivo principal de este desarrollo ha sido aplicar de forma práctica el patrón de arquitectura Modelo-Vista-Controlador (MVC), garantizando un desacoplamiento total entre la interfaz visual, la gestión de datos y las reglas de negocio del juego.

La aplicación simula una mesa de casino real de Blackjack, permitiendo gestionar múltiples asientos de jugadores simultáneamente, controlar saldos, aplicar restricciones de apuestas y ejecutar la lógica automatizada de la banca.

Características principales
Arquitectura MVC Limpia: Paquetes estrictamente separados (Controlador, Modelo, Vista, mvc) para asegurar la mantenibilidad y escalabilidad del código.

Interfaz Gráfica Avanzada (Java Swing): Uso extensivo de componentes personalizados, paneles con capas (JLayeredPane) para fondos temáticos y campos de texto transparentes integrados en el diseño visual de la mesa.

Sistema de Gestión de Usuarios: Capacidad de registrar jugadores distinguiendo entre cuentas Estándar y perfiles VIP, con actualización dinámica de la interfaz y los menús.

Motor de Blackjack Robusto: Control completo del flujo de turnos, conteo dinámico de puntos (con gestión inteligente de ases), validación de apuestas en tiempo real y cálculo de resultados (ganador, empate o derrota).

Persistencia de Datos: Conexión a base de datos relacional para el almacenamiento seguro de historiales de partidas y control financiero de los saldos de los usuarios.

Tecnologías utilizadas
Lenguaje: Java SE

Interfaz Gráfica: Java Swing / AWT / AbsoluteLayout

Arquitectura: Patrón MVC

Base de Datos: JDBC / SQL

Control de Versiones: Maven

Cómo ponerlo en marcha
1. Clonar el repositorio: git clone https://github.com/CristianGarciaMunoz/BlackJack.git 
2. Configurar la Base de Datos: Importa el esquema SQL en tu gestor de base de datos y actualiza las credenciales de acceso en la clase correspondiente del paquete Modelo.
3. Ejecutar el proyecto: Abre el proyecto en tu IDE (como NetBeans), asegúrate de que el pom.xml reconozca las dependencias y ejecuta la clase principal ubicada en mvc.Blackjack.

Autor
Desarrollado con dedicación por Cristian García Muñoz. ¡Cualquier sugerencia, mejora o pull request es bienvenida!
