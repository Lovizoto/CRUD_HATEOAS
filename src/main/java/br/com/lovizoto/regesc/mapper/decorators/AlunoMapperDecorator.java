package br.com.lovizoto.regesc.mapper.decorators;

import br.com.lovizoto.regesc.data.dto.AlunoDTO;
import br.com.lovizoto.regesc.mapper.AlunoMapper;
import br.com.lovizoto.regesc.mapper.config.StringToDisciplinaConverter;
import br.com.lovizoto.regesc.data.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlunoMapperDecorator implements AlunoMapper {

    private final AlunoMapper delegate;
    private final StringToDisciplinaConverter converter;

    public AlunoMapperDecorator(@Qualifier("alunoMapperImpl") AlunoMapper delegate,
                                StringToDisciplinaConverter converter) {
        this.delegate = delegate;
        this.converter = converter;
    }

    @Override
    public Aluno toEntity(AlunoDTO dto) {
        Aluno aluno = delegate.toEntity(dto);
        return aluno;
    }

    @Override
    public AlunoDTO toDTO(Aluno entity) {
        AlunoDTO dto = delegate.toDTO(entity);
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
