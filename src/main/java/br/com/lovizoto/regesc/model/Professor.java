package br.com.lovizoto.regesc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Entity
@Table(name = "professores")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String prontuario;


    @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
    @JsonManagedReference //gera um loop infinito no json por isso a anotação
    private List<Disciplina> disciplinas;


    @PreRemove
    public void atualizaDisciplinaOnRemove() {
        for (Disciplina disciplina : this.disciplinas) {
            disciplina.setProfessor(null);
        }
    }


    /*
        Construtor vazio explícito, pois foi criado um com argumentos.
        Caso não tivesse criado um construtor com argumentos, podia manter implícito.
     */
    public Professor() {
    }

    public Professor(String nome, String prontuario) {
        this.nome = nome;
        this.prontuario = prontuario;
    }

    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
