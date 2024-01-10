package org.example.Graphic;

import org.example.Data.HibernateConexion;
import org.example.domain.CursosClass;
import org.example.domain.EstudiantesClass;
import org.example.domain.InscripcionesClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class Graphic2 extends JFrame{
    private JPanel panel2;
    private JTabbedPane tabbedPane1;
    private JTextField InombreE;
    private JTextField IApellidoE;
    private JTextField IMailE;
    private JButton salirButton2;
    private JTabbedPane tabbedPane2;
    private JButton crearButtonE;
    private JComboBox SCurso;
    private JComboBox SCatedratico;
    private JButton ACurso;
    private JTextField IIdE;
    private JTextArea TotalCursos;
    private JButton ACursos;
    private JTextField IIdC;
    private JTabbedPane MostrarC;
    private JTextField MostrarEstudiantes;
    private JLabel ingreseElIDDelLabel;
    private JButton ButtonME;
    private JList listE;
    private JButton ButtonMC;
    private JTextField MostrarCursos;
    private JTextField MostrarInscripcion;
    private JButton buttonMI;
    private JList listC;
    private JList listI;
    private JTabbedPane tabbedPane3;
    private JTextField ACTextField;
    private JTextField ActApellido;
    private JTextField ActGmail;
    private JTextField ActNombre;
    private JButton actualizarButtonEstudiante;
    private JButton actualizarButtonCurso;
    private JButton actualizarButtonInscripcion;
    private JTextField ActIdE;
    private JTextField ActIdC;
    private JTextField ActIdI;
    private JTextField ActIdEstudiante;
    private JTextField ActIdCurso;
    private JComboBox ActCurso;
    private JComboBox ActProfesor;
    private JTabbedPane tabbedPane4;
    private JTextField DeletEstudiante;
    private JButton deletButtonEstudiante;
    private JTextField DeletCurso;
    private JButton deletButtonCurso;
    private JTextField DeletInscripcion;
    private JButton deletButtonInscripcion;
    private JComboBox comboBoxIDCurso;

    private List<String> listaCursos;
    private List<String> listaCatedraticos;

    private DefaultListModel<String> estudiantesModel;
    private DefaultListModel<String> cursosModel;

    private DefaultListModel<String> inscripcionesModel;
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                Graphic2 gui = new Graphic2();
//                gui.setVisible(true);
//            }
//        });
//    }


    public Graphic2() {
        super("");
        setContentPane(panel2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        pack();
        setVisible(true);

        estudiantesModel = new DefaultListModel<>();
        listE.setModel(estudiantesModel);

        cursosModel = new DefaultListModel<>();
        listC.setModel(cursosModel);

        inscripcionesModel = new DefaultListModel<>();
        listI.setModel(inscripcionesModel);


        // Llenar la lista de cursos
        listaCursos = new ArrayList<>();
        listaCursos.add("Matematicas");
        listaCursos.add("Calculo");
        listaCursos.add("Fisica");
        listaCursos.add("Programacion");
        for (String curso : listaCursos) {
            SCurso.addItem(curso);
            ActCurso.addItem(curso);
        }

        // Llenar la lista de catedráticos
        listaCatedraticos = new ArrayList<>();
        listaCatedraticos.add("Pepito");
        listaCatedraticos.add("Juanito");
        listaCatedraticos.add("Chepito");
        listaCatedraticos.add("Pablito");
        for (String catedratico : listaCatedraticos) {
            SCatedratico.addItem(catedratico);
            ActProfesor.addItem(catedratico);
        }
        salirButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        crearButtonE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = InombreE.getText();
                String apellido = IApellidoE.getText();
                String email = IMailE.getText();

                EstudiantesClass estudiante = new EstudiantesClass();
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                estudiante.setEmail(email);

                // Guardar el estudiante en la base de datos
                SessionFactory sessionFactory = HibernateConexion.getSessionFactory();
                try (Session session = sessionFactory.openSession()) {
                    session.beginTransaction();
                    session.save(estudiante);
                    session.getTransaction().commit();
                    session.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Manejar cualquier excepción que pueda ocurrir al guardar el estudiante
                }
                JOptionPane.showMessageDialog(null, "El estudiante se ha creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);


            }
        });


        ACurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCurso = (String) SCurso.getSelectedItem();
                String profesor = (String) SCatedratico.getSelectedItem();

                CursosClass curso = new CursosClass();
                curso.setNombreCurso(nombreCurso);
                curso.setProfesor(profesor);

                // Guardar el curso en la base de datos
                SessionFactory sessionFactory = HibernateConexion.getSessionFactory();
                try (Session session = sessionFactory.openSession()) {
                    session.beginTransaction();
                    session.save(curso);
                    session.getTransaction().commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Manejar cualquier excepción que pueda ocurrir al guardar el curso
                }
                JOptionPane.showMessageDialog(null, "El curso se ha creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }


        });
        ACursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
// Obtener las ID de estudiante y curso ingresadas
                int idEstudiante = Integer.parseInt(IIdE.getText());
                int idCurso = Integer.parseInt(IIdC.getText());

                // Verificar si el ID de estudiante existe en la base de datos
                SessionFactory sessionFactory = HibernateConexion.getSessionFactory();
                try (Session session = sessionFactory.openSession()) {
                    EstudiantesClass estudiante = session.get(EstudiantesClass.class, idEstudiante);
                    if (estudiante == null) {
                        JOptionPane.showMessageDialog(null, "El ID de estudiante no existe. Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del método sin crear la inscripción
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Manejar cualquier excepción que pueda ocurrir al verificar el ID de estudiante
                    return; // Salir del método sin crear la inscripción
                }

                // Verificar si el ID de curso existe en la base de datos
                try (Session session = sessionFactory.openSession()) {
                    CursosClass curso = session.get(CursosClass.class, idCurso);
                    if (curso == null) {
                        JOptionPane.showMessageDialog(null, "El ID de curso no existe. Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del método sin crear la inscripción
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Manejar cualquier excepción que pueda ocurrir al verificar el ID de curso
                    return; // Salir del método sin crear la inscripción
                }

                // Crear una nueva instancia de InscripcionesClass
                InscripcionesClass inscripcion = new InscripcionesClass();

                // Asignar las ID de estudiante y curso a la instancia de InscripcionesClass
                inscripcion.setIdEstudiante(idEstudiante);
                inscripcion.setIdCurso(idCurso);

                // Establecer la fecha de inscripción actual
                inscripcion.setFechaInscripcion(new java.sql.Date(System.currentTimeMillis()));

                // Guardar la instancia de InscripcionesClass en la base de datos
                try (Session session = sessionFactory.openSession()) {
                    session.beginTransaction();
                    session.save(inscripcion);
                    session.getTransaction().commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Manejar cualquier excepción que pueda ocurrir al guardar la inscripción
                    return; // Salir del método sin crear la inscripción
                }

                // Mostrar un mensaje de éxito
                JOptionPane.showMessageDialog(null, "La inscripción se ha creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }


        });

        ButtonME.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                        // Obtener el ID del estudiante ingresado
                        int idEstudiante = Integer.parseInt(MostrarEstudiantes.getText());

                        // Verificar si el estudiante existe en la base de datos
                        SessionFactory sessionFactory = HibernateConexion.getSessionFactory();
                        try (Session session = sessionFactory.openSession()) {
                            EstudiantesClass estudiante = session.get(EstudiantesClass.class, idEstudiante);
                            if (estudiante == null) {
                                JOptionPane.showMessageDialog(null, "El ID de estudiante no existe. Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                                return; // Salir del método si el estudiante no existe
                            }

                            // Crear la cadena de texto con la información del estudiante
                            String infoEstudiante = estudiante.getNombre() + " " + estudiante.getApellido() + " | " + estudiante.getEmail();

                            // Verificar si ya hay un estudiante en el JList
                            if (estudiantesModel.isEmpty()) {
                                // No hay estudiantes en el JList, agregar la información del estudiante
                                estudiantesModel.addElement(infoEstudiante);
                            } else {
                                // Reemplazar la información del estudiante en el JList
                                estudiantesModel.set(0, infoEstudiante);
                            }

                            // Actualizar la interfaz gráfica
                            listE.setModel(estudiantesModel);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            // Manejar cualquier excepción que pueda ocurrir al obtener el estudiante
                        }
            }


        });
        ButtonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el ID del curso ingresado
                int idCurso = Integer.parseInt(MostrarCursos.getText());

                // Verificar si el curso existe en la base de datos
                SessionFactory sessionFactory = HibernateConexion.getSessionFactory();
                try (Session session = sessionFactory.openSession()) {
                    CursosClass cursos = session.get(CursosClass.class, idCurso);
                    if (cursos == null) {
                        JOptionPane.showMessageDialog(null, "El ID de curso no existe. Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del método si el estudiante no existe
                    }

                    // Crear la cadena de texto con la información del curso
                    String infoCursos = cursos.getNombreCurso() + " | " + cursos.getProfesor();

                    // Verificar si ya hay un curso en el JList
                    if (cursosModel.isEmpty()) {
                        // No hay estudiantes en el JList, agregar la información del estudiante
                        cursosModel.addElement(infoCursos);
                    } else {
                        // Reemplazar la información del curso en el JList
                        cursosModel.set(0, infoCursos);
                    }

                    // Actualizar la interfaz gráfica
                    listC.setModel(cursosModel);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Manejar cualquier excepción que pueda ocurrir al obtener el estudiante
                }
            }
        });
        buttonMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Obtener el ID del curso ingresado
                int idCurso = Integer.parseInt(IIdC.getText());

                // Obtener el curso desde la base de datos
                SessionFactory sessionFactory = HibernateConexion.getSessionFactory();
                try (Session session = sessionFactory.openSession()) {
                    CursosClass curso = session.get(CursosClass.class, idCurso);
                    if (curso != null) {
                        cursosModel.clear();
                        cursosModel.addElement(curso.getNombreCurso() + " (" + curso.getProfesor() + ")");
                    } else {
                        JOptionPane.showMessageDialog(null, "Curso no encontrado");
                    }
                    session.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Manejar cualquier excepción que pueda ocurrir al obtener el curso
                }
            }
        });

        buttonMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el ID de la inscripción ingresada
                int idInscripcion = Integer.parseInt(MostrarInscripcion.getText());

                // Obtener la información de la inscripción desde la base de datos
                SessionFactory sessionFactory = HibernateConexion.getSessionFactory();
                try (Session session = sessionFactory.openSession()) {
                    InscripcionesClass inscripcion = session.get(InscripcionesClass.class, idInscripcion);

                    if (inscripcion != null) {
                        // Obtener la información del estudiante relacionado con la inscripción
                        EstudiantesClass estudiante = inscripcion.getEstudiantesByIdEstudiante();

                        // Obtener la información del curso relacionado con la inscripción
                        CursosClass curso = inscripcion.getCursosByIdCurso();

                        if (estudiante != null && curso != null) {
                            // Crear la cadena de texto con la información a mostrar
                            String infoEstudiante = "ID Estudiante: " + estudiante.getIdEstudiante() + " | " +
                                    "Nombre: " + estudiante.getNombre() + " | " +
                                    "Apellido: " + estudiante.getApellido() + " | " +
                                    "Correo: " + estudiante.getEmail();

                            String infoCurso = "ID Curso: " + curso.getIdCurso() + " | " +
                                    "Nombre Curso: " + curso.getNombreCurso() + " | " +
                                    "Profesor: " + curso.getProfesor();

                            // Limpiar la lista de inscripciones y agregar la información de la inscripción
                            inscripcionesModel.clear();
                            inscripcionesModel.addElement(infoEstudiante);
                            inscripcionesModel.addElement(""); // Espacio en blanco
                            inscripcionesModel.addElement(infoCurso);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró información relacionada con la inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Inscripción no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Manejar cualquier excepción que pueda ocurrir al obtener la inscripción
                }
            }
        });

        actualizarButtonEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                    // Obtén el ID del estudiante a actualizar
                    int idEstudiante = Integer.parseInt(ActIdE.getText());

                    // Verifica si el estudiante existe en la base de datos
                    SessionFactory sessionFactory = HibernateConexion.getSessionFactory();
                    try (Session session = sessionFactory.openSession()) {
                        EstudiantesClass estudiante = session.get(EstudiantesClass.class, idEstudiante);
                        if (estudiante == null) {
                            JOptionPane.showMessageDialog(null, "El ID de estudiante no existe. Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Salir del método si el estudiante no existe
                        }

                        // Obtén el nuevo nombre, apellido y correo electrónico
                        String nuevoNombre = ActNombre.getText();
                        String nuevoApellido = ActApellido.getText();
                        String nuevoEmail = ActGmail.getText();

                        // Actualiza los campos del estudiante
                        estudiante.setNombre(nuevoNombre);
                        estudiante.setApellido(nuevoApellido);
                        estudiante.setEmail(nuevoEmail);

                        // Guarda la actualización en la base de datos
                        session.beginTransaction();
                        session.update(estudiante);
                        session.getTransaction().commit();

                        // Muestra un mensaje de éxito
                        JOptionPane.showMessageDialog(null, "La información del estudiante ha sido actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        // Manejar cualquier excepción que pueda ocurrir durante la actualización
                        JOptionPane.showMessageDialog(null, "Error al actualizar la información del estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

            }
        });
        actualizarButtonCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int idCurso = Integer.parseInt(ActIdC.getText());

                // Verifica si el curso existe en la base de datos
                SessionFactory sessionFactory = HibernateConexion.getSessionFactory();
                try (Session session = sessionFactory.openSession()) {
                    CursosClass curso = session.get(CursosClass.class, idCurso);
                    if (curso == null) {
                        JOptionPane.showMessageDialog(null, "El ID de curso no existe. Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del método si el curso no existe
                    }

                    // Obtén el nuevo nombre del curso
                    String nuevoNombreCurso = ActCurso.getSelectedItem().toString();

                    // Obtén el nuevo profesor del curso
                    String nuevoProfesor = ActProfesor.getSelectedItem().toString();

                    // Actualiza los campos del curso
                    curso.setNombreCurso(nuevoNombreCurso);
                    curso.setProfesor(nuevoProfesor);

                    // Guarda la actualización en la base de datos
                    session.beginTransaction();
                    session.update(curso);
                    session.getTransaction().commit();

                    // Muestra un mensaje de éxito
                    JOptionPane.showMessageDialog(null, "La información del curso ha sido actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Manejar cualquier excepción que pueda ocurrir durante la actualización
                    JOptionPane.showMessageDialog(null, "Error al actualizar la información del curso.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        actualizarButtonInscripcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Obtén el ID de la inscripción a actualizar
                int idInscripcion = Integer.parseInt(ActIdI.getText());

                // Verifica si la inscripción existe en la base de datos
                SessionFactory sessionFactory = HibernateConexion.getSessionFactory();
                try (Session session = sessionFactory.openSession()) {
                    InscripcionesClass inscripcion = session.get(InscripcionesClass.class, idInscripcion);
                    if (inscripcion == null) {
                        JOptionPane.showMessageDialog(null, "El ID de inscripción no existe. Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del método si la inscripción no existe
                    }

                    // Obtén el nuevo ID de estudiante
                    int nuevoIdEstudiante = Integer.parseInt(ActIdEstudiante.getText());

                    // Verifica si el estudiante existe en la base de datos
                    EstudiantesClass estudiante = session.get(EstudiantesClass.class, nuevoIdEstudiante);
                    if (estudiante == null) {
                        JOptionPane.showMessageDialog(null, "El ID de estudiante no existe. Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del método si el estudiante no existe
                    }

                    // Obtén el nuevo ID de curso
                    int nuevoIdCurso = Integer.parseInt(ActIdCurso.getText());

                    // Verifica si el curso existe en la base de datos
                    CursosClass curso = session.get(CursosClass.class, nuevoIdCurso);
                    if (curso == null) {
                        JOptionPane.showMessageDialog(null, "El ID de curso no existe. Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del método si el curso no existe
                    }

                    // Actualiza los campos de la inscripción
                    inscripcion.setIdEstudiante(nuevoIdEstudiante);
                    inscripcion.setIdCurso(nuevoIdCurso);

                    // Guarda la actualización en la base de datos
                    session.beginTransaction();
                    session.update(inscripcion);
                    session.getTransaction().commit();

                    // Muestra un mensaje de éxito
                    JOptionPane.showMessageDialog(null, "La información del curso ha sido actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Manejar cualquier excepción que pueda ocurrir durante la actualización
                    JOptionPane.showMessageDialog(null, "Error al actualizar la información del curso.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        deletButtonEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    // Obtén el ID del estudiante a eliminar
                    int idEstudiante = Integer.parseInt(DeletEstudiante.getText());

                    // Verifica si el estudiante existe en la base de datos
                    SessionFactory sessionFactory = HibernateConexion.getSessionFactory();
                    try (Session session = sessionFactory.openSession()) {
                        EstudiantesClass estudiante = session.get(EstudiantesClass.class, idEstudiante);
                        if (estudiante == null) {
                            JOptionPane.showMessageDialog(null, "El ID de estudiante no existe. Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Salir del método si el estudiante no existe
                        }

                        // Elimina el estudiante de la base de datos
                        session.beginTransaction();
                        session.delete(estudiante);
                        session.getTransaction().commit();

                        // Muestra un mensaje de éxito
                        JOptionPane.showMessageDialog(null, "El estudiante ha sido eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        // Manejar cualquier excepción que pueda ocurrir durante la eliminación
                        JOptionPane.showMessageDialog(null, "Error al eliminar el estudiante.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
            }
        });
        deletButtonCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idCurso = Integer.parseInt(DeletCurso.getText());

                // Verifica si el curso existe en la base de datos
                SessionFactory sessionFactory = HibernateConexion.getSessionFactory();
                try (Session session = sessionFactory.openSession()) {
                    CursosClass curso = session.get(CursosClass.class, idCurso);
                    if (curso == null) {
                        JOptionPane.showMessageDialog(null, "El ID del Curso no existe. Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del método si el Curso no existe
                    }

                    // Elimina el estudiante de la base de datos
                    session.beginTransaction();
                    session.delete(curso);
                    session.getTransaction().commit();

                    // Muestra un mensaje de éxito
                    JOptionPane.showMessageDialog(null, "El Curso ha sido eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Manejar cualquier excepción que pueda ocurrir durante la eliminación
                    JOptionPane.showMessageDialog(null, "Error al eliminar el Curso.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        deletButtonInscripcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int idInscripcion = Integer.parseInt(DeletInscripcion.getText());

                // Verifica si el curso existe en la base de datos
                SessionFactory sessionFactory = HibernateConexion.getSessionFactory();
                try (Session session = sessionFactory.openSession()) {
                    InscripcionesClass inscripcion = session.get(InscripcionesClass.class, idInscripcion);
                    if (inscripcion == null) {
                        JOptionPane.showMessageDialog(null, "El ID de Inscripcion no existe. Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del método si la inscripcion no existe
                    }

                    // Elimina el estudiante de la base de datos
                    session.beginTransaction();
                    session.delete(inscripcion);
                    session.getTransaction().commit();

                    // Muestra un mensaje de éxito
                    JOptionPane.showMessageDialog(null, "La Inscripcion ha sido eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Manejar cualquier excepción que pueda ocurrir durante la eliminación
                    JOptionPane.showMessageDialog(null, "Error al eliminar la Inscripcion.", "Error", JOptionPane.ERROR_MESSAGE);
                }




            }
        });
    }


}
