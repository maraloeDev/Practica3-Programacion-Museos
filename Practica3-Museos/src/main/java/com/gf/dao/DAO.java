/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.models.Autores;
import com.gf.models.Museos;
import com.gf.models.Obras;
import com.gf.models.Paises;
import com.gf.models.Ranking;
import com.gf.utils.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class DAO {

    private ArrayList<Autores> autores = new ArrayList<>();
    private ArrayList<Museos> museos = new ArrayList<>();
    private ArrayList<Obras> obras = new ArrayList<>();
    private ArrayList<Paises> paises = new ArrayList<>();
    private ArrayList<Ranking> ranking = new ArrayList<>();

    public DAO() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        conexion.conectarBD();
        try (Connection conn = conexion.getConn();  Statement st = conn.createStatement()) {
            ResultSet rs;

            String sqlAutores = "SELECT * FROM autores;";
            rs = st.executeQuery(sqlAutores);
            while (rs.next()) {
                autores.add(new Autores(rs.getInt("id_autor"),
                        rs.getString("nombre_autor"),
                        rs.getInt("id_pais")));
            }
            String sqlMuseos = "SELECT * FROM museos;";
            rs = st.executeQuery(sqlMuseos);
            while (rs.next()) {
                museos.add(new Museos(rs.getInt("id_museo"),
                        rs.getString("nombre_museo"),
                        rs.getInt("id_pais")));
            }
            String sqlObras = "SELECT * FROM obras;";
            rs = st.executeQuery(sqlObras);
            while (rs.next()) {
                obras.add(new Obras(rs.getInt("id_obra"),
                        rs.getString("nombre_obra"),
                        rs.getString("descripcion_obra"),
                        rs.getString("disciplina"),
                        rs.getInt("id_muaeo"),
                        rs.getInt("id_autor")));
            }
            String sqlPaises = "SELECT * FROM paises;";
            rs = st.executeQuery(sqlPaises);
            while (rs.next()) {
                paises.add(new Paises(rs.getInt("id_pais"),
                        rs.getString("nombre_pais")));
            }
            String sqlRanking = "SELECT * FROM ranking;";
            rs = st.executeQuery(sqlRanking);
            while (rs.next()) {
                ranking.add(new Ranking(rs.getInt("id_ranking"),
                        rs.getString("nombre_jugador"),
                        rs.getInt("puntos_Jugador")));
            }
        } catch (SQLException e) {
            conexion.desconectarBD();
            JOptionPane.showMessageDialog(null, "La conexion o la recuperacion de datos de la base de datos fallo.Por favor reinicie el programa");
            System.out.println(e.getMessage());
            throw e;
        } finally {
            conexion.desconectarBD();
        }
    }
}
