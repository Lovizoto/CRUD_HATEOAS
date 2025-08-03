package br.com.lovizoto.regesc.repository;

import br.com.lovizoto.regesc.model.Aluno;
import br.com.lovizoto.regesc.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {


}
