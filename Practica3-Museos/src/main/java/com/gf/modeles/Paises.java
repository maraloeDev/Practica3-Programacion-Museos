package com.gf.modeles;

/**
 *
 * @author Eduardo Martin-Sonseca
 */
public class Paises {
    /* Clase en general Orientada a objetos que referencia a la Entidad denomidada Paises*/
    private int id_pais;
    private String nombre_pais;

    public Paises(int id_pais, String nombre_pais) {
        this.id_pais = id_pais;
        this.nombre_pais = nombre_pais;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public String getNombre_pais() {
        return nombre_pais;
    }

    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }

    @Override
    public String toString() {
        return "id_pais " + id_pais + ", nombre_pais " + nombre_pais;
    }
    
    
    
}
