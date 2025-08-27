package br.com.lovizoto.regesc.mocks;

import br.com.lovizoto.regesc.data.dto.DisciplinaDTO;
import br.com.lovizoto.regesc.data.model.Disciplina;

public class MockDisciplinaFactory {

    public static Disciplina mockDisciplina() {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(1L);
        disciplina.setNome("Psico-História");
        disciplina.setSemestre(1);

        disciplina.setProfessor(MockProfessorFactory.mockProfessor());

        return disciplina;
    }

    public static DisciplinaDTO mockDisciplinaDTO() {
        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setId(1L);
        disciplinaDTO.setNome("Psico-História");
        disciplinaDTO.setSemestre(1);
        disciplinaDTO.setProfessor("Hari Seldon");
        return disciplinaDTO;
    }

}
