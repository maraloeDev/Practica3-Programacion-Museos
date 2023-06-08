/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gf.vistas;

import com.gf.controles.Juego2;
import com.gf.dao.Dao;
import com.gf.modelos.Museos;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Eduardo Martin-Sonseca Mario Ortuñes Sanz
 */
public class GUI_Juego2 extends javax.swing.JFrame {

    private static final Dao dao = new Dao();
    private static final Juego2 juego2 = new Juego2(dao);
    private static final JPanel panelContenedor = new JPanel(new GridLayout(0, 1));
    private int contador = 0;
    private static final JLabel labelResultado = new JLabel("ES CORRECTO!!");
    private static final JButton botonComprobar = new JButton("Comprobar");
    static List<String> museosSeleccionados = new ArrayList<>();

    /**
     * Creates new form GUI_Juego2
     */
    public GUI_Juego2() {
        initComponents();
        setFrame();
        control();
    }

    private void setFrame() {
        this.setTitle("Verdadero/Falso de Museos");
        this.setContentPane(panelContenedor);
        this.setLocationRelativeTo(null);
        labelResultado.setVisible(false);
        panelContenedor.add(labelResultado);

        botonComprobar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Component componente : panelContenedor.getComponents()) {
                    if (componente instanceof JCheckBox) {
                        JCheckBox checkbox = (JCheckBox) componente;
                        if (checkbox.isSelected()) {
                            museosSeleccionados.add(checkbox.getText());
                            labelResultado.setVisible(true);
                        } else {
                            labelResultado.setVisible(false);
                        }
                    }
                }
                panelContenedor.revalidate();
                panelContenedor.repaint();

                if (!todosMuseosVerdaderos(museosSeleccionados)) {
                    JOptionPane.showMessageDialog(GUI_Juego2.this, "Enhorabuena, todos los museos seleccionados son verdaderos");
                } else {
                    JOptionPane.showMessageDialog(GUI_Juego2.this, "Tienes que seleccionar un maximo de 3 museos");
                }
            }
        });
    }

    private boolean todosMuseosVerdaderos(List<String> museosSeleccionados) {
        for (String museoSeleccionado : museosSeleccionados) {
            Museos museo = (Museos) juego2.getMuseos(museoSeleccionado); // Obtengo el objeto Museo
            if (museo == null || museo.isExiste_museo()) {
                return false;
            }
        }
        return true;
    }

    private void control() {
        List<JCheckBox> checkboxes = new ArrayList<>();

        // Obtener la lista de nombres de museos de la base de datos
        List<String> nombresMuseosBD = juego2.getNombresMuseos();

        for (String museo : nombresMuseosBD) {
            JCheckBox checkBoxMuseo = new JCheckBox(museo);

            // Verificar si el nombre del museo está en la lista de nombres de museos
            if (juego2.getNombresMuseos() != null) {
                checkBoxMuseo.setSelected(true); // Marcar como seleccionado por defecto
                contador++;
            }

            checkboxes.add(checkBoxMuseo);
            checkBoxMuseo.addActionListener((ActionEvent e) -> {
                // Resto del código
            });
            panelContenedor.add(checkBoxMuseo);

        }
        panelContenedor.add(botonComprobar);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Juego2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Juego2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Juego2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Juego2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GUI_Juego2().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
