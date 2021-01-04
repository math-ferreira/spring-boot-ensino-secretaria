package com.springboot.apisecretaria.routes;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.apisecretaria.business.SalvarAlunoTests;
import com.springboot.apisecretaria.model.Aluno;

import com.springboot.apisecretaria.routes.config.RequestRouteConfig;
import org.h2.tools.Server;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RequestRouteAlunosTests extends RequestRouteConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String PATH = "/alunos";

    @BeforeAll
    public void setUp() throws Exception {
        requestToken();
        gerarAlunos(3L);
    }

    @BeforeEach
    public void gerarToken() throws Exception {
        requestToken();
    }

    public void gerarAlunos(Long quantidade) throws Exception {
        Long aux = 1L;
        while (aux <= quantidade){
            postAluno200();
            aux++;
        }
    }

    @Test
    void postAluno200() throws Exception {
        // Cria objeto Request Body para API geracao de Token
        Aluno aluno = new SalvarAlunoTests().createAluno();

        ResultActions RequestPost = requestPOST(PATH, aluno);

        // Verifica HTTP status
        RequestPost.andExpect(
                MockMvcResultMatchers
                        .status()
                        .isCreated());
    }

    @Test
    void postAluno400() throws Exception {
        // Cria objeto Request Body para API geracao de Token
        Aluno aluno = new SalvarAlunoTests().createAluno();
        aluno.setIdade(null);

        ResultActions RequestPost = requestPOST(PATH, aluno);

        // Verifica HTTP status
        RequestPost.andExpect(
                MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }

    @Test
    void getAlunos200() throws Exception {

        ResultActions RequestGet = requestGET(PATH);

        // Verifica HTTP status
        RequestGet.andExpect(
                MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    @Test
    void getAlunoId200() throws Exception {

        ResultActions RequestGet = requestGET(PATH + "/1");

        // Verifica HTTP status
        RequestGet.andExpect(
                MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    @Test
    void getAlunoId204() throws Exception {

        ResultActions RequestGet = requestGET(PATH + "/100");

        // Verifica HTTP status
        RequestGet.andExpect(
                MockMvcResultMatchers
                        .status()
                        .isNoContent());
    }

    @Test
    void deleteAluno204() throws Exception {

        ResultActions RequestDELETE = requestDELETE(PATH + "/2");

        // Verifica HTTP status
        RequestDELETE.andExpect(
                MockMvcResultMatchers
                        .status()
                        .isNoContent());
    }

    @Test
    void deleteAluno404() throws Exception {

        ResultActions RequestDELETE = requestDELETE(PATH + "/100");

        // Verifica HTTP status
        RequestDELETE.andExpect(
                MockMvcResultMatchers
                        .status()
                        .isNotFound());
    }

    @Test
    void putAluno204() throws Exception {
        // Cria objeto Request Body para API geracao de Token
        Aluno aluno = new SalvarAlunoTests().createAluno();
        aluno.setNome("Rosy Mary");
        aluno.getContato().setEmail("rosy_mary2021@hotmail.com.br");
        ResultActions RequestDELETE = requestPUT(PATH + "/1", aluno);

        // Verifica HTTP status
        RequestDELETE.andExpect(
                MockMvcResultMatchers
                        .status()
                        .isNoContent());
    }
}
