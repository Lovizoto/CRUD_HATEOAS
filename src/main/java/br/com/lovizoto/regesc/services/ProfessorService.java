package br.com.lovizoto.regesc.services;

import br.com.lovizoto.regesc.exception.handler.ResourceNotFoundException;
import br.com.lovizoto.regesc.model.Disciplina;
import br.com.lovizoto.regesc.model.Professor;
import br.com.lovizoto.regesc.repository.DisciplinaRepository;
import br.com.lovizoto.regesc.repository.ProfessorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/*
    Toda parte da lógica deve estar na classe de serviço (Service)
 */


@Service
public class ProfessorService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(ProfessorService.class.getName());

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    public List<Professor> findAll() {
        logger.info("Buscando todos professores");
        return professorRepository.findAll();
    }

    public Professor findById(Long id) {
        logger.info("Buscando professor");
//        return professorRepository.findById(id).get();  << aqui eu só retorno sem o tratamento de erro caso solicitar um id que não possui
        return professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found with id " + id));
    }

    public Professor create(Professor professor) {
        logger.info("Criando professor");
        return professorRepository.save(professor);
    }


    public void delete(Long id) {
        logger.info("Deletando professor");
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found with id " + id));


//        professorRepository.delete(professor); // acessa os eventos do ciclo de vida da entidade
        professorRepository.deleteById(id);
    }

    public List<Disciplina> findAllDisciplinasByProfessorId(Long idProfessor) {
        logger.info("Buscando disciplinas");

        if (!professorRepository.existsById(idProfessor)) {
            throw new ResourceNotFoundException("No record found with id " + idProfessor);
        }

        return disciplinaRepository.findByProfessorId(idProfessor);

    }

}
