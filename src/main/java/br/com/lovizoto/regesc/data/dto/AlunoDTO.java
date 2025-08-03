package br.com.lovizoto.regesc.data.dto;



import java.io.Serializable;
import java.util.Set;


public class AlunoDTO implements Serializable {

    private Long id;
    private String nomecompleto;
    private Integer idade;
    private Set<String> disciplinas;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomecompleto() {
        return nomecompleto;
    }

    public void setNomecompleto(String nomecompleto) {
        this.nomecompleto = nomecompleto;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Set<String> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Set<String> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
