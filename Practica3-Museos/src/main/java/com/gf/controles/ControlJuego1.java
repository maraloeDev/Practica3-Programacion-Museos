/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controles;

import com.gf.dao.Dao;
import com.gf.modelos.Obras;
import com.gf.vistas.GUI_Juego1;
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
 * @author ortsanma
 */
public class ControlJuego1 {
    
    private final JPanel panelCuadros = new JPanel(new GridLayout(0, 2));
    private final JPanel panelDatos = new JPanel(new GridLayout(0, 2));
    
    private final Dao dao = new Dao();
    private final MetodosJuego1 juego1 = new MetodosJuego1(dao);

    private int tiempoContador = 360;
    private Timer timer;

    private final List<String> datosColocados = new ArrayList<>();
    private int obrasAcertados = 0;
    private List<Obras> obrasConseguidas = new ArrayList<>();
    
    
    public JPanel rellenarPanelCuadros() throws MalformedURLException {
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
        return panelCuadros;
    }

    public JPanel rellenarPanelDatos() {
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
        return panelDatos;
    }

    public JPanel rellenarPanelBoton() {
        JPanel panelBoton = new JPanel(new FlowLayout());
        JButton botonOk = new JButton("OK");
        botonOk.addActionListener((ActionEvent e) -> {
            int contadorCuadro = 1;
            for (Component cuadro : panelCuadros.getComponents()) {
                if (cuadro instanceof JLabel jLabel) {
                    for (Component dato : panelDatos.getComponents()) {
                        if (cuadro.getBounds().contains(dato.getLocation())) {
                            if (dato.getName().equals(datosColocados.get(contadorCuadro))) {
                                jLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                                obrasAcertados++;
                            } else {
                                jLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
                            }
                        }
                    }
                }
                contadorCuadro++;
            }
            String mensaje = "Has acertado " + obrasAcertados + " cuadros";
            if(obrasAcertados >= 5){
                Collections.shuffle(datosColocados);
                mensaje = mensaje + "\n Conseguistes el cuadro " + datosColocados.get(1);
            }else{
                mensaje = mensaje + "Lo siento, no conseguistes ningun cuadro";
            }
            if(obrasAcertados >= 7){
                mensaje = mensaje + "\n y el cuadro " + datosColocados.get(2);
            }
            JOptionPane.showMessageDialog(null, mensaje);
        });
        panelBoton.add(botonOk);

        botonOk.setHorizontalAlignment(SwingConstants.CENTER);
        return panelBoton;
    }

    public JPanel rellenarPanelContador() {
        JPanel panelContador = new JPanel(new FlowLayout());
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
        return panelContador;
    }
}
