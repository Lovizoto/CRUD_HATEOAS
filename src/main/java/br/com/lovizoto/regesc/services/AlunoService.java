package br.com.lovizoto.regesc.services;

import br.com.lovizoto.regesc.data.dto.AlunoDTO;
import br.com.lovizoto.regesc.exception.handler.ResourceNotFoundException;
import br.com.lovizoto.regesc.mapper.AlunoMapper;
import br.com.lovizoto.regesc.data.model.Aluno;
import br.com.lovizoto.regesc.repository.AlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class AlunoService {

    private final Logger logger = LoggerFactory.getLogger(AlunoService.class.getName());
    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
    }

    @Transactional(readOnly = true)
    public List<AlunoDTO> findAll() {
        logger.info("Buscando todos os alunos");
        List<Aluno> alunos = alunoRepository.findAll();

        return alunoMapper.toDTOList(alunos);
    }

    @Transactional(readOnly = true)
    public AlunoDTO findById(Long id) {
        logger.info("Buscando aluno por id: {}", id);
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado  com o id: " + id));

        return alunoMapper.toDTO(aluno);
    }

    @Transactional
    public AlunoDTO create(AlunoDTO alunoDTO) {
        logger.info("Criando aluno");
        Aluno aluno = alunoMapper.toEntity(alunoDTO);
        Aluno alunoSalvo = alunoRepository.save(aluno);

        return alunoMapper.toDTO(alunoSalvo);
    }

    @Transactional
    public AlunoDTO update(Long id, AlunoDTO alunoDTO) {
        logger.info("Atualizando aluno com id: {}", id);
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o id: " + id));

        Aluno alunoAtualizado = alunoMapper.toEntity(alunoDTO);
        alunoAtualizado.setId(alunoExistente.getId());

        Aluno alunoSalvo = alunoRepository.save(alunoAtualizado);
        return alunoMapper.toDTO(alunoSalvo);
    }

    @Transactional
    public void delete(Long id) {
        logger.info("Deletando aluno com id: {}", id);
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o id: " + id));
        alunoRepository.delete(aluno);
    }


}
