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
 * @author Eduardo Martin-Sonseca Mario Ortuñez
 * 
 * 3. Gregorio Fernández.
 * El usuario tiene que adivinar cual de dos imágenes de esculturas planteadas, 
 * corresponde su autoría a Gregorio Fernández
 * 
 */
public class Juego3 {

    private List<Integer> obras = new ArrayList<>();

    public Juego3(Dao dao) {
        List<Integer> obrasGFLista = new ArrayList<>();
        List<Integer> obrasNoLista = new ArrayList<>();

        for (Obras obra : dao.getObras()) {
            if (obra.getId_autor() == 21) {
                obrasGFLista.add(obra.getId_obra());
                for (Obras obraPareja : dao.getObras()) {
                    if (obraPareja.getNombre_obra().equals(obra.getNombre_obra())) {
                        obrasNoLista.add(obraPareja.getId_obra());
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            int aleatorio = (int) (Math.random() * obrasGFLista.size());
            this.obras.add(obrasGFLista.get(aleatorio));
            this.obras.add(obrasNoLista.get(aleatorio));
        }
    }

    public List<String> nombreObra(Dao dao) {
        List<String> nombres = new ArrayList<>();

        for (int i = 0; i < obras.size(); i++) {
            int obraId = obras.get(i);
            nombres.add(dao.getObras().get(obraId).getNombre_obra());
        }
        return nombres;
    }

    public List<String> urlObra(Dao dao) {
        List<String> urls = new ArrayList<>();

        for (int i = 0; i < obras.size(); i++) {
            int obraId = obras.get(i);
            urls.add(dao.getObras().get(obraId).getDescripcion_obra());
        }
        return urls;
    }
}
