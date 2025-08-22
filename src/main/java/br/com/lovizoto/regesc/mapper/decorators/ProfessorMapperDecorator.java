package br.com.lovizoto.regesc.mapper.decorators;

import br.com.lovizoto.regesc.data.dto.ProfessorDTO;
import br.com.lovizoto.regesc.data.model.Disciplina;
import br.com.lovizoto.regesc.data.model.Professor;
import br.com.lovizoto.regesc.exception.handler.ResourceNotFoundException;
import br.com.lovizoto.regesc.mapper.ProfessorMapper;
import br.com.lovizoto.regesc.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.List;
import java.util.stream.Collectors;


public abstract class ProfessorMapperDecorator implements ProfessorMapper {

    @Autowired
    @Qualifier("delegate")
    private ProfessorMapper delegate;

    @Autowired
    private DisciplinaRepository disciplinaRepository;



    @Override
    public ProfessorDTO toDto(Professor entity) {
        ProfessorDTO dto =  delegate.toDto(entity);
        if (entity.getDisciplinas() != null) {
            dto.setDisciplinas(entity.getDisciplinas().stream()
                    .map(Disciplina :: getNome)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    @Override
    public Professor toEntity(ProfessorDTO dto) {
        Professor entity =  delegate.toEntity(dto);
        if (dto.getDisciplinas() != null) {
            entity.setDisciplinas(dto.getDisciplinas().stream()
                    .map(nome -> disciplinaRepository.findByNome(nome)
                            .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada " + nome)))
                            .collect(Collectors.toSet()));
        }
        return entity;
    }

    @Override
    public List<ProfessorDTO> toDTOList(List<Professor> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Professor> toEntityList(List<ProfessorDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
