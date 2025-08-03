package br.com.lovizoto.regesc.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private Integer semestre;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = true) //somente na classe detentora, isso evita de criar uma tabela intermediária
    @JsonBackReference
//    @JsonIgnoreProperties("disciplinas")
    private Professor professor;

    @JsonBackReference
    @ManyToMany(mappedBy = "disciplinas", fetch = FetchType.EAGER)
    private Set<Aluno> alunos;

    public Disciplina() {
    }

    public Disciplina(String nome, Integer semestre) {
        this.nome = nome;
        this.semestre = semestre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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


    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }


    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }
}
