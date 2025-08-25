package br.com.lovizoto.regesc.controller;


import br.com.lovizoto.regesc.data.dto.CollectionResponse;
import br.com.lovizoto.regesc.data.dto.ProfessorDTO;
import br.com.lovizoto.regesc.services.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/{id}")
    public ProfessorDTO findById(@PathVariable Long id){
        ProfessorDTO professorDTO = professorService.findById(id);

        professorDTO.add(linkTo(methodOn(ProfessorController.class).findById(id)).withSelfRel());
        professorDTO.add(linkTo(methodOn(ProfessorController.class).findAll()).withRel("professores"));

        return professorDTO;

    }

    @GetMapping
    public CollectionResponse<ProfessorDTO> findAll() {
        List<ProfessorDTO> professores = professorService.findAll();

        professores.forEach(professorDTO -> {
            professorDTO.add(linkTo(methodOn(ProfessorController.class).findById(professorDTO.getId())).withSelfRel());
        });

        CollectionResponse<ProfessorDTO> response = new CollectionResponse<>(professores);
        response.add(linkTo(methodOn(ProfessorController.class).findAll()).withSelfRel());

        return response;
    }

    @PostMapping
    public ProfessorDTO create(@RequestBody ProfessorDTO professorDTO) {
        ProfessorDTO createdProfessor = professorService.create(professorDTO);

        createdProfessor.add(linkTo(methodOn(ProfessorController.class).findById(createdProfessor.getId())).withSelfRel());
        return createdProfessor;
    }

    @PutMapping("/{id}")
    public ProfessorDTO update(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
        ProfessorDTO professorAtualizado = professorService.update(id,  professorDTO);
        professorAtualizado.add(linkTo(methodOn(ProfessorController.class).findById(professorAtualizado.getId())).withSelfRel());
        return professorAtualizado;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        professorService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
