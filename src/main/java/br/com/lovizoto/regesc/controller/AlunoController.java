package br.com.lovizoto.regesc.controller;

import br.com.lovizoto.regesc.data.dto.AlunoDTO;



import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.lovizoto.regesc.data.dto.CollectionResponse;
import br.com.lovizoto.regesc.services.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alunos")
public class AlunoController {


    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping("/{id}")
    public AlunoDTO findById(@PathVariable Long id) {
        AlunoDTO alunoDTO = alunoService.findById(id);

        alunoDTO.add(linkTo(methodOn(AlunoController.class).findById(id)).withSelfRel());
        alunoDTO.add(linkTo(methodOn(AlunoController.class).findAll()).withRel("alunos"));

        return alunoDTO;

    }

    @GetMapping
    public CollectionResponse<AlunoDTO> findAll() {
        List<AlunoDTO> alunos = alunoService.findAll();

        alunos.forEach(alunoDTO ->
                alunoDTO.add(linkTo(methodOn(AlunoController.class).findById(alunoDTO.getId())).withSelfRel()));

        CollectionResponse<AlunoDTO> response = new CollectionResponse<>(alunos);
        response.add(linkTo(methodOn(AlunoController.class).findAll()).withSelfRel());

        return response;
    }

    @PostMapping
    public AlunoDTO create(@RequestBody AlunoDTO alunoDTO) {
        AlunoDTO createdAluno = alunoService.create(alunoDTO);

        createdAluno.add(linkTo(methodOn(AlunoController.class).findById(createdAluno.getId())).withSelfRel());
        return createdAluno;
    }

    @PutMapping("/{id}")
    public AlunoDTO update(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) {
        AlunoDTO alunoAtualizado = alunoService.update(id, alunoDTO);
        alunoAtualizado.add(linkTo(methodOn(AlunoController.class).findById(alunoAtualizado.getId())).withSelfRel());
        return alunoAtualizado;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
