package br.com.lovizoto.regesc.controller;


import br.com.lovizoto.regesc.model.Disciplina;
import br.com.lovizoto.regesc.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regesc")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;


    @GetMapping("/disciplina")
    public List<Disciplina> listarDisciplinas() {
        return disciplinaService.findAll();
    }

    @GetMapping("/disciplina/{idDisciplina}")
    public Disciplina getDisciplina(@PathVariable Long idDisciplina) {
        return disciplinaService.findById(idDisciplina);
    }


    @PostMapping("/disciplina")
    public Disciplina criarDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaService.create(disciplina);
    }

    @PutMapping("/disciplina/aluno/{idAluno}/{idDisciplina}")
    public Disciplina vincularDisciplinaAluno(@PathVariable Long idAluno, @PathVariable Long idDisciplina){
        return disciplinaService.vincularDisciplinaAluno(idAluno, idDisciplina);
    }


}
