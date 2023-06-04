/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controls;

import com.gf.dao.Dao;
import com.gf.models.Obras;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo Martin-Sonseca y Mario Ortuñez
 *
 * Clase del primer juego
 * 
 * Enunciado:
 * 1. ¿Quién lo hizo? A partir de unas imágenes de obras de arte genéricas, hay
 * que adivinar su autor
 *
 */
public class Juego1 {

    // Atributos
    private List<Obras> obras = new ArrayList<>();// Variable que guarda las obras que apareceran en el juego

    // Constructor
    public Juego1(Dao dao) {

        // Variables
        List<Integer> aleatoriosSel = new ArrayList<>();// Variable para que guarde las ids de las obras que ya hayan sido seleccionadas
        int aleatorio = 0;// Variable para sacra una numero aleatorio de las obras que haya

        // Instrucciones
        for (int i = 0; i < 10; i++) {// Bucle for para rellenar con las 10 obras
            do {// Bucle do while para hacer que repita la accion de sacar una aleatorio mientras no se haya escogido antes
                aleatorio = (int) (Math.random() * (dao.getObras().size() - 1) + 1);
            } while (ControlJuegos.idRepetida(aleatoriosSel, aleatorio));// LLamamos al metodo idRepetido que devuelve un voleano si esta repetido(true si lo esta)
            this.obras.add(dao.getObras().get(aleatorio));// si no lo esta lo añadimos a las obras
            aleatoriosSel.add(aleatorio);// Y guardanmos el aleatorio
        }

    }

    // Getter de las obras
    public List<Obras> getObras() {
        return obras;
    }
    
    // Metodo que devuelve el nombre de las obras
    public List<String> nombreImg() {

        // Variables
        List<String> nombres = new ArrayList<>();// Variable para la almacenacion de los nombres

        // Instrucciones
        for (int i = 0; i < obras.size(); i++) {// Bucle for para recorrer todas las obras
            nombres.add(obras.get(i).getNombre_obra());// Las guardamos en la lista de nombres
        }

        return nombres;// Retornamos la lista de los nombres
    }

    // Metodo que devuelve el nombre de las obras
    public List<String> urlImg() {

        // Variables
        List<String> urls = new ArrayList<>();// Variable para la almacenacion de las urls

        // Instrucciones
        for (int i = 0; i < obras.size(); i++) {// Bucle for para recorrer todas las obras
            urls.add(obras.get(i).getDescripcion_obra());// Las guardamos en la lista de urls
        }

        return urls;// Retornamos la lista de los urls
    }
}
