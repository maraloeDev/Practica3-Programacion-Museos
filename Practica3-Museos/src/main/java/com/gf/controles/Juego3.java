/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controles;

import com.gf.dao.Dao;
import com.gf.modelos.Autores;
import com.gf.modelos.Obras;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mario Ortuñez
 *
 * 3. Gregorio Fernández. El usuario tiene que adivinar cual de dos imágenes de
 * esculturas planteadas, corresponde su autoría a Gregorio Fernández
 *
 *
 * Lógica: Al igual que en los juegos anteriores, la clase Juego3 utiliza dos
 * listas: una para almacenar todas las obras y otra para las obras del Gregorio
 * Fernández.
 *
 * En el constructor, al igual que el anterior, se hacen dos listas, una para
 * las obras del Gregorio y otras las homologas y finalmente despues de
 * desordenar una lista de numeros, y estos los usaremos como indice para
 * colocar las anteriores lisdtas en la lista del juego
 *
 * La clase Juego3 también proporciona métodos para obtener las obras del juego,
 * las obras del Gregorio Fernández, los nombres de las obras y las URLs de las
 * imágenes de las obras. Además, tenemos un método para determinar si la imagen
 * verdadera estará a la izquierda ('i') o derecha ('d'). Por ultimo el metodo
 * para seleccionar las obras del Gregorio Fernandez
 *
 */
public class Juego3 {

    private final List<Obras> obras = new ArrayList<>(); // Obras seleccionadas para el juego 3
    private final List<Obras> obrasGF = new ArrayList<>(); // Obras del Gregorio Fernández entre las seleccionadas

    public Juego3(Dao dao) throws Exception {
        List<Obras> obrasGFLista = new ArrayList<>(); // Obras del Gregorio Fernández
        List<Obras> obrasNoLista = new ArrayList<>(); // Obras que no son del Gregorio Fernández
        List<Autores> autoresLista = dao.getAutores();
        Autores GF = seleccionarGF(autoresLista); // Buscar al Gregorio Fernández entre los autores
        List<Integer> indicesAleatorios = new ArrayList<>(); // Variable para guardar los indices de la lista aleatorios
        int cantidadObras = 6; // Variable para la cantidad de obras del juego

        // Guardamos segun sean del GF o homonimas
        for (Obras obra : dao.getObras()) {
            if (obra.getId_autor() == GF.getId_autor()) {
                obrasGFLista.add(obra);
                for (Obras obraPareja : dao.getObras()) {
                    if (obraPareja.getNombre_obra().equals(obra.getNombre_obra())) {
                        obrasNoLista.add(obraPareja);
                    }
                }
            }
        }

        //Rellenamos el lista de inidices
        for (int i = 0; i < cantidadObras; i++) {
            indicesAleatorios.add(i);
        }

        // La descolocamos
        Collections.shuffle(indicesAleatorios);

        // Medianet un numero aleatorio decidimos si van ante(izq) o despues(deer) en la lista 
        for (int i = 0; i < cantidadObras; i++) {

            int mezcla = (int) (Math.random() * 2);

            if (mezcla == 0) {
                this.obras.add(obrasGFLista.get(indicesAleatorios.get(i)));
                this.obras.add(obrasNoLista.get(indicesAleatorios.get(i)));
            } else {
                this.obras.add(obrasNoLista.get(indicesAleatorios.get(i)));
                this.obras.add(obrasGFLista.get(indicesAleatorios.get(i)));
            }

            // Guardamos entre las obras que son del GF
            this.obrasGF.add(obrasGFLista.get(indicesAleatorios.get(i)));
        }
    }

    public List<Obras> getObras() {
        return obras;
    }

    public List<Obras> getObrasGF() {
        return obrasGF;
    }

    public List<String> nombreObras() {
        List<String> nombres = new ArrayList<>();

        for (int i = 0; i < obras.size(); i += 2) {
            nombres.add(obras.get(i).getNombre_obra());
        }

        return nombres;
    }

    public List<String> urlObra() {
        List<String> urls = new ArrayList<>();

        for (int i = 0; i < obras.size(); i++) {
            urls.add(obras.get(i).getDescripcion_obra());
        }

        return urls;
    }

    public List<Character> isGF() {
        List<Character> verdaderas = new ArrayList<>();

        for (int i = 0; i < obras.size(); i += 2) {
            if (obras.get(i) == obrasGF.get(i)) {
                verdaderas.add('i');
            } else {
                verdaderas.add('d');
            }
        }

        return verdaderas;
    }

    // Buscar obras del Gregorio Fernandez
    public static Autores seleccionarGF(List<Autores> autoresLista) throws Exception {
        Autores GF = null;
        try {
            for (Autores autor : autoresLista) {
                if (autor.getNombre_autor().equals("Gregorio Fernandez")) {
                    GF = autor;
                    break;
                }
            }
            if (GF == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Algo sucedio y no se pudo encontrar entre los autores a Gregorio Fernandez");
            throw e;
        }
        return GF;
    }
}
