package org.example.Graphic;

import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.hibernate.cfg.Configuration;
public class Graphic extends JFrame{
    private JPanel panel1;
    private JLabel usuarioLabel;
    private JTextField textFieldUser;
    private JPanel extraP1;
    private JPanel extraP2;
    private JLabel contraseñaLabel;
    private JPasswordField passwordField1;
    private JButton entrarButton;
    private JButton salirButton;
    private JLabel iniciaSesionLabel;

    private JFrame previousFrame; // Variable para almacenar la ventana anterior



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Graphic gui = new Graphic();
                gui.setVisible(true);
            }
        });
    }

    public Container panel1() {
        return panel1;
    }

    public Graphic() {
        super("iniciaSesion");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        pack();
        setVisible(true);
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        entrarButton.addActionListener(new ActionListener() {
            int cont = 3;
            @Override
            public void actionPerformed(ActionEvent e) {

                if (cont == 0) {
                    System.exit(0);
                } else {


                    String usuario = textFieldUser.getText();
                    String contraseña = passwordField1.getText();
                    if (usuario.equals("1") && contraseña.equals("1")) {
                        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        if (session != null) {
//            System.out.println("Conectado");
//        } else {
//            System.out.println("No conectado");
//        }
                        JOptionPane.showMessageDialog(null, "Bienvenido");

                        // Cerrar la ventana anterior
                        if (previousFrame != null) {
                            previousFrame.dispose();
                        }

                        // Crear y mostrar la nueva ventana
                        Graphic2 newFrame = new Graphic2();
//                        setContentPane(extraP1);
//                        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                        newFrame.setSize(800, 600);
//                        pack();
                        // Agregar aquí los componentes y la lógica para el área de usuario
                        newFrame.setVisible(true);

                        // Actualizar la referencia a la nueva ventana
                        previousFrame = newFrame;

                        // Cerrar la ventana actual
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos" +
                                "\n" + "Intentos restantes: " + cont, "Error", JOptionPane.ERROR_MESSAGE);
                        cont --;
                    }
                }
            }
        });
    }


}
