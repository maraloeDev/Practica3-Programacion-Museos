package com.gf.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo Martin-Sonseca Mario Ortuñez
 */
public class ConexionBD {

    public static Connection conn;
    public static String url = "jdbc:mysql://localhost:3306/dim_gf";
    public static final String USER_SQL = "root";
    public static final String PASSWORD_SQL = "";

    public static void main(String[] args) {

        try {
            conn = DriverManager.getConnection(url, USER_SQL, PASSWORD_SQL);
            JOptionPane.showMessageDialog(null, "Base de datos conectada");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Base de datos no conectada");
        }

    }
}
