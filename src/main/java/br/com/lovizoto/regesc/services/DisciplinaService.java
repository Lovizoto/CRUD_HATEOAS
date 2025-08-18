package br.com.lovizoto.regesc.services;


import br.com.lovizoto.regesc.exception.handler.ResourceNotFoundException;
import br.com.lovizoto.regesc.data.model.Aluno;
import br.com.lovizoto.regesc.data.model.Disciplina;
import br.com.lovizoto.regesc.mapper.DisciplinaMapper;
import br.com.lovizoto.regesc.repository.AlunoRepository;
import br.com.lovizoto.regesc.repository.DisciplinaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {


    private Logger logger = LoggerFactory.getLogger(DisciplinaService.class.getName());

    private final DisciplinaRepository disciplinaRepository;
    private final AlunoRepository alunoaRepository;
    private final DisciplinaMapper disciplinaMapper;

    public DisciplinaService(DisciplinaRepository disciplinaRepository, AlunoRepository alunoaRepository, DisciplinaMapper disciplinaMapper) {
        this.disciplinaRepository = disciplinaRepository;
        this.alunoaRepository = alunoaRepository;
        this.disciplinaMapper = disciplinaMapper;
    }

    @Transactional(readOnly = true)
    public List<Disciplina> findAll() {
        logger.info("Buscando todas as disciplinas");
        return disciplinaMapper.toDTOList


        return disciplinaRepository.findAll();
    }

    public Disciplina findById(Long id) {
        logger.info("Buscando disciplina por id: {}", id);
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
