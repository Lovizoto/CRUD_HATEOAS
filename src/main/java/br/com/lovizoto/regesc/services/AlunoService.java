package br.com.lovizoto.regesc.services;

import br.com.lovizoto.regesc.data.dto.AlunoDTO;
import br.com.lovizoto.regesc.exception.handler.ResourceNotFoundException;
import br.com.lovizoto.regesc.mapper.AlunoMapper;


import br.com.lovizoto.regesc.model.Aluno;
import br.com.lovizoto.regesc.repository.AlunoRepository;
import br.com.lovizoto.regesc.repository.DisciplinaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private Logger logger = LoggerFactory.getLogger(AlunoService.class.getName());


    private final AlunoRepository alunoRepository;


    private final DisciplinaRepository disciplinaRepository;


    private final AlunoMapper alunoMapper;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository, AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.alunoMapper = alunoMapper;
    }

    //VERSÃO SEM DTO

//    public List<Aluno> findAll() {
//        logger.info("Buscando todos alunos");
//        return alunoRepository.findAll();
//    }
//
//    public Aluno findById(Long id) {
//        logger.info("Buscando aluno por id");
//        return alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found with id " + id));
//    }
//
//    public Aluno criarAluno(Aluno aluno) {
//        logger.info("Criando aluno");
//        return alunoRepository.save(aluno);
//    }
//
//    public Aluno atualizarAluno(Aluno aluno) {
//        logger.info("Atualizando aluno");
//        return alunoRepository.save(aluno);
//    }
//
//    public Aluno vincularDisciplina(Long idAluno, Long idDisciplina) {
//
//        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new ResourceNotFoundException("No record found with id " + idAluno));
//        Disciplina disciplina = disciplinaRepository.findById(idDisciplina).orElseThrow(() -> new ResourceNotFoundException("No record found with id " + idAluno));
//
//        aluno.getDisciplinas().add(disciplina);
//        return alunoRepository.save(aluno);
//
//    }


    /*
        VERSÃO COM DTO
     */

    public List<AlunoDTO> findAll() {
        logger.info("Buscando todos alunos");
        return alunoMapper.toDTOList(alunoRepository.findAll());
    }

    public AlunoDTO findById(Long id) {
        logger.info("Buscando aluno por id");

        var entity = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found with id " + id));
        return alunoMapper.toDTO(entity);

    }

    public AlunoDTO criarAluno(AlunoDTO aluno) {
        logger.info("Criando aluno");

        var entity = alunoMapper.toEntity(aluno);
        return alunoMapper.toDTO(alunoRepository.save(entity));
    }

//    public Aluno atualizarAluno(Aluno aluno) {
//        logger.info("Atualizando aluno");
//
//
//
//        return alunoRepository.save(aluno);
//    }
//
//    public Aluno vincularDisciplina(Long idAluno, Long idDisciplina) {
//
//        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new ResourceNotFoundException("No record found with id " + idAluno));
//        Disciplina disciplina = disciplinaRepository.findById(idDisciplina).orElseThrow(() -> new ResourceNotFoundException("No record found with id " + idAluno));
//
//        aluno.getDisciplinas().add(disciplina);
//        return alunoRepository.save(aluno);
//
//    }


}
