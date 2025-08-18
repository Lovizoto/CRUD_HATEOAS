package br.com.lovizoto.regesc.mapper.decorators;

import br.com.lovizoto.regesc.data.dto.DisciplinaDTO;
import br.com.lovizoto.regesc.data.model.Disciplina;
import br.com.lovizoto.regesc.data.model.Professor;
import br.com.lovizoto.regesc.exception.handler.ResourceNotFoundException;
import br.com.lovizoto.regesc.mapper.DisciplinaMapper;
import br.com.lovizoto.regesc.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Qualifier;


public abstract class DisciplinaMapperDecorator implements DisciplinaMapper {

    private final DisciplinaMapper disciplinaMapper;
    private final ProfessorRepository professorRepository;

    public DisciplinaMapperDecorator(@Qualifier("disciplinaMapper") DisciplinaMapper disciplinaMapper, ProfessorRepository professorRepository) {
        this.disciplinaMapper = disciplinaMapper;
        this.professorRepository = professorRepository;
    }

    @Override
    public DisciplinaDTO toDTO(Disciplina entity) {
        DisciplinaDTO dto = disciplinaMapper.toDTO(entity);
        if (entity.getProfessor() != null) {
            dto.setProfessor(entity.getProfessor().getNome());
        }
        return dto;
    }

    @Override
    public Disciplina toEntity(DisciplinaDTO dto) {
        Disciplina entity = disciplinaMapper.toEntity(dto);
        if (dto.getProfessor() != null && !dto.getProfessor().isEmpty()) {
            Professor professor = professorRepository.findByNome(dto.getProfessor())
                    .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado " + dto.getProfessor()));
            entity.setProfessor(professor);
        }
        return entity;
    }

}
