package br.com.lovizoto.regesc.data.dto;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

public class DisciplinaDTO extends RepresentationModel<DisciplinaDTO> implements Serializable {

    private Long id;
    private String nome;
    private Integer semestre;
    private String professor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
