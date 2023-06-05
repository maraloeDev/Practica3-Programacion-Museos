/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controles;

import com.gf.dao.Dao;
import com.gf.modelos.Museos;
import com.gf.modelos.Obras;
import com.gf.modelos.Paises;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario Ortuñez
 *
 * 4. Coloca en el mapa. El usuario tiene que colocar en un mapa europeo obras
 * de arte
 *
 * Lógica: Este último juego tiene una lista con las cuatro obras del juego 4,
 * las cuales se seleccionan de los juegos anteriores mediante el ControlJuegos
 * y se añaden en el constructor.
 *
 * Además, se implementan métodos para obtener los nombres de las obras, las
 * URLs de sus imágenes y, por último, un método para obtener el país de
 * ubicación de cada obra mediante su museo.
 *
 *
 */
public class Juego4 {

    private final List<Obras> obras = new ArrayList<>(); // Lista de obras del juego 4

    public Juego4(Dao dao, Obras obra1, Obras obra2, Obras obra3, Obras obra4) {
        // Validación de las obras antes de agregarlas al juego
        if (validarObra(dao, obra1) && validarObra(dao, obra2) && validarObra(dao, obra3) && validarObra(dao, obra4)) {
            obras.add(obra1); // Primera obra del juego 1
            obras.add(obra2); // Segunda obra del juego 1
            obras.add(obra3); // Obra del juego 2
            obras.add(obra4); // Obra del juego 3
        } else {
            System.out.println("Alguna de las obras fue alterada y no pertenece a las registradas");
        }
    }

    // Lista de nombres de las obras.
    public List<String> nombreObras() {
        List<String> nombres = new ArrayList<>();
        for (Obras obra : obras) {
            nombres.add(obra.getNombre_obra());
        }
        return nombres;
    }

    // Lista de URL de las imágenes de las obras.
    public List<String> urlImg() {
        List<String> urls = new ArrayList<>();
        for (Obras obra : obras) {
            urls.add(obra.getDescripcion_obra());
        }
        return urls;
    }

    // Lista de los países donde se encuentran las obras del juego 4 mediante su museo.
    public List<String> paisObra(Dao dao) throws NullPointerException {
        List<String> paises = new ArrayList<>();
        Museos museoObra = null;
        Paises paisObra = null;
        for (Obras obra : obras) {
            try {
                for (Museos museo : dao.getMuseos()) {// Buscamos entre todos los museos cual tiene la id correspondiente
                    if (museo.getId_museo() == obra.getId_muaeo()) {
                        museoObra = museo;
                        break;
                    }
                }
                for (Paises pais : dao.getPaises()) {// Buscamos entre todos los países cual tiene la id correspondiente
                    if (pais.getId_pais() == museoObra.getId_pais()) {
                        paisObra = pais;
                        break;
                    }
                }
                if (paisObra != null) {
                    paises.add(paisObra.getNombre_pais());// Añadimos a la lista
                } else {
                    paises.add("En el metodo no se encontro el pais del museo y este quedo en null.");
                    throw new NullPointerException();
                }
            } catch (NullPointerException e) {// Controlamos que no se encuentre el pais o el museo, por lo que quedaria alguno en null
                System.out.println("En el metodo no se encontro el museo de la obra y este quedo en null.");
                throw e;
            }
        }
        return paises;
    }

    private boolean validarObra(Dao dao, Obras obraComprobar) {
        boolean validar = false;
        for (Obras obra : dao.getObras()) {
            if (obraComprobar.equals(obra)) {
                validar = true;
                break;
            }
        }
        return validar;
    }
}
