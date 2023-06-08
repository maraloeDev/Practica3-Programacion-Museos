/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gf.vistas;

import com.gf.controles.Juego1;
import com.gf.dao.Dao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

/**
 *
 * @author Eduardo Martin-Sonseca && Mario Ortuñez Sanz
 * 
  * Clase del primer juego
 *
 * Enunciado: 1. ¿Quién lo hizo? A partir de unas imágenes de obras de arte
 * genéricas, hay que adivinar su autor 
 * 
 */

public class GUI_Juego1 extends javax.swing.JFrame {

    private static final JPanel panelContenedor = new JPanel(new GridLayout(0, 2));
    private static Juego1 juego1;
    private static final Dao dao = new Dao();
    private static JLabel nombreArrastrado; // Almacena el JLabel de nombre arrastrado

    public GUI_Juego1() {
        initComponents();
        setFrame();
        GUI_Juego1.juego1 = new Juego1(dao);
        try {
            insertarCuadros();
        } catch (MalformedURLException ex) {
            Logger.getLogger(GUI_Juego1.class.getName()).log(Level.SEVERE, null, ex);
        }
        insertarNombre();
    }

    private void setFrame() {
        this.setTitle("Juego 1");
        this.setLocationRelativeTo(null);
        this.setContentPane(panelContenedor);
    }

    public static void insertarCuadros() throws MalformedURLException {
        JPanel panelCuadros = new JPanel(new GridLayout(0, 2));
        panelContenedor.add(panelCuadros);
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

            // Establecer borde predeterminado para los cuadros
            cuadro.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            panelCuadros.add(cuadro);
        }
    }

    public static void insertarNombre() {
        JPanel panelDatos = new JPanel(new GridLayout(0, 2));
        panelContenedor.add(panelDatos);

        List<String> listaNombres = juego1.nombreObras();
        List<String> listaAutores = juego1.autoresObra(dao);
        int numeroCuadros = 10;

        for (int i = 0; i < numeroCuadros; i++) {
            String nombre = listaNombres.get(i);
            String autor = listaAutores.get(i);
            String mensaje = "<html> <h3>" + nombre + " </h3><br>" + autor + "</html>";
            JLabel datosCuadro = new JLabel(mensaje);

            Color colorNota = new Color(250, 235, 175);
            datosCuadro.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            datosCuadro.setOpaque(true);
            datosCuadro.setBackground(colorNota);

            Dimension dimension = new Dimension(200, 50);
            datosCuadro.setPreferredSize(dimension);

            datosCuadro.setHorizontalAlignment(SwingConstants.CENTER);

            // Establecer TransferHandler para permitir el arrastre y la transferencia de datos
            datosCuadro.setTransferHandler(new TransferHandler("text"));

            // Agregar MouseListener para detectar el inicio y el final del arrastre
            datosCuadro.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    nombreArrastrado = (JLabel) e.getSource(); // Almacenar el nombre arrastrado
                    nombreArrastrado.setBackground(Color.ORANGE); // Cambiar el color de fondo
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    nombreArrastrado = null; // Restaurar el nombre arrastrado
                    datosCuadro.setBackground(colorNota); // Restaurar el color de fondo original
                }
            });

            panelDatos.add(datosCuadro);
        }
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
    
    // Clase interna para el TransferHandler
    private static class NombreTransferHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return TransferHandler.COPY;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new StringSelection(((JLabel) c).getText());
        }
    }
}
