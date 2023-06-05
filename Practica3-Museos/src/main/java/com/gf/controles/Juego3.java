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
 * Logica:
 * Parecido a los anteriores tengo dos listas,una con todas las obras, y otra con
 * las obras que sean del Gregorio Fernandez.
 * 
 * Quedar claro, que en las listas de todas las obras, se almacenan de dos en dos,
 * seguidamente.
 * 
 * Lo relleno en el constructor, en este podemos ver que primero con un for saco
 * las obras que son del Gregorio Fernandez, y despues las obras homonimas, con la logica
 * de que tienen el mismo nombre. Entonces, rellenamos las listas. En el segundo for,
 * lo primero que hago es sacar un obra aleatorioamente, y despues mediante mezcla
 * donde da 0 o 1, determino que la obra se guardara primera, aleatoriamente.
 * 
 * Despues tenemos metodos que ya vimos antes de retornar las obras, las obras del GF, y
 * retornar el nombre y urls de las imagenes de las obras.
 * 
 * Por ultimo, cree un metodo que decide si la imagen verdadera estara a la izquierda 
 * (sera 'i')o derecha (sera 'd'), esto lo hago guardandolo en una lista que se rellena
 * sabiendo que van de 2 en 2, si la primera coincide con la de la lista de obras del GF,
 * lo que determina si es del GF o no
 * 
 */
public class Juego3 {

    // Atributos
    private final List<Obras> obras = new ArrayList<>();// Obras que se elegiran en el juego 3
    private final List<Obras> obrasGF = new ArrayList<>();// Obras que seran del Gregorio Fernandez, entre las dos del juego

    // Constructor
    public Juego3(Dao dao) throws Exception {

        // Variables
        List<Obras> obrasGFLista = new ArrayList<>();// Variable que guerda las obras del Gregorio Fernandez
        List<Obras> obrasNoLista = new ArrayList<>();// Variable que guerda las obras que no sean del Gregorio Fernandez
        List<Autores> autoresLista = dao.getAutores();// Variable para recoger todos los autores
        Autores GF;// Variable para guardar al Gregorio Fernandez
        List<Integer> aleatoriosSel = new ArrayList<>();// Variable para que guarde las ids de las obras que ya hayan sido seleccionadas
        int aleatorio;// Variable para sacar las obras al azar mas adelante
        int mezcla;// Variable para saber si pondremos antes o despues la obra del GF

        GF = ControlJuegos.seleccionarGF(autoresLista);// LLamamos al metodo que se encarga de buscar al GF entre los autores y lo guardamos

        // Instrucciones
        for (Obras obra : dao.getObras()) {// Bucle para recorrer todas las obras
            if (obra.getId_autor() == GF.getId_autor()) {// Si la obra tiene el id de Gregorio Fernandez
                obrasGFLista.add(obra);// Añadimos la obra a la lista de obras del GF
                for (Obras obraPareja : dao.getObras()) {// Buscamos su obra contrapuesta
                    if (obraPareja.getNombre_obra().equals(obra.getNombre_obra())) {// Que se hace debuscando su nombre, porque tienen el mismo nombre
                        obrasNoLista.add(obraPareja);// Añadimos esa obra a la lists
                    }
                }
            }
        }
        
        for (int i = 0; i < 6; i++) {// Bucle for para sacar aleatoriamente las obras
            do {// Bucle pàara que repita hasta que el aleatorio ya no haya sido seleccionado
                aleatorio = (int) (Math.random() * obrasGFLista.size());// Sacamos un numero aleatorio
            } while (ControlJuegos.idRepetida(aleatoriosSel, aleatorio));

            mezcla = (int) (Math.random() * 2);// Sacamos si ponemos primero o no la obra del GF

            if (mezcla == 0) {// SI es 0 ira primero, y añadimos las dos
                this.obras.add(obrasGFLista.get(aleatorio));
                this.obras.add(obrasNoLista.get(aleatorio));
            } else {// Si no la añadimos la segunda
                this.obras.add(obrasNoLista.get(aleatorio));
                this.obras.add(obrasGFLista.get(aleatorio));
            }
            
            this.obrasGF.add(obrasGFLista.get(aleatorio));// Le pasamos las obras que van siendo del Gregorio
            aleatoriosSel.add(aleatorio);// Registramos el aleatorio seleccionado
        }
    }

    // Getter para recoger las obras
    public List<Obras> getObras() {
        return obras;
    }

    // Getter para recoger las obras que sean del GF
    public List<Obras> getObrasGF() {
        return obrasGF;
    }
    
    // Metodo para sacar los nombres de las Obras
    public List<String> nombreObra() {
        
        // VAriable
        List<String> nombres = new ArrayList<>();// Variables para devolver los nombres de las obras

        //Instrucciones
        for (int i = 0; i < obras.size(); i=i+2) {// Bucle for para sacar todos los nombres, va de 2 en 2 poruqe estan guardadas las imagenes por parejas
            nombres.add(obras.get(i).getNombre_obra());// Y lo metemos en la variable
        }
        
        return nombres;// Y la retornamos
    }

    // Metodo para sacar los nombres de las Obras
    public List<String> urlObra() {
        
        //Variable
        List<String> urls = new ArrayList<>();// Variable para sacar unas lista con las url

        //Instrucciones
        for (int i = 0; i < obras.size(); i++) {
            urls.add(obras.get(i).getDescripcion_obra());// Lo registramos
        }
        
        return urls;// Y las retornamos
    }
    
    //Metodos para saber cual de las dos imagenes son las del GF
    public List<Character> isGF(){
        
        //Variable
        List<Character> verdaderas = new ArrayList<>();// Variable para poner cual sera la verdadera entre las dos imagenes

        //Instrucciones
        for (int i = 0; i < obras.size(); i=i+2) {// Vamos con un bucle for que coge de dos en dos
            if(obras.get(i) == obrasGF.get(i)){//Si la que cae es de Gregorio
                verdaderas.add('i');// La imagen estara a la izquierda
            }else{// SI no 
                verdaderas.add('d');// La imagen del gregorio sera la de la derecha
            }
        }
        
        return verdaderas;// Y lo retornamos
    }
}
