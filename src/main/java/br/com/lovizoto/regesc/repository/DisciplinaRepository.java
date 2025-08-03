package br.com.lovizoto.regesc.repository;

import br.com.lovizoto.regesc.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina,Long> {

   List<Disciplina> findByProfessorId(Long professorId); //por padrão o java entende que é public e abstract por se tratar de interface
   Optional<Disciplina> findByNome(String nome);
}
