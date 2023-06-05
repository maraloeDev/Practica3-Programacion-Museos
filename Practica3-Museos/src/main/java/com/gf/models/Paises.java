package com.gf.models;

/**
 *
 * @author Eduardo Martin-Sonseca
 */
public class Paises {
    // Atributos
    private int id_pais;
    private String nombre_pais;
    
    // Constructores
    public Paises(int id_pais, String nombre_pais) {
        this.id_pais = id_pais;
        this.nombre_pais = nombre_pais;
    }
    
    // Metodos Getter y Setter
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
    
    // Metodos toString
    @Override
    public String toString() {
        return "id_pais " + id_pais + ", nombre_pais " + nombre_pais;
    }
    
    
    
}
