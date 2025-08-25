package br.com.lovizoto.regesc.controller;


import br.com.lovizoto.regesc.data.dto.CollectionResponse;
import br.com.lovizoto.regesc.data.dto.DisciplinaDTO;
import br.com.lovizoto.regesc.services.DisciplinaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping("/{id}")
    public DisciplinaDTO findById(@PathVariable Long id){
        DisciplinaDTO disciplinaDTO = disciplinaService.findById(id);

        disciplinaDTO.add(linkTo(methodOn(DisciplinaController.class).findById(id)).withSelfRel());
        disciplinaDTO.add(linkTo(methodOn(DisciplinaController.class).findAll()).withRel("disciplinas"));

        return disciplinaDTO;

    }


    @GetMapping
    public CollectionResponse<DisciplinaDTO> findAll() {
        List<DisciplinaDTO> disciplinas = disciplinaService.findAll();

        disciplinas.forEach(disciplinaDTO ->
                disciplinaDTO.add(linkTo(methodOn(DisciplinaController.class).findById(disciplinaDTO.getId())).withSelfRel()));

        CollectionResponse<DisciplinaDTO> response = new CollectionResponse<>(disciplinas);
        response.add(linkTo(methodOn(DisciplinaController.class).findAll()).withSelfRel());

        return response;

    }

    @PostMapping
    public DisciplinaDTO create(@RequestBody DisciplinaDTO disciplinaDTO){
        DisciplinaDTO disciplinaCriada = disciplinaService.create(disciplinaDTO);
        disciplinaCriada.add(linkTo(methodOn(DisciplinaController.class).findById(disciplinaCriada.getId())).withSelfRel());
        return disciplinaCriada;
    }

    @PutMapping("/{id}")
    public DisciplinaDTO update(@PathVariable Long id, @RequestBody DisciplinaDTO disciplinaDTO){
        DisciplinaDTO disciplinaAtualizada = disciplinaService.update(id, disciplinaDTO);
        disciplinaAtualizada.add(linkTo(methodOn(DisciplinaController.class).findById(disciplinaAtualizada.getId())).withSelfRel());
        return disciplinaAtualizada;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        disciplinaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idDisciplina}/vincular-aluno/{idAluno}")
    public DisciplinaDTO vincularAluno(@PathVariable Long idDisciplina, @PathVariable Long idAluno){
        DisciplinaDTO disciplinaAtualizada = disciplinaService.vincularAluno(idDisciplina, idAluno);

        disciplinaAtualizada.add(linkTo(methodOn(DisciplinaController.class).findById(idDisciplina)).withSelfRel());
        disciplinaAtualizada.add(linkTo(methodOn(AlunoController.class).findById(idAluno)).withSelfRel());

        return disciplinaAtualizada;
    }

}
