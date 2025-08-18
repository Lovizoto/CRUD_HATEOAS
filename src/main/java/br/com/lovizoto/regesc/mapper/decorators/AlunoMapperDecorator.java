package br.com.lovizoto.regesc.mapper.decorators;

import br.com.lovizoto.regesc.data.dto.AlunoDTO;
import br.com.lovizoto.regesc.mapper.AlunoMapper;

import br.com.lovizoto.regesc.data.model.Aluno;
import br.com.lovizoto.regesc.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.List;
import java.util.stream.Collectors;


public class AlunoMapperDecorator implements AlunoMapper {

    private final AlunoMapper alunoMapper;
    private final DisciplinaRepository disciplinaRepository;

    public AlunoMapperDecorator(@Qualifier("alunoMapper") AlunoMapper alunoMapper, DisciplinaRepository disciplinaRepository) {
        this.alunoMapper = alunoMapper;
        this.disciplinaRepository = disciplinaRepository;
    }

    @Override
    public Aluno toEntity(AlunoDTO dto) {
        Aluno aluno = alunoMapper.toEntity(dto);
        return aluno;
    }

    @Override
    public AlunoDTO toDTO(Aluno entity) {
        AlunoDTO dto = alunoMapper.toDTO(entity);
        dto.setDisciplinas(converter.mapDisciplinas(entity.getDisciplinas()));
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
