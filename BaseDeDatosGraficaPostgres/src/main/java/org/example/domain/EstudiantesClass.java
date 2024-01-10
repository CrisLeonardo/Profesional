package org.example.domain;

import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name = "estudiantes")
public class EstudiantesClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_estudiante")
    private int idEstudiante;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "email")
    private String email;

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstudiantesClass that = (EstudiantesClass) o;
        return idEstudiante == that.idEstudiante && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstudiante, nombre, apellido, email);
    }
}
