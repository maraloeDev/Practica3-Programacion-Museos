/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controles;

import com.gf.dao.Dao;
import com.gf.modelos.Museos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario Ortuñez
 *
 * Clase del segundo juego
 *
 * Enunciado: 2. Verdadero/Falso de Museos Se mostrará una lista de nombres de
 * museos y el usuario tiene que decir si existen o no.
 *
 * 
 * Logica: Al igual que en el primer juego, en la clase Juego2 almacenamos los
 * museos del juego en una lista y otra lista para almacenar los museos que sean
 * verdaderos. Estas listas se rellenan en el constructor al crear el objeto.
 *
 * En el constructor, se separan los museos en dos listas: los museos falsos y
 * los museos verdaderos. Luego, se utiliza un bucle para seleccionar 10 museos
 * para el juego. Se genera un número aleatorio y se decide si el museo que se
 * va a añadir será verdadero (0) o falso (1). Además, se tiene en cuenta que
 * solo puede haber tres museos verdaderos, por lo que se verifica que el
 * contador de museos verdaderos no sea mayor que 3. Después, se elige
 * aleatoriamente entre los museos verdaderos o falsos que se encuentran en las
 * listas y se añade a la lista de museos del juego.
 *
 * Además de esto, la clase Juego2 proporciona otros métodos, como getMuseos y
 * getMuseosExistentes, que devuelven la lista completa de museos y la lista de
 * museos verdaderos, respectivamente. También hay un método para obtener los
 * nombres de todos los museos.
 *
 */
public class Juego2 {

    private final List<Museos> museos = new ArrayList<>(); // Variable que guarda los museos del juego
    private final List<Museos> museosExistentes = new ArrayList<>(); // Variable que guarda los museos que son verdaderos

    public Juego2(Dao dao) {
        List<Museos> museosFalsos = new ArrayList<>(); // Variable para almacenar los museos falsos
        List<Museos> museosVerdaderos = new ArrayList<>(); // Variable para almacenar los museos verdaderos
        int contMuseosReal = 0; // Contador de museos verdaderos

        // Separar los museos en verdaderos y falsos
        for (Museos museo : dao.getMuseos()) {
            if (museo.isExiste_museo()) {
                museosVerdaderos.add(museo);
            } else {
                museosFalsos.add(museo);
            }
        }

        // Generar los 10 museos del juego
        for (int i = 0; i < 10; i++) {
            int aleatorio = (int) (Math.random() * 2); // Generar 0 o 1 para decidir si el museo será verdadero o falso

            if (aleatorio == 0 || contMuseosReal <= 3) { // Si aleatorio es 0 o hay 3 o menos museos verdaderos
                Museos museoSeleccionado = obtenerMuseoAleatorioSinRepetir(museosVerdaderos);
                museos.add(museoSeleccionado);
                museosExistentes.add(museoSeleccionado);
                contMuseosReal++;
            } else { // Si aleatorio es 1
                museos.add(obtenerMuseoAleatorioSinRepetir(museosFalsos));
            }
        }
    }

    public List<Museos> getMuseos() {
        return museos;
    }

    public List<Museos> getMuseosExistentes() {
        return museosExistentes;
    }

    public List<String> getNombresMuseos() {
        List<String> nombres = new ArrayList<>();
        for (Museos museo : museos) {
            nombres.add(museo.getNombre_museo());
        }
        return nombres;
    }

    private Museos obtenerMuseoAleatorioSinRepetir(List<Museos> museos) {
        int aleatorio;
        do {
            aleatorio = (int) (Math.random() * museos.size());
        } while (museos.get(aleatorio) == null);
        Museos museoSeleccionado = museos.get(aleatorio);
        museos.set(aleatorio, null); // Marcamos el museo seleccionado como nulo para evitar seleccionarlo nuevamente
        return museoSeleccionado;
    }
}
