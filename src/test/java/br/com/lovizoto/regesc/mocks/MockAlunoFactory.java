package br.com.lovizoto.regesc.mocks;

import br.com.lovizoto.regesc.data.dto.AlunoDTO;
import br.com.lovizoto.regesc.data.model.Aluno;
import br.com.lovizoto.regesc.data.model.Disciplina;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockAlunoFactory {

    public static Aluno mockAluno() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Gaal Dornick");
        aluno.setIdade(18);

        Set<Disciplina> disciplinas = new HashSet<>();
        disciplinas.add(MockDisciplinaFactory.mockDisciplina());
        aluno.setDisciplinas(disciplinas);

        return aluno;
    }

    public static AlunoDTO mockAlunoDTO() {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(1L);
        alunoDTO.setNomecompleto("Gaal Dornick");
        alunoDTO.setIdade(18);

        Set<String> disciplinas = new HashSet<>();
        disciplinas.add("Psico-História");
        alunoDTO.setDisciplinas(disciplinas);

        return alunoDTO;
    }

    public static List<Aluno> mockAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            Aluno aluno = mockAluno();
            aluno.setId(i);
            alunos.add(aluno);
        }
        return alunos;
    }

    public static List<AlunoDTO> mockAlunosDTO() {
        List<AlunoDTO> alunosDTO = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            AlunoDTO alunoDTO = mockAlunoDTO();
            alunoDTO.setId(i);
            alunosDTO.add(alunoDTO);
        }
        return alunosDTO;
    }


}
