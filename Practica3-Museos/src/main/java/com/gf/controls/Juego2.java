/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controls;

import com.gf.dao.Dao;
import com.gf.models.Museos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo Martin-Sonseca Mario Ortuñez
 *
 * Clase del segundo juego
 *
 * Enunciado: 2. Verdadero/Falso de Museos Se mostrará una lista de nombres de
 * museos y el usuario tiene que decir si existen o no.
 *
 */
public class Juego2 {

    // Atributos
    private List<Museos> museos = new ArrayList<>();// Variable que guarde los museos del juego

    // Constructor
    public Juego2(Dao dao) {

        //Variables 
        List<Museos> museosFalsosLista = new ArrayList<>();// Variable para recoger los museos que son falsos
        List<Museos> museosVerdaderosLista = new ArrayList<>();// Variable para recoger los museos que son verdaderos
        int contMuseosReal = 0;// Variable contador para de los museos reales
        int verdadOFalso;// Variable que mediante random se escoge si va ser falso o no
        List<Integer> aleatoriosSel = new ArrayList<>();// Variable para que guarde las ids de las obras que ya hayan sido seleccionadas
        int aleatorios;// Variable que guarda el aleatorio para que añada el museo

        // Instrucciones
        for (Museos museo : dao.getMuseos()) {// Bucle for para recorrer todos los museos que haya
            if (museo.isExiste_museo()) {// Si el museo existe se añade a la lista de existente
                museosVerdaderosLista.add(museo);
            } else {// Si no, se añade a la lista de no existentes
                museosFalsosLista.add(museo);
            }
        }

        for (int i = 0; i < 10; i++) {// Bucle para añadir los 10 museos del juego
            verdadOFalso = (int) (Math.random() * 2);// Sacamos si va a ser falso o no

            if (verdadOFalso == 0 || contMuseosReal <= 3) {// Si sale 0 o hay 3 o menos museos añade un museo real
                do {// Bucle que repita hasta sacar un numero que no se haya seleccionado antes
                    aleatorios = (int) (Math.random() * museosFalsosLista.size());// Sacamos el aleatorio
                } while (ControlJuegos.idRepetida(aleatoriosSel, aleatorios));
                this.museos.add(museosVerdaderosLista.get(aleatorios));// Guardamos el museo
                contMuseosReal++;// Aumentamos el contador de los museos falsos
                aleatoriosSel.add(aleatorios);// Y guardanmos el aleatorio
            } else {// Si no, se añade un museo falso
                do {// Bucle que repita hasta sacar un numero que no se haya seleccionado antes
                    aleatorios = (int) (Math.random() * museosFalsosLista.size());// Sacamos el aleatorio
                } while (ControlJuegos.idRepetida(aleatoriosSel, aleatorios));
                this.museos.add(museosFalsosLista.get(aleatorios));// Guardamos el museo
                aleatoriosSel.add(aleatorios);// Y guardanmos el aleatorio
            }
        }
    }

    // Getter de los museos
    public List<Museos> getMuseos() {
        return museos;
    }

    // Metodo para sacar el nombre de los museos
    public List<String> nombreMuseo(Dao dao) {
        // Variables
        List<String> nombres = new ArrayList<>();// Varibale donde se guardan los nombres de los museos

        // Instrucionrd
        for (int i = 0; i < museos.size(); i++) {// Bucle for para recorrer todos los museos
            nombres.add(museos.get(i).getNombre_museo());// Guardamos los nombres en la lista
        }

        return nombres;// Retornamos la lista de los nombres
    }
}
