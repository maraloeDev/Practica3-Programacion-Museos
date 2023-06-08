package com.gf.vistas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Eduardo Martín-Sonseca y Mario Ortuñez
 */
public class GUIPrincipal extends javax.swing.JFrame {
private GUI_Juego1 guiJ1 = new GUI_Juego1();
private GUI_Juego2 guiJ2 = new GUI_Juego2();
private GUI_Juego3 guiJ3 = new GUI_Juego3();
private GUI_Juego4 guiJ4 = new GUI_Juego4();
    /**
     * Creates new form GUIPrincipal
     */
    public GUIPrincipal() {
        initComponents();
        setFrame();
        
    }

    private void setFrame() {
        this.setTitle("Día Internacional de los Museos");
        setTitle("Ventana Principal");
        setSize(500,500);
        this.setLocationRelativeTo(null);

        JButton button1 = new JButton("Juego 1");
        button1.addActionListener((ActionEvent e) -> {
            OtherFrame otherFrame = new OtherFrame();
            guiJ1.setVisible(true);
            
        });

        JButton button2 = new JButton("Juego 2");
        button2.addActionListener((ActionEvent e) -> {
            GUI_Juego2 juego2 = new GUI_Juego2();
            guiJ2.setVisible(true);
        });

        JButton button3 = new JButton("Juego 3");
        button3.addActionListener((ActionEvent e) -> {
            GUI_Juego3 juego3 = new GUI_Juego3();
            guiJ3.setVisible(true);
        });

        JButton button4 = new JButton("Juego 4");
        button4.addActionListener((ActionEvent e) -> {
            GUI_Juego4 juego4 = new GUI_Juego4();
            guiJ4.setVisible(true);
        });

        getContentPane().setLayout(new GridLayout(4, 1));
        getContentPane().add(button1);
        getContentPane().add(button2);
        getContentPane().add(button3);
        getContentPane().add(button4);
    }
    
public class OtherFrame extends JFrame {

        public OtherFrame() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Ventana principal");
            setSize(300, 200);
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

        jMenuItem2 = new javax.swing.JMenuItem();
        jTextField1 = new javax.swing.JTextField();

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setEditable(false);
        jTextField1.setText("Un ladron ha robado unos cuadros, y os hemos llamado para que los devolvais a sus museos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
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

            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            //</editor-fold>

        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GUIPrincipal().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
