package br.com.lovizoto.regesc.data.dto;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Set;

public class ProfessorDTO extends RepresentationModel<ProfessorDTO> implements Serializable {

    private Long id;
    private String nome;
    private String observacoes;
    private Set<String> disciplinas;

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

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Set<String> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Set<String> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
