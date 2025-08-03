package br.com.lovizoto.regesc.mapper.config;


import br.com.lovizoto.regesc.model.Disciplina;
import br.com.lovizoto.regesc.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StringToDisciplinaConverter {

    @Autowired
    DisciplinaService disciplinaService;

    public Set<Disciplina> map(Set<String> nomesDisciplinas) {

        if (nomesDisciplinas == null) {
            return null;
        }

        Set<Disciplina> disciplinas = new HashSet<>();
        for (String nome : nomesDisciplinas) {
            Disciplina disciplina = disciplinaService.findByNome(nome).get();
            if (disciplina != null) {
                disciplinas.add(disciplina);
            }
            return disciplinas;

        }
        return null;
    }

    public Set<String> mapDisciplinas(Set<Disciplina> disciplinas) {
        if (disciplinas == null) return null;
        return disciplinas.stream()
                .map(Disciplina::getNome)
                .collect(Collectors.toSet());
    }



}
