/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controles;

import com.gf.dao.Dao;
import com.gf.modelos.Autores;
import com.gf.modelos.Obras;
import java.util.ArrayList;
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
 * En el constructor, se recorren todas las obras y se separan las obras del
 * Gregorio Fernández y sus obras homónimas. Luego, se seleccionan
 * aleatoriamente las obras para el juego. Se elige una obra aleaotiramente
 * mediante el metodo obtenerObraAleatoriaSinRepetir y se para declarar si la
 * obra del Gregorio Fernández se guardará primero o segundo en cada par de
 * obras seleccionadas.
 *
 * La clase Juego3 también proporciona métodos para obtener las obras del juego,
 * las obras del Gregorio Fernández, los nombres de las obras y las URLs de las
 * imágenes de las obras. Además, tenemos un método para determinar si la imagen
 * verdadera estará a la izquierda ('i') o derecha ('d').
 *
 */
public class Juego3 {

    private final List<Obras> obras = new ArrayList<>(); // Obras seleccionadas para el juego 3
    private final List<Obras> obrasGF = new ArrayList<>(); // Obras del Gregorio Fernández entre las seleccionadas

    public Juego3(Dao dao) throws Exception {
        List<Obras> obrasGFLista = new ArrayList<>(); // Obras del Gregorio Fernández
        List<Obras> obrasNoLista = new ArrayList<>(); // Obras que no son del Gregorio Fernández
        List<Autores> autoresLista = dao.getAutores();
        Autores GF = ControlJuegos.seleccionarGF(autoresLista); // Buscar al Gregorio Fernández entre los autores

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

        List<Integer> aleatoriosSel = new ArrayList<>(); // Obras seleccionadas aleatoriamente
        int aleatorio;
        int mezcla;

        for (int i = 0; i < 6; i++) {
            Obras obraSeleccionada = obtenerObraAleatoriaSinRepetir(obrasGFLista);
            Obras obraHomonima = obtenerObraAleatoriaSinRepetir(obrasNoLista);

            mezcla = (int) (Math.random() * 2);

            if (mezcla == 0) {
                this.obras.add(obraSeleccionada);
                this.obras.add(obraHomonima);
            } else {
                this.obras.add(obraHomonima);
                this.obras.add(obraSeleccionada);
            }

            this.obrasGF.add(obraSeleccionada);
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

    public Obras obtenerObraAleatoriaSinRepetir(List<Obras> obras) {
        int aleatorio;
        do {
            aleatorio = (int) (Math.random() * obras.size());
        } while (obras.get(aleatorio) == null);
        Obras obraSeleccionada = obras.get(aleatorio);
        obras.set(aleatorio, null);
        return obraSeleccionada;
    }
}
