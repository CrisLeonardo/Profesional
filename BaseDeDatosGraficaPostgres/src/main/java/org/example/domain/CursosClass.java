package org.example.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "cursos")
public class CursosClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private int idCurso;
    @Column(name = "nombre_curso")
    private String nombreCurso;

    @Column(name = "profesor")
    private String profesor;

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursosClass that = (CursosClass) o;
        return idCurso == that.idCurso && Objects.equals(nombreCurso, that.nombreCurso) && Objects.equals(profesor, that.profesor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurso, nombreCurso, profesor);
    }
}
