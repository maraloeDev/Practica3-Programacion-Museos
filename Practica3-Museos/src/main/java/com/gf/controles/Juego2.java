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
 * Logica:
 * Al igual que el primer juego, almacenamos los muesos del juego en una lista y
 * otra para almacenar los museos que sean reales.
 * 
 * Tambien la relleno en el constructor para hacer segun se crea el objeto, entonces,
 * primero con un for separo en dos listas los museos falsos, y los museos verdaderos,
 * y segundo, con ptro for con 10 de limite, saco con un random para decidir si el 
 * museo que añado sera de los verdaderos, dando 0, o falsos, sale 1, tambien tengo 
 * en cuenta que solo hay tres reales, por lo que otra condicion en el if es que este
 * no sea mayor de 3, para que el resto sean falsos, y tengo en cuenta que al final
 * de añadir un museo real aumento el contador.Por ultimo, dentro del if elijo aleatoriamente
 * entre los museos verdaderos o falsos, guardados en las listas.
 * 
 * Otros metodos a parte son la utilizacion del getMuseos, y getMuseosExistentes 
 * (con los museos que son reales de la lista completa)y otro metodo para retornar el 
 * nombre de todos los museos.
 * 
 */
public class Juego2 {

    // Atributos
    private List<Museos> museos = new ArrayList<>();// Variable que guarde los museos del juego
    private List<Museos> museosExistentes = new ArrayList<>();// Variable que guarde los museos que sean los verdaderos

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
                this.museosExistentes.add(museosVerdaderosLista.get(aleatorios));// Y como existe lo guardo tambien en la lista de los que existen
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

    // Getter de los museos que esxistan
    public List<Museos> getmuseosExistentes() {
        return museosExistentes;
    }
    
    // Metodo para sacar el nombre de los museos
    public List<String> nombreMuseo(Dao dao) {
        
        // Variable
        List<String> nombres = new ArrayList<>();// Varibale donde se guardan los nombres de los museos

        // Instruciones
        for (int i = 0; i < museos.size(); i++) {// Bucle for para recorrer todos los museos
            nombres.add(museos.get(i).getNombre_museo());// Guardamos los nombres en la lista
        }

        return nombres;// Retornamos la lista de los nombres
    }
    
}
