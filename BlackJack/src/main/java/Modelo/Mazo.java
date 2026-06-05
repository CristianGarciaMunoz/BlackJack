package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Representa la baraja de cartas
 * 
 * @author Cristiangm05
 * @version 1.0 02/06/2026
 */
public class Mazo {
    private Stack<Integer> cartas;

    /**
     * Constructor por defecto que inicializa la pila y se apoya en el 
     * método <code>inicializarMazo()</code> para rellenar la pila de cartas
     */
    public Mazo() {
        cartas = new Stack<>();
        inicializarMazo();
    }

    /**
     * Crea una lista para agregar todos los valores de las cartas, la baraja y 
     * por último agrega los valores de la lista a la lista original de cartas
     * @since 1.0
     */
    public final void inicializarMazo() {
        cartas.clear();
        
        // Lista temporal para poder barajar con Collections
        List<Integer> listaTemporal = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            listaTemporal.add(11);
            for (int j = 2; j <= 10; j++) {
                listaTemporal.add(j);
            }
            for (int j = 0; j < 3; j++) {
                listaTemporal.add(10);
            }
        }

        Collections.shuffle(listaTemporal);

        for (int carta : listaTemporal) {
            cartas.push(carta);
        }
    }

    /**
     * Coge una carta de la cima de la pila, y si la pila ya está vacía, la vuelve a barajar con el método <code>inicializarMazo()</code>
     * @return Devuelve el valor de la carta que cogió
     * @since 1.0
     */
    public int robarCarta() {
        if (cartas.isEmpty()) {
            inicializarMazo();
        }
        return cartas.pop();
    }

    /**
     * Cuenta la cantidad de cartas que tiene la baraja
     * 
     * @return Devuelve el número de cartas que tiene la baraja
     * @since 1.0
     */
    public int cantidadCartas() {
        return cartas.size();
    }
}