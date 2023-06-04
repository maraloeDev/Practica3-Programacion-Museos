/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controls;

import java.util.List;

/**
 *
 * @author Eduardo Martin-Sonseca Mario Ortuñez
 */
public class ControlJuegos {

    // Metodo para saber si esta repetido el aleatorio
    public static boolean idRepetida(List<Integer> aleatoriosSel, int id) {

        // Variable
        boolean repetida = false;// Variable que se retorna, true si esta repetido, y false si no lo esta

        // Instrucciones
        for (int i = 0; i < aleatoriosSel.size(); i++) {// Bucle for para recorrer todos los aleatorios ya seleccionados
            if (aleatoriosSel.get(i) == id) {// SI esta repetido 
                repetida = true;// La variable se torna true
                break;// Salimos ya del bucle, porque no hace falta seguir
            }
        }

        return repetida;// Retornamos el boleano
    }

}
