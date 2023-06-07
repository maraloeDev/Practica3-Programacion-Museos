/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controles;

import com.gf.modelos.Autores;
import java.util.List;

/**
 *
 * @author Mario Ortuñez
 */
public class ControlJuegos {
    

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
