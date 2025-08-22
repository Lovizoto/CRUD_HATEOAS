package br.com.lovizoto.regesc.controller;

import br.com.lovizoto.regesc.data.dto.AlunoDTO;



import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.lovizoto.regesc.services.AlunoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alunos") //posso utilizar ("/regesc/aluno") e retirar todos os "/aluno" das anotações
public class AlunoController {


    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping("/{id}")
    public AlunoDTO findById(@PathVariable Long id) {
        AlunoDTO alunoDTO = alunoService.findById(id);

        alunoDTO.add(linkTo(methodOn(AlunoController.class).findById(id)).withSelfRel());
        alunoDTO.add(linkTo(methodOn(AlunoController.class).findAll()).withRel("todosAlunos"));

        return alunoDTO;

    }

    @GetMapping
    public CollectionModel<AlunoDTO> findAll() {
        List<AlunoDTO> alunos = alunoService.findAll();

        alunos.forEach(alunoDTO ->
                alunoDTO.add(linkTo(methodOn(AlunoController.class).findById(alunoDTO.getId())).withSelfRel()));

        var link = linkTo(methodOn(AlunoController.class).findAll()).withSelfRel();

        return CollectionModel.of(alunos, link);

    }




}
