package br.com.lovizoto.regesc.mapper;


import br.com.lovizoto.regesc.data.dto.AlunoDTO;
import br.com.lovizoto.regesc.mapper.decorators.AlunoMapperDecorator;
import br.com.lovizoto.regesc.model.Aluno;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(AlunoMapperDecorator.class)
public interface AlunoMapper {

    @Mappings({
            @Mapping(source = "nomecompleto", target = "nome"),
            @Mapping(target = "disciplinas", ignore = true)
    })
    Aluno toEntity(AlunoDTO dto);

    @Mappings({
            @Mapping(source = "nome", target = "nomecompleto"),
            @Mapping(target = "disciplinas", ignore = true)
    })
    AlunoDTO toDTO(Aluno entity);

    List<AlunoDTO> toDTOList(List<Aluno> entityList);
    List<Aluno> toEntityList(List<AlunoDTO> dtoList);
}


