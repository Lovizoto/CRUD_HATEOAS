package br.com.lovizoto.regesc.mapper.decorators;

import br.com.lovizoto.regesc.data.dto.AlunoDTO;
import br.com.lovizoto.regesc.data.model.Disciplina;
import br.com.lovizoto.regesc.exception.handler.ResourceNotFoundException;
import br.com.lovizoto.regesc.mapper.AlunoMapper;

import br.com.lovizoto.regesc.data.model.Aluno;
import br.com.lovizoto.regesc.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



import java.util.List;
import java.util.stream.Collectors;


public abstract class AlunoMapperDecorator implements AlunoMapper {

    @Autowired
    @Qualifier("delegate")
    private AlunoMapper delegate;

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    @Override
    public Aluno toEntity(AlunoDTO dto) {
        Aluno entity = delegate.toEntity(dto);
        if (dto.getDisciplinas() != null) {
            entity.setDisciplinas(dto.getDisciplinas().stream()
                    .map(nome -> disciplinaRepository.findByNome(nome)
                            .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada " + nome)))
                    .collect(Collectors.toSet()));
        }
        return entity;
    }

    @Override
    public AlunoDTO toDTO(Aluno entity) {
        AlunoDTO dto = delegate.toDTO(entity);
        if (entity.getDisciplinas() != null) {
            dto.setDisciplinas(entity.getDisciplinas().stream()
                    .map(Disciplina :: getNome)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    @Override
    public List<AlunoDTO> toDTOList(List<Aluno> entityList) {
        return entityList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<Aluno> toEntityList(List<AlunoDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }



}
