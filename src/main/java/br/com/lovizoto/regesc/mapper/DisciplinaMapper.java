package br.com.lovizoto.regesc.mapper;

import br.com.lovizoto.regesc.data.dto.DisciplinaDTO;
import br.com.lovizoto.regesc.data.model.Disciplina;
import br.com.lovizoto.regesc.mapper.decorators.DisciplinaMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(DisciplinaMapperDecorator.class)
public interface DisciplinaMapper {

    @Mapping(target = "professor", ignore = true)
    DisciplinaDTO toDTO(Disciplina entity);

    @Mapping(target = "professor", ignore = true)
    @Mapping(target = "alunos", ignore = true)
    Disciplina toEntity(DisciplinaDTO dto);

    List<DisciplinaDTO> toDTOList(List<Disciplina> entityList);
    List<Disciplina> toEntityList(List<DisciplinaDTO> dtoList);

}
