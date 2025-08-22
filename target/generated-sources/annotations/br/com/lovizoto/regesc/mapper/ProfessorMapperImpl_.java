package br.com.lovizoto.regesc.mapper;

import br.com.lovizoto.regesc.data.dto.ProfessorDTO;
import br.com.lovizoto.regesc.data.model.Professor;
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
public class ProfessorMapperImpl_ implements ProfessorMapper {

    @Override
    public ProfessorDTO toDto(Professor entity) {
        if ( entity == null ) {
            return null;
        }

        ProfessorDTO professorDTO = new ProfessorDTO();

        professorDTO.setObservacoes( entity.getProntuario() );
        professorDTO.setId( entity.getId() );
        professorDTO.setNome( entity.getNome() );

        return professorDTO;
    }

    @Override
    public Professor toEntity(ProfessorDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Professor professor = new Professor();

        professor.setProntuario( dto.getObservacoes() );
        professor.setNome( dto.getNome() );
        if ( dto.getId() != null ) {
            professor.setId( dto.getId() );
        }

        return professor;
    }

    @Override
    public List<ProfessorDTO> toDTOList(List<Professor> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProfessorDTO> list = new ArrayList<ProfessorDTO>( entityList.size() );
        for ( Professor professor : entityList ) {
            list.add( toDto( professor ) );
        }

        return list;
    }

    @Override
    public List<Professor> toEntityList(List<ProfessorDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Professor> list = new ArrayList<Professor>( dtoList.size() );
        for ( ProfessorDTO professorDTO : dtoList ) {
            list.add( toEntity( professorDTO ) );
        }

        return list;
    }
}
