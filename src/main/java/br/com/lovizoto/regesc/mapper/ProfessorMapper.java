package br.com.lovizoto.regesc.mapper;

import br.com.lovizoto.regesc.data.dto.ProfessorDTO;
import br.com.lovizoto.regesc.data.model.Professor;
import br.com.lovizoto.regesc.mapper.decorators.ProfessorMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(ProfessorMapperDecorator.class)
public interface ProfessorMapper {


    @Mappings({
            @Mapping(source = "prontuario", target = "observacoes"),
            @Mapping(target = "disciplinas", ignore = true)
    })
    ProfessorDTO toDto(Professor entity);


    @Mappings({
            @Mapping(source = "observacoes", target = "prontuario"),
            @Mapping(target = "disciplinas", ignore = true)
    })
    Professor toEntity(ProfessorDTO dto);

    List<ProfessorDTO> toDTOList(List<Professor> entityList);
    List<Professor> toEntityList(List<ProfessorDTO> dtoList);

}
