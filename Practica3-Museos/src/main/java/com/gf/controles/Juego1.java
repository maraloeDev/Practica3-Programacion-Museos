/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controles;

import com.gf.dao.Dao;
import com.gf.modeles.Obras;
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
 * Lógica:
 * Tenemos una variable que nos guardara una lista de las obras que apareceran en el jueg.
 * 
 * Para rellenarla segun se crea el objeto (por eso es crea da en el constructor) recorro un 
 * for 10 veces (debido a que el jeugo tiene 10 obras distintas),en el saco un numero aleatorio 
 * entre 1 y el numero total de obras guardadas, y despues de comprobar que no fue seleccionado
 * ese numero antes (para que no se repita), con ese numero cogemos la obra que caiga segun se 
 * guardada, con el indice siendo el numero aleatorio, y finalmente la añadimos a la lista.
 * 
 * Una vez con la lista llena, tengo metodos para retornar la propia lista, el nombre de las
 * obras y las urls de las imagenes de las obras.
 * 
 * Estos dos ultimos metodos son parecidos, debido a que recojo cada nombre o url en una lista y 
 * retorno esta
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

        // Variable
        List<String> nombres = new ArrayList<>();// Variable para la almacenacion de los nombres

        // Instrucciones
        for (int i = 0; i < obras.size(); i++) {// Bucle for para recorrer todas las obras
            nombres.add(obras.get(i).getNombre_obra());// Las guardamos en la lista de nombres
        }

        return nombres;// Retornamos la lista de los nombres
    }

    // Metodo que devuelve el nombre de las obras
    public List<String> urlImg() {

        // Variable
        List<String> urls = new ArrayList<>();// Variable para la almacenacion de las urls

        // Instrucciones
        for (int i = 0; i < obras.size(); i++) {// Bucle for para recorrer todas las obras
            urls.add(obras.get(i).getDescripcion_obra());// Las guardamos en la lista de urls
        }

        return urls;// Retornamos la lista de los urls
    }
}
