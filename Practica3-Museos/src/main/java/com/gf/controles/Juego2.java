/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controles;

import com.gf.dao.Dao;
import com.gf.modelos.Museos;
import java.util.ArrayList;
import java.util.Collections;
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
 * En el constructor, guardamos todos los museos reales, y todos los museos
 * falsos desordenamos, y añadimos con su respectiva cantidad (3 reales/ 7
 * falsos), y desordenamos
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
//        int nMuseosReales = 3; // Contador de museos verdaderos
//        int nMuseosNoReales = 7; // Contador de museos falsos

        // Separar los museos en verdaderos y falsos
        for (Museos museo : dao.getMuseos()) {
            if (museo.isExiste_museo()) {
                museosVerdaderos.add(museo);
            } else {
                museosFalsos.add(museo);
            }
        }

        //Desordenamos las listas
        Collections.shuffle(museosFalsos);
        Collections.shuffle(museosVerdaderos);
        
        /*Se tomará la cantidad mínima entre 3 y el tamaño de museosVerdaderos, evitando así el error si hay menos de 3 museos verdaderos.*/
        museos.addAll(museosVerdaderos.subList(0, Math.min(3, museosVerdaderos.size())));
        museosExistentes.addAll(museosVerdaderos.subList(0, Math.min(3, museosVerdaderos.size())));
        museos.addAll(museosFalsos.subList(0, 7));

        Collections.shuffle(museos);

//        //Añadimos los museos
//        for (int i = 0; i < 10; i++) {
//    museos.add(museosVerdaderos.get(i));
//    museosExistentes.add(museosVerdaderos.get(i));
//}
//
//for (int j = 0; j < 7; j++) {
//    museos.add(museosFalsos.get(j));
//}
//
//
//        Collections.shuffle(museos);// Y desordenamos
//
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
}
