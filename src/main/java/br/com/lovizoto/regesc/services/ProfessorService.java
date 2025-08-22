package br.com.lovizoto.regesc.services;

import br.com.lovizoto.regesc.data.dto.ProfessorDTO;
import br.com.lovizoto.regesc.exception.handler.ResourceNotFoundException;
import br.com.lovizoto.regesc.data.model.Professor;
import br.com.lovizoto.regesc.mapper.ProfessorMapper;
import br.com.lovizoto.regesc.repository.ProfessorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/*
    Toda parte da lógica deve estar na classe de serviço (Service)
 */


@Service
public class ProfessorService {

    private final Logger logger = LoggerFactory.getLogger(ProfessorService.class.getName());

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    @Transactional(readOnly = true)
    public List<ProfessorDTO> findAll() {
        logger.info("Buscando todos professores");
        return professorMapper.toDTOList(professorRepository.findAll());
    }


    @Transactional(readOnly = true)
    public ProfessorDTO findById(Long id) {
        logger.info("Buscando professor");
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com o id: " + id));
        return professorMapper.toDto(professor);
    }


    @Transactional
    public ProfessorDTO create(ProfessorDTO professorDTO) {
        logger.info("Criando novo professor");
        Professor professor = professorMapper.toEntity(professorDTO);
        Professor professorSalvo = professorRepository.save(professor);
        return professorMapper.toDto(professorSalvo);
    }


    @Transactional
    public ProfessorDTO update(Long id, ProfessorDTO professorDTO) {
        logger.info("Atualizando professor com id: {}", professorDTO.getId());
        Professor professorExistente = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com o id: " + id));

        Professor professorAtualizado = professorMapper.toEntity(professorDTO);
        professorAtualizado.setId(professorExistente.getId());

        Professor professorSalvo = professorRepository.save(professorAtualizado);
        return professorMapper.toDto(professorSalvo);

    }


    @Transactional
    public void delete(Long id) {
        logger.info("Deletando professor com id: {}", id);
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com o id: " + id));
        professorRepository.deleteById(id);
    }

}
