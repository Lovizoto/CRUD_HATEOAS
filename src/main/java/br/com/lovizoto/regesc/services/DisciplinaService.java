package br.com.lovizoto.regesc.services;


import br.com.lovizoto.regesc.data.dto.DisciplinaDTO;
import br.com.lovizoto.regesc.exception.handler.ResourceNotFoundException;
import br.com.lovizoto.regesc.data.model.Aluno;
import br.com.lovizoto.regesc.data.model.Disciplina;
import br.com.lovizoto.regesc.mapper.DisciplinaMapper;
import br.com.lovizoto.regesc.repository.AlunoRepository;
import br.com.lovizoto.regesc.repository.DisciplinaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<DisciplinaDTO> findAll() {
        logger.info("Buscando todas as disciplinas");
        return disciplinaMapper.toDTOList(disciplinaRepository.findAll());
    }

    @Transactional(readOnly = true)
    public DisciplinaDTO findById(Long id) {
        logger.info("Buscando disciplina por id: {}", id);
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o id: " + id));

        return disciplinaMapper.toDTO(disciplina);
    }

    @Transactional
    public DisciplinaDTO create(DisciplinaDTO disciplinaDTO) {
        logger.info("Criando disciplina");
        Disciplina disciplina = disciplinaMapper.toEntity(disciplinaDTO);
        Disciplina disciplinaSalva = disciplinaRepository.save(disciplina);
        return disciplinaMapper.toDTO(disciplinaSalva);
    }

    @Transactional
    public DisciplinaDTO update(Long id, DisciplinaDTO disciplinaDTO) {
        logger.info("Atualizando disciplina com id: {}", disciplinaDTO.getId());
        Disciplina disciplinaExistente = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o id: " + id));
        Disciplina disciplinaAtualizada = disciplinaMapper.toEntity(disciplinaDTO);

        disciplinaAtualizada.setId(disciplinaExistente.getId());

        Disciplina disciplinaSalva = disciplinaRepository.save(disciplinaAtualizada);
        return disciplinaMapper.toDTO(disciplinaSalva);
    }

    @Transactional
    public void delete(Long id) {
        logger.info("Deletando disciplina com id: {}", id);
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o id: " + id));
        disciplinaRepository.delete(disciplina);
    }

    @Transactional
    public DisciplinaDTO vincularAluno(Long idDisciplina, Long idAluno) {
        logger.info("Vinculando aluno {} à disciplina {}", idAluno, idDisciplina);

        Disciplina disciplina = disciplinaRepository.findById(idDisciplina)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o id: " + idDisciplina));

        Aluno aluno = alunoaRepository.findById(idAluno)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o id: " + idAluno));

        disciplina.getAlunos().add(aluno);

        Disciplina disciplinaSalva = disciplinaRepository.save(disciplina);
        return disciplinaMapper.toDTO(disciplinaSalva);
    }





}
