/*
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licencia
 * Haz clic en nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java para editar esta plantilla
 */
package com.gf.controles;

import com.gf.controles.Juego2;
import com.gf.dao.Dao;
import com.gf.modelos.Museos;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 *
 * Autores: Eduardo Martin-Sonseca, Mario Ortuñes Sanz
 */
public class J2_Prueba extends javax.swing.JFrame {

    private static final Dao dao = new Dao();
    private static final Juego2 juego2 = new Juego2(dao);
    static JPanel panelContenedor = new JPanel(new GridLayout(0, 1));
    private int contador = 0;

    /**
     * Crea un nuevo formulario GUI_Juego2
     */
    public J2_Prueba() {
        initComponents();
        configurarVentana();
        agregarControles();
    }

    private void configurarVentana() {
        this.setTitle("Verdadero/Falso de Museos");
        this.setContentPane(panelContenedor);
        this.setLocationRelativeTo(null);
    }

    private void agregarControles() {
        // Recorre los nombres de los museos
        for (String museo : juego2.getNombresMuseos()) {
            JCheckBox checkBoxMuseo = new JCheckBox(museo);
            checkBoxMuseo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkBoxMuseo.isSelected()) {
                        contador++;
                        if (contador >= 3) {
                            for (Component componente : panelContenedor.getComponents()) {
                                if (componente instanceof JCheckBox) {
                                    JCheckBox checkbox = (JCheckBox) componente;
                                    if (!checkbox.isSelected()) {
                                        checkbox.setEnabled(false);
                                    }
                                }
                            }
                        }
                    } else {
                        contador--;
                        if (contador <= 3) {
                            for (Component componente : panelContenedor.getComponents()) {
                                if (componente instanceof JCheckBox) {
                                    JCheckBox checkbox = (JCheckBox) componente;
                                    checkbox.setEnabled(true);
                                }
                            }
                        }
                    }
                }
            });
            panelContenedor.add(checkBoxMuseo);
        }
    }

    /**
     * Este método se llama desde el constructor para inicializar el formulario.
     * ADVERTENCIA: No modifiques este código. El contenido de este método siempre se regenera automáticamente por el editor de formularios.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
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
    }// </editor-fold>

    /**
     * Punto de entrada principal para la aplicación. Crea y muestra el formulario GUI_Juego2.
     */
    public static void main(String args[]) {
        /* Establece el aspecto visual Nimbus (opcional) */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(J2_Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(J2_Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(J2_Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(J2_Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Crea y muestra el formulario */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new J2_Prueba().setVisible(true);
            }
        });
    }

    // Variables declaration - no modificar
    // Fin de la declaración de variables
}
