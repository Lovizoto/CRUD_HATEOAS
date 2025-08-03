package br.com.lovizoto.regesc.repository;


import br.com.lovizoto.regesc.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    Interface extendendo a interface CrudRepository
    com classe que estamos criando um repositorio de crud
     e o tipo da chave primária desta classe

     //Verificar o Uso do CrudRepository e do JPARepository
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {}
