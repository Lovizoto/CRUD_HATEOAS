package br.com.lovizoto.regesc.controller;


import br.com.lovizoto.regesc.data.model.Disciplina;
import br.com.lovizoto.regesc.data.model.Professor;
import br.com.lovizoto.regesc.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regesc")
public class ProfessorController {

//    @Autowired
//    private ProfessorService professorService;
//
//    @PostMapping("/professor")
//    public Professor criarProfessor(@RequestBody Professor professor) {
//        return professorService.create(professor);
//    }
//
//    @GetMapping("/professor/{id}")
//    public Professor getProfessor(@PathVariable Long id) {
//        return professorService.findById(id);
//    }
//
//    @GetMapping("/professor")
//    public List<Professor> listarProfessor() {
//        return professorService.findAll();
//    }
//
//    @DeleteMapping("/professor/{id}")
//    public ResponseEntity<?> deleteProfessor(@PathVariable Long id) {
//        professorService.delete(id);
//        return ResponseEntity.noContent().build(); //a ideia desta linha é que ao deletar resulte no código 204 - código correto para delete
//    }
//
//    @GetMapping("/professor/disciplina/{idProfessor}")
//    public List<Disciplina> listarDisciplinasPorProfessor(@PathVariable Long idProfessor) {
//        return professorService.findAllDisciplinasByProfessorId(idProfessor);
//    }
}
