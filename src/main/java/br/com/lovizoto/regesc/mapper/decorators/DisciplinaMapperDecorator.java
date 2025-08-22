package br.com.lovizoto.regesc.mapper.decorators;

import br.com.lovizoto.regesc.data.dto.DisciplinaDTO;
import br.com.lovizoto.regesc.data.model.Disciplina;
import br.com.lovizoto.regesc.data.model.Professor;
import br.com.lovizoto.regesc.exception.handler.ResourceNotFoundException;
import br.com.lovizoto.regesc.mapper.DisciplinaMapper;
import br.com.lovizoto.regesc.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.List;
import java.util.stream.Collectors;


public abstract class DisciplinaMapperDecorator implements DisciplinaMapper {

    @Autowired
    @Qualifier("delegate")
    private DisciplinaMapper delegate;

    @Autowired
    private ProfessorRepository professorRepository;


    @Override
    public DisciplinaDTO toDTO(Disciplina entity) {
        DisciplinaDTO dto = delegate.toDTO(entity);
        if (entity.getProfessor() != null) {
            dto.setProfessor(entity.getProfessor().getNome());
        }
        return dto;
    }

    @Override
    public Disciplina toEntity(DisciplinaDTO dto) {
        Disciplina entity = delegate.toEntity(dto);
        if (dto.getProfessor() != null && !dto.getProfessor().isEmpty()) {
            Professor professor = professorRepository.findByNome(dto.getProfessor())
                    .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado " + dto.getProfessor()));
            entity.setProfessor(professor);
        }
        return entity;
    }

    @Override
    public List<DisciplinaDTO> toDTOList(List<Disciplina> entityList) {
        return entityList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Disciplina> toEntityList(List<DisciplinaDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
