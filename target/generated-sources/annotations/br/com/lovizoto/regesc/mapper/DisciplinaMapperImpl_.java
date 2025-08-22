package br.com.lovizoto.regesc.mapper;

import br.com.lovizoto.regesc.data.dto.DisciplinaDTO;
import br.com.lovizoto.regesc.data.model.Disciplina;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T12:08:44-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class DisciplinaMapperImpl_ implements DisciplinaMapper {

    @Override
    public DisciplinaDTO toDTO(Disciplina entity) {
        if ( entity == null ) {
            return null;
        }

        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();

        disciplinaDTO.setId( entity.getId() );
        disciplinaDTO.setNome( entity.getNome() );
        disciplinaDTO.setSemestre( entity.getSemestre() );

        return disciplinaDTO;
    }

    @Override
    public Disciplina toEntity(DisciplinaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Disciplina disciplina = new Disciplina();

        if ( dto.getId() != null ) {
            disciplina.setId( dto.getId() );
        }
        disciplina.setNome( dto.getNome() );
        disciplina.setSemestre( dto.getSemestre() );

        return disciplina;
    }

    @Override
    public List<DisciplinaDTO> toDTOList(List<Disciplina> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DisciplinaDTO> list = new ArrayList<DisciplinaDTO>( entityList.size() );
        for ( Disciplina disciplina : entityList ) {
            list.add( toDTO( disciplina ) );
        }

        return list;
    }

    @Override
    public List<Disciplina> toEntityList(List<DisciplinaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Disciplina> list = new ArrayList<Disciplina>( dtoList.size() );
        for ( DisciplinaDTO disciplinaDTO : dtoList ) {
            list.add( toEntity( disciplinaDTO ) );
        }

        return list;
    }
}
