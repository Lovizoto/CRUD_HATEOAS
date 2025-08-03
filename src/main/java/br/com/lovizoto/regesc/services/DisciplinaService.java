package br.com.lovizoto.regesc.services;


import br.com.lovizoto.regesc.exception.handler.ResourceNotFoundException;
import br.com.lovizoto.regesc.model.Aluno;
import br.com.lovizoto.regesc.model.Disciplina;
import br.com.lovizoto.regesc.model.Professor;
import br.com.lovizoto.regesc.repository.AlunoRepository;
import br.com.lovizoto.regesc.repository.DisciplinaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DisciplinaService {


    private Logger logger = LoggerFactory.getLogger(DisciplinaService.class.getName());

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Disciplina> findAll() {
        logger.info("Buscando todas as disciplinas");
        return disciplinaRepository.findAll();
    }

    public Disciplina findById(Long id) {
        logger.info("Buscando disciplina");
        return disciplinaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found with id " + id));
    }

    public Disciplina create(Disciplina disciplina) {
        logger.info("Criando disciplina");
        return disciplinaRepository.save(disciplina);
    }

    public void deleteById(Long id) {
        logger.info("Deletando disciplina");
        disciplinaRepository.deleteById(id);
    }


    public Disciplina vincularDisciplinaAluno(Long idAluno, Long idDisciplina) {

        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new ResourceNotFoundException("No record found with id " + idAluno));
        Disciplina disciplina = disciplinaRepository.findById(idDisciplina).orElseThrow(() -> new ResourceNotFoundException("No record found with id " + idAluno));

        disciplina.getAlunos().add(aluno);
        return disciplinaRepository.save(disciplina);

    }

    public Optional<Disciplina> findByNome(String nome) {
        return disciplinaRepository.findByNome(nome);
    }


}
