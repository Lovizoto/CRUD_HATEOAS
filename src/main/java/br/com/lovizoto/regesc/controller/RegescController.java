package br.com.lovizoto.regesc.controller;

import br.com.lovizoto.regesc.model.Aluno;
import br.com.lovizoto.regesc.model.Disciplina;
import br.com.lovizoto.regesc.model.Professor;
import br.com.lovizoto.regesc.services.AlunoService;
import br.com.lovizoto.regesc.services.DisciplinaService;
import br.com.lovizoto.regesc.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


/*
    Esta classe faz as chamadas dos Serviços
    Nunca o controller chama direto do repository,
    pois é nos services que haverá qualquer tratamento de erro
 */


@RestController
@RequestMapping("/regesc")
public class RegescController {



}
