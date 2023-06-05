/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modeles.Autores;
import com.gf.modeles.Museos;
import com.gf.modeles.Obras;
import com.gf.modeles.Paises;
import com.gf.modeles.Ranking;
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
 * @author mario
 */
public class Dao {

    private List<Autores> listaAutores = new ArrayList<>();
    private List<Museos> listaMuseos = new ArrayList<>();
    private List<Obras> listaObras = new ArrayList<>();
    private List<Paises> listaPaises = new ArrayList<>();
    private List<Ranking> listaRanking = new ArrayList<>();

    public Dao() {
        ConexionBD conexion = new ConexionBD();
        conexion.conectarBD();
        try (Connection conn = conexion.getConn(); Statement st = conn.createStatement()) {
            ResultSet rs;

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
            }
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
            }
            try {
                String sqlObras = "SELECT * FROM obras;";
                rs = st.executeQuery(sqlObras);
                while (rs.next()) {
                    listaObras.add(new Obras(rs.getInt("id_obra"),
                            rs.getString("nombre_obra"),
                            rs.getString("descripcion_obra"),
                            rs.getString("disciplina"),
                            rs.getInt("id_muaeo"),
                            rs.getInt("id_autor")));
                }

            } catch (SQLException e) {
                System.out.println("Algo falló en la recuperación de datos de los obras");
            }
            try {
                String sqlPaises = "SELECT * FROM paises;";
                rs = st.executeQuery(sqlPaises);
                while (rs.next()) {
                    listaPaises.add(new Paises(rs.getInt("id_pais"),
                            rs.getString("nombre_pais")));
                }

            } catch (SQLException e) {
                System.out.println("Algo falló en la recuperación de datos de los paises");
            }
            try {
                String sqlRanking = "SELECT * FROM ranking;";
                rs = st.executeQuery(sqlRanking);
                while (rs.next()) {
                    listaRanking.add(new Ranking(rs.getInt("id_ranking"),
                            rs.getString("nombre_jugador"),
                            rs.getInt("puntos_Jugador")));
                }

            } catch (SQLException e) {
                System.out.println("Algo falló en la recuperación de datos de los ranking");
            }
        } catch (SQLException e) {
            conexion.desconectarBD();
            JOptionPane.showMessageDialog(null, "La conexión o la recuperación de datos de la base de datos falló. Por favor reinicie el programa");
            System.out.println(e.getMessage());
        } finally {
            conexion.desconectarBD();
        }
    }

    public List<Autores> getAutores() {
        return (listaAutores);
    }

    public List<Museos> getMuseos() {
        return (listaMuseos);
    }

    public List<Obras> getObras() {
        return (listaObras);
    }

    public List<Paises> getPaises() {
        return (listaPaises);
    }

    public List<Ranking> getRanking() {
        return (listaRanking);
    }
}
