package br.com.lovizoto.regesc.mapper;

import br.com.lovizoto.regesc.data.dto.AlunoDTO;
import br.com.lovizoto.regesc.data.model.Aluno;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-17T19:38:48-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class AlunoMapperImpl implements AlunoMapper {

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
}
