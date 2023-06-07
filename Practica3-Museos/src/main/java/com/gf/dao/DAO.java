/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelos.Autores;
import com.gf.modelos.Museos;
import com.gf.modelos.Obras;
import com.gf.modelos.Paises;
import com.gf.modelos.Ranking;
import com.gf.utilidades.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario Ortuñez
 *
 * Logica: Esta clase se encarga de realizar la conexión con la base de datos y
 * recuperar los datos de las tablas correspondientes. En el constructor, se
 * establece la conexión con la base de datos y se ejecutan las consultas
 * necesarias para obtener los registros de las tablas de autores, museos,
 * obras, países y ranking. Los resultados de las consultas se utilizan para
 * crear objetos de las respectivas clases (Autores, Museos, Obras, Paises,
 * Ranking) y se almacenan en listas para su posterior acceso.
 *
 * Al crear un objeto de esta clase, se llenan automáticamente las listas con
 * los datos recuperados de la base de datos. Los métodos getter proporcionan
 * acceso a estas listas para que puedan ser utilizadas en otras partes del
 * programa.
 *
 * Por ultimo, decir que se controla algun error al conectar y/o recuperar los
 * datos de la base dedatos en el constructor mediante los try - catch
 *
 */
public class Dao {

    private List<Autores> listaAutores = new ArrayList<>(); // Almacena la lista de autores de la base de datos
    private List<Museos> listaMuseos = new ArrayList<>(); // Almacena la lista de museos de la base de datos
    private List<Obras> listaObras = new ArrayList<>(); // Almacena la lista de obras de la base de datos
    private List<Paises> listaPaises = new ArrayList<>(); // Almacena la lista de países de la base de datos
    private List<Ranking> listaRanking = new ArrayList<>(); // Almacena la lista de puntuaciones del ranking

    public Dao() {

        try (Connection conn = ConexionBD.getConn()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            // Rellenamos las listas con los datos de la base de datos
            try {
                String sqlAutores = "SELECT * FROM autores;";
                rs = st.executeQuery(sqlAutores);
                while (rs.next()) {
                    listaAutores.add(new Autores(rs.getInt("id_autor"),
                            rs.getString("nombre_autor"),
                            rs.getInt("id_pais")));
                }
            } catch (SQLException e) {
                System.out.println("Algo falló en la recuperación de datos de los autores");
                System.out.println(e.getMessage());
            }

            // Repetimos el mismo patrón para las otras tablas
            try {
                String sqlMuseos = "SELECT * FROM museos;";
                rs = st.executeQuery(sqlMuseos);
                while (rs.next()) {
                    listaMuseos.add(new Museos(rs.getInt("id_museo"),
                            rs.getString("nombre_museo"),
                            rs.getInt("id_pais"),
                            rs.getBoolean("existe_museo")));
                }

            } catch (SQLException e) {
                System.out.println("Algo falló en la recuperación de datos de los museos");
                System.out.println(e.getMessage());
            }

            try {
                String sqlObras = "SELECT * FROM obras;";
                rs = st.executeQuery(sqlObras);
                while (rs.next()) {
                    listaObras.add(new Obras(rs.getInt("id_obra"),
                            rs.getString("nombre_obra"),
                            rs.getString("descripción_obra"),
                            rs.getString("disciplina"),
                            rs.getInt("id_museo"),
                            rs.getInt("id_autor")));
                }

            } catch (SQLException e) {
                System.out.println("Algo falló en la recuperación de datos de las obras");
                System.out.println(e.getMessage());
            }

            try {
                String sqlPaises = "SELECT * FROM paises;";
                rs = st.executeQuery(sqlPaises);
                while (rs.next()) {
                    listaPaises.add(new Paises(rs.getInt("id_pais"),
                            rs.getString("nombre_pais")));
                }

            } catch (SQLException e) {
                System.out.println("Algo falló en la recuperación de datos de los países");
                System.out.println(e.getMessage());
            }

            try {
                String sqlRanking = "SELECT * FROM ranking;";
                rs = st.executeQuery(sqlRanking);
                while (rs.next()) {
                    listaRanking.add(new Ranking(rs.getInt("id_ranking"),
                            rs.getString("nombre_jugador"),
                            rs.getInt("puntos_jugador")));
                }

            } catch (SQLException e) {
                System.out.println("Algo falló en la recuperación de datos del ranking");
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La conexión o la recuperación de datos de la base de datos falló. Por favor reinicie el programa");
            System.out.println("La conexión o la recuperación de datos de la base de datos falló. Por favor reinicie el programa");
            System.out.println(e.getMessage());
        } finally {
            ConexionBD.desconectarBD();
        }
    }

    // Getters de las listas
    public List<Autores> getAutores() {
        return listaAutores;
    }

    public List<Museos> getMuseos() {
        return listaMuseos;
    }

    public List<Obras> getObras() {
        return listaObras;
    }

    public List<Paises> getPaises() {
        return listaPaises;
    }

    public List<Ranking> getRanking() {
        return listaRanking;
    }
}
