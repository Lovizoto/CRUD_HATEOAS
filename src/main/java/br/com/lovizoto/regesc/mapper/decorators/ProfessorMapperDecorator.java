package br.com.lovizoto.regesc.mapper.decorators;

import br.com.lovizoto.regesc.data.dto.ProfessorDTO;
import br.com.lovizoto.regesc.data.model.Disciplina;
import br.com.lovizoto.regesc.data.model.Professor;
import br.com.lovizoto.regesc.exception.handler.ResourceNotFoundException;
import br.com.lovizoto.regesc.mapper.ProfessorMapper;
import br.com.lovizoto.regesc.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.stream.Collectors;

public class ProfessorMapperDecorator implements ProfessorMapper {

    private final ProfessorMapper professorMapper;
    private final DisciplinaRepository disciplinaRepository;

    public ProfessorMapperDecorator(@Qualifier("professorMapper") ProfessorMapper professorMapper, DisciplinaRepository disciplinaRepository) {
        this.professorMapper = professorMapper;
        this.disciplinaRepository = disciplinaRepository;
    }

    @Override
    public ProfessorDTO toDto(Professor entity) {
        ProfessorDTO dto =  professorMapper.toDto(entity);
        if (entity.getDisciplinas() != null) {
            dto.setDisciplinas(entity.getDisciplinas().stream()
                    .map(Disciplina :: getNome)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    @Override
    public Professor toEntity(ProfessorDTO dto) {
        Professor entity =  professorMapper.toEntity(dto);
        if (dto.getDisciplinas() != null) {
            entity.setDisciplinas(dto.getDisciplinas().stream()
                    .map(nome -> disciplinaRepository.findByNome(nome)
                            .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada " + nome)))
                            .collect(Collectors.toSet()));
        }
        return entity;
    }
}
