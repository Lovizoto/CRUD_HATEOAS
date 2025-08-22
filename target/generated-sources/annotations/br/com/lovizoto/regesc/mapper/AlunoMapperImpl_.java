package br.com.lovizoto.regesc.mapper;

import br.com.lovizoto.regesc.data.dto.AlunoDTO;
import br.com.lovizoto.regesc.data.model.Aluno;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T12:08:43-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class AlunoMapperImpl_ implements AlunoMapper {

    @Override
    public Aluno toEntity(AlunoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Aluno aluno = new Aluno();

        aluno.setNome( dto.getNomecompleto() );
        aluno.setId( dto.getId() );
        aluno.setIdade( dto.getIdade() );

        return aluno;
    }

    @Override
    public AlunoDTO toDTO(Aluno entity) {
        if ( entity == null ) {
            return null;
        }

        AlunoDTO alunoDTO = new AlunoDTO();

        alunoDTO.setNomecompleto( entity.getNome() );
        alunoDTO.setId( entity.getId() );
        alunoDTO.setIdade( entity.getIdade() );

        return alunoDTO;
    }

    @Override
    public List<AlunoDTO> toDTOList(List<Aluno> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AlunoDTO> list = new ArrayList<AlunoDTO>( entityList.size() );
        for ( Aluno aluno : entityList ) {
            list.add( toDTO( aluno ) );
        }

        return list;
    }

    @Override
    public List<Aluno> toEntityList(List<AlunoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Aluno> list = new ArrayList<Aluno>( dtoList.size() );
        for ( AlunoDTO alunoDTO : dtoList ) {
            list.add( toEntity( alunoDTO ) );
        }

        return list;
    }
}
