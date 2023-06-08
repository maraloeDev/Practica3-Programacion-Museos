/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gf.vistas;

import com.gf.controles.Juego1;
import com.gf.dao.Dao;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author Eduardo Martín-Sonseca Mario Ortuñez
 *
 * Clase del primer juego
 *
 * Enunciado: 1. ¿Quién lo hizo? A partir de unas imágenes de obras de arte
 * genéricas, hay que adivinar su autor
 *
 */
public class GUI_Juego1 extends javax.swing.JFrame {

    private final JPanel panelContenedor = new JPanel(new GridLayout(2, 2));
    private final JPanel panelDatos = new JPanel(new GridLayout(0, 2));
    private final JPanel panelCuadros = new JPanel(new GridLayout(0, 2));
    private final JPanel panelBoton = new JPanel(new FlowLayout());
    private final JPanel panelContador = new JPanel(new FlowLayout());

    private Juego1 juego1;
    private final Dao dao = new Dao();

    private int tiempoContador = 360;
    private Timer timer;

    private final List<String> datosColocados = new ArrayList<>();
    private int cuadrosAcertados = 0;

    public GUI_Juego1() {
        initComponents();
        setFrame();
        juego1 = new Juego1(dao);
    }

    private void setFrame() {
        setTitle("Juego 1");
        setLocationRelativeTo(null);
        setContentPane(panelContenedor);

        panelContenedor.add(panelCuadros);
        panelContenedor.add(panelDatos);
        panelContenedor.add(panelBoton);
        panelContenedor.add(panelContador);

        try {
            rellenarPanelCuadros();
        } catch (MalformedURLException ex) {
            Logger.getLogger(GUI_Juego1.class.getName()).log(Level.SEVERE, null, ex);
        }
        rellenarPanelDatos();
        rellenarPanelBoton();
    }

    public void rellenarPanelCuadros() throws MalformedURLException {
        List<String> listaURLS = juego1.urlImg();
        int numeroCuadros = 10;

        for (int i = 0; i < numeroCuadros; i++) {
            JLabel cuadro = new JLabel();
            try {
                URL imagenUrl = new URL(listaURLS.get(i));
                ImageIcon icono = new ImageIcon(imagenUrl);
                Image imagen = icono.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                cuadro.setIcon(new ImageIcon(imagen));

            } catch (MalformedURLException e) {
                throw e;
            }
            panelCuadros.add(cuadro);
        }
    }

    public void rellenarPanelDatos() {
        List<String> listaNombres = juego1.nombreObras();
        List<String> listaAutores = juego1.autoresObra(dao);
        List<String> listaDatos = new ArrayList<>();

        int numeroCuadros = 10;
        for (int i = 0; i < numeroCuadros; i++) {
            datosColocados.add("<html> <h3>" + listaNombres.get(i) + " </h3><br>" + listaAutores.get(i) + "</html>");
            listaDatos.add("<html> <h3>" + listaNombres.get(i) + " </h3><br>" + listaAutores.get(i) + "</html>");
        }

        Collections.shuffle(listaDatos);

        for (int i = 0; i < numeroCuadros; i++) {
            JLabel datosCuadro = new JLabel(listaDatos.get(i));

            Color colorNota = new Color(250, 235, 175);
            datosCuadro.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            datosCuadro.setOpaque(true);
            datosCuadro.setBackground(colorNota);

            Dimension dimension = new Dimension(200, 50);
            datosCuadro.setPreferredSize(dimension);

            datosCuadro.setHorizontalAlignment(SwingConstants.CENTER);

            datosCuadro.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    datosCuadro.setLocation(e.getX() + datosCuadro.getX() - datosCuadro.getWidth() / 2,
                            e.getY() + datosCuadro.getY() - datosCuadro.getHeight() / 2);
                }
            });

            panelDatos.add(datosCuadro);
        }
    }

    public void rellenarPanelBoton() {
        JButton botonOk = new JButton("OK");
        botonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int contadorCuadro = 1;
                for (Component cuadro : panelCuadros.getComponents()) {
                    if (cuadro instanceof JLabel jLabel) {
                        for (Component dato : panelDatos.getComponents()) {
                            if (cuadro.getBounds().contains(dato.getLocation())) {
                                if (dato.getName().equals(datosColocados.get(contadorCuadro))) {
                                    jLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                                    cuadrosAcertados++;
                                } else {
                                    jLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
                                }
                            }
                        }
                    }
                    contadorCuadro++;
                }
            }
        });
        panelBoton.add(botonOk);

        botonOk.setVerticalAlignment(SwingConstants.CENTER);
        botonOk.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void rellenarPanelContador() {
        JLabel contador = new JLabel();
        ActionListener actionListener = (ActionEvent event) -> {
            tiempoContador--;
            contador.setName(tiempoContador + " s");

            if (tiempoContador <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Se acabó el tiempo");
            }
        };

        timer = new Timer(1000, actionListener);
        timer.start();

        panelContador.add(contador);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Juego1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GUI_Juego1().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
