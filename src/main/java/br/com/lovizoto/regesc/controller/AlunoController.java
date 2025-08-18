package br.com.lovizoto.regesc.controller;

import br.com.lovizoto.regesc.data.dto.AlunoDTO;
import br.com.lovizoto.regesc.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regesc") //posso utilizar ("/regesc/aluno") e retirar todos os "/aluno" das anotações
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    /*
        VERSÃO SEM DTO
     */

//    @GetMapping("/aluno")
//    public List<Aluno> listarAlunos() {
//        return alunoService.findAll();
//    }
//
//    @PostMapping("/aluno")
//    public Aluno criarAluno(@RequestBody Aluno aluno) {
//        return alunoService.criarAluno(aluno);
//    }
//
//    @PutMapping("/aluno")
//    public Aluno atualizarAluno(@RequestBody Aluno aluno) {
//        return alunoService.atualizarAluno(aluno);
//    }
//
//    @PutMapping("/aluno/disciplina/{idAluno}/{idDisciplina}")
//    public Aluno vincularAlunoDisciplina(@PathVariable Long idAluno, @PathVariable Long idDisciplina) {
//        return alunoService.vincularDisciplina(idAluno, idDisciplina);
//    }

    /*
        VERSÃO COM DTO
     */


    @GetMapping(value = "/aluno", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<AlunoDTO> listarAlunos() {
        return alunoService.findAll();
    }

    @PostMapping("/aluno")
    public AlunoDTO criarAluno(@RequestBody AlunoDTO aluno) {
        return alunoService.criarAluno(aluno);
    }

//    @PutMapping("/aluno")
//    public Aluno atualizarAluno(@RequestBody Aluno aluno) {
//        return alunoService.atualizarAluno(aluno);
//    }
//
//    @PutMapping("/aluno/disciplina/{idAluno}/{idDisciplina}")
//    public Aluno vincularAlunoDisciplina(@PathVariable Long idAluno, @PathVariable Long idDisciplina) {
//        return alunoService.vincularDisciplina(idAluno, idDisciplina);
//    }


}
