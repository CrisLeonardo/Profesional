package org.example.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.*;

public class InscripcionesClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripcion")

    private int idInscripcion;
    @Column(name = "id_estudiante")
    private Integer idEstudiante;
    @Column(name = "id_curso")
    private Integer idCurso;
    @Column(name = "id_inscripcion")
    private Date fechaInscripcion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante", insertable = false, updatable = false)
    private EstudiantesClass estudiantesByIdEstudiante;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso", insertable = false, updatable = false)
    private CursosClass cursosByIdCurso;

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscripcionesClass that = (InscripcionesClass) o;
        return idInscripcion == that.idInscripcion && Objects.equals(idEstudiante, that.idEstudiante) && Objects.equals(idCurso, that.idCurso) && Objects.equals(fechaInscripcion, that.fechaInscripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInscripcion, idEstudiante, idCurso, fechaInscripcion);
    }

    public EstudiantesClass getEstudiantesByIdEstudiante() {
        return estudiantesByIdEstudiante;
    }

    public void setEstudiantesByIdEstudiante(EstudiantesClass estudiantesByIdEstudiante) {
        this.estudiantesByIdEstudiante = estudiantesByIdEstudiante;
    }

    public CursosClass getCursosByIdCurso() {
        return cursosByIdCurso;
    }

    public void setCursosByIdCurso(CursosClass cursosByIdCurso) {
        this.cursosByIdCurso = cursosByIdCurso;
    }
}
