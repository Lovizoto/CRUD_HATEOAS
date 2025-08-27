package br.com.lovizoto.regesc.mocks;

import br.com.lovizoto.regesc.data.dto.ProfessorDTO;
import br.com.lovizoto.regesc.data.model.Professor;

public class MockProfessorFactory {

    public static Professor mockProfessor() {
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setNome("Hari Seldon");
        professor.setProntuario("PHelicon");
        return professor;
    }

    public static ProfessorDTO mockProfessorDTO() {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(1L);
        professorDTO.setNome("Hari Seldon");
        professorDTO.setObservacoes("PHelicon");
        return professorDTO;
    }

}
