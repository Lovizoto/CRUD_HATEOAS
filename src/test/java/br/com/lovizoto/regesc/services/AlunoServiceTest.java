package br.com.lovizoto.regesc.services;

import br.com.lovizoto.regesc.data.dto.AlunoDTO;
import br.com.lovizoto.regesc.data.model.Aluno;
import br.com.lovizoto.regesc.mapper.AlunoMapper;
import br.com.lovizoto.regesc.mocks.MockAlunoFactory;
import br.com.lovizoto.regesc.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


//@TestInstance(TestInstance.Lifecycle.PER_CLASS) - por method, default do JUnit 5, é mais seguro e promove isolamento entre os testes
@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para Aluno Service")
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private AlunoMapper alunoMapper;

    @InjectMocks
    private AlunoService alunoService;

    private Aluno aluno;
    private AlunoDTO alunoDTO;

    @BeforeEach
    void setUp() {

        aluno = MockAlunoFactory.mockAluno();
        alunoDTO = MockAlunoFactory.mockAlunoDTO();
//        MockitoAnnotations.openMocks(this); - depreciada, no JUnit 5

    }


    @Test
    @DisplayName("Deve encontrar um aluno por Id e retornar um DTO")
    void findById() {

        //Arrange
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(alunoMapper.toDTO(aluno)).thenReturn(alunoDTO);

        //Act
        AlunoDTO result = alunoService.findById(1L);

        //Assertions
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Gaal Dornick", result.getNomecompleto());
        assertNotNull(result.getDisciplinas());
        assertTrue(result.getDisciplinas().contains("Psico-História"));

        //Dependences Verify
        verify(alunoRepository).findById(1L);
        verify(alunoMapper).toDTO(aluno);
    }

    @Test
    @DisplayName("Deve criar um novo aluno")
    void create() {

        //Arrange
        when(alunoMapper.toEntity(any(AlunoDTO.class))).thenReturn(aluno);
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
        when(alunoMapper.toDTO(any(Aluno.class))).thenReturn(alunoDTO);

        //Act
        AlunoDTO result = alunoService.create(alunoDTO);

       //Assert
       assertNotNull(result);
       assertEquals(alunoDTO.getId(), result.getId());
       verify(alunoRepository).save(aluno);

    }

    @Test
    @DisplayName("Deve atualizar um aluno existente")
    void update() {



    }

    @Test
    void delete() {
    }

    @Test
    @DisplayName("Retorna lista com todos os alunos")
    void findAll() {

        //Arrange
        List<Aluno> alunos = MockAlunoFactory.mockAlunos();
        List<AlunoDTO> alunosDTO = MockAlunoFactory.mockAlunosDTO();
        when(alunoRepository.findAll()).thenReturn(alunos);
        when(alunoMapper.toDTOList(alunos)).thenReturn(alunosDTO);

        //Act
        List<AlunoDTO> result = alunoService.findAll();

        //Assert
        assertNotNull(result);
        assertEquals(alunosDTO.size(), result.size());
        assertEquals(alunosDTO.get(0).getNomecompleto(), result.get(0).getNomecompleto());
        verify(alunoRepository).findAll();

    }

}