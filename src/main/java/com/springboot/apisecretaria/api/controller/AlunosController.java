package com.springboot.apisecretaria.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.springboot.apisecretaria.SecretariaApplication;
import com.springboot.apisecretaria.model.dto.ResponseAlunoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.apisecretaria.api.response.CustomResponse;
import com.springboot.apisecretaria.model.Aluno;
import com.springboot.apisecretaria.model.dto.AlunoDTO;
import com.springboot.apisecretaria.service.AlunoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Secretaria - Alunos")
@RestController
@RequestMapping("/alunos")
@Validated
public class AlunosController {

    @Autowired
    private AlunoService service;

    private static Logger logger = LoggerFactory.getLogger(AlunosController.class);

    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna a lista de alunos"),
    	    @ApiResponse(code = 204, message = "Não há alunos cadastrados"),
    	    @ApiResponse(code = 401, message = "A solicitação desejada requer autenticação"),
    	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
    	    @ApiResponse(code = 500, message = "Foi gerada uma exceção não tratada"),
    	})
    @ApiOperation(value = "Busca lista com dados de alunos matriculados")
    @GetMapping()
    public ResponseEntity<?> getAlunos() {
        logger.info("GET /alunos");
        List<AlunoDTO> alunosDTO = service.getAlunos();
        return ResponseEntity.ok(
            CustomResponse.wrapper("data", alunosDTO));
    }
    
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Retorna o aluno desejado"),
    	    @ApiResponse(code = 204, message = "Não há dado para o aluno desejado"),
    	    @ApiResponse(code = 401, message = "A solicitação desejada requer autenticação"),
    	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
    	    @ApiResponse(code = 500, message = "Foi gerada uma exceção não tratada"),
    	})
    @ApiOperation(value = "Busca aluno especifico matriculados")
    @GetMapping("/{id}")
    public ResponseEntity<?> getAlunosById(@PathVariable("id") Long id) {
        logger.info("GET /alunos/{id}");
        AlunoDTO alunoDTO = service.getAlunoById(id);
        return ResponseEntity.ok(
            CustomResponse.wrapper("data", alunoDTO));
    }

    @ApiResponses(value = {
    	    @ApiResponse(code = 201, message = "Dados do aluno foram salvos com sucesso"),
    	    @ApiResponse(code = 400, message = "Há erro de sintaxe, ou campos informados incorretamente"),
    	    @ApiResponse(code = 401, message = "A solicitação desejada requer autenticação"),
    	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
    	    @ApiResponse(code = 500, message = "Foi gerada uma exceção não tratada"),
    	})
    @ApiOperation(value = "Salva dados de um aluno")
    @PostMapping()
    public ResponseEntity<?> postAluno(@Valid @RequestBody Aluno aluno) {
        logger.info("POST /alunos");
        service.postAluno(aluno);
        return ResponseEntity.created(getUri(aluno.getId())).body(ResponseAlunoDTO.create(aluno));
    }

    @ApiResponses(value = {
    	    @ApiResponse(code = 204, message = "Atualização para o Aluno realizada com sucesso"),
    	    @ApiResponse(code = 400, message = "Há erro de sintaxe, ou campos informados incorretamente"),
    	    @ApiResponse(code = 401, message = "A solicitação desejada requer autenticação"),
    	    @ApiResponse(code = 404, message = "Não há aluno com o registro informado"),
    	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
    	    @ApiResponse(code = 500, message = "Foi gerada uma exceção não tratada"),
    	})
    @ApiOperation(value = "Atualiza os dados de um aluno")
    @PutMapping("/{id}")
    public ResponseEntity<?> putAluno(@Valid @RequestBody Aluno aluno, @PathVariable("id") Long id) {
        logger.info("PUT /alunos");
        service.putAluno(aluno, id);
        return ResponseEntity.noContent().build();
    }
    
    @ApiResponses(value = {
    	    @ApiResponse(code = 204, message = "Aluno informado removido com sucesso"),
    	    @ApiResponse(code = 401, message = "A solicitação desejada requer autenticação"),
    	    @ApiResponse(code = 404, message = "Não há aluno com o registro informado"),
    	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
    	    @ApiResponse(code = 500, message = "Foi gerada uma exceção não tratada"),
    	})
    @ApiOperation(value = "Remove os dados salvos de um aluno")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAluno(@PathVariable("id") Long id){
        logger.info("DELETE /alunos");
        service.deleteAluno(id);
        return ResponseEntity.noContent().build();
    }

    private URI getUri(Long id) {
         URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();

        return location;
    }

}