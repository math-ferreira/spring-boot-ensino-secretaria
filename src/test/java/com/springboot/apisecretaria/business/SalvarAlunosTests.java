package com.springboot.apisecretaria.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintViolationException;

import com.springboot.apisecretaria.model.Aluno;
import com.springboot.apisecretaria.model.Contato;
import com.springboot.apisecretaria.model.Endereco;
import com.springboot.apisecretaria.model.enums.PeriodoLetivo;
import com.springboot.apisecretaria.service.AlunoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.UncategorizedJmsException;

@SpringBootTest
public class SalvarAlunosTests {

    @Autowired
    private AlunoService alunoService;

    private static final String NOME_ALUNO = "Ciclano da Silva";
    private static final Integer IDADE_ALUNO = 26;
    private static final String NUMERO_MATRICULA_ALUNO = "110872664";
    private static final PeriodoLetivo PERIODO_LETIVO_ALUNO = PeriodoLetivo.SETIMA_SERIE;
    private static final String CONTATO_TELEFONE_ALUNO = "11945667909";
    private static final String CONTATO_EMAIL_ALUNO = "ciclano_silva@gmail.com";
    private static final String CONTATO_TIPO_TELEFONE_ALUNO = "celular";
    private static final String ENDERECO_RUA_ALUNO = "Rua das Orquideas Oliveiras ";
    private static final Integer ENDERECO_NUMERO_ALUNO = 1017;
    private static final String ENDERECO_BAIRRO_ALUNO = "Vila Cambui";
    private static final String ENDERECO_CIDADE_ALUNO = "São Paulo";
    private static final String ENDERECO_ESTADO_ALUNO = "SP";
    private static final String ENDERECO_CEP_ALUNO = "09231056";

    private Aluno createAluno(){
        Aluno aluno = new Aluno();
        aluno.setNome(NOME_ALUNO);
        aluno.setIdade(IDADE_ALUNO);
        aluno.setNumeroMatricula(NUMERO_MATRICULA_ALUNO);
        aluno.setPeriodo(PERIODO_LETIVO_ALUNO);

        Endereco endereco = new Endereco();
        endereco.setRua(ENDERECO_RUA_ALUNO);
        endereco.setNumero(ENDERECO_NUMERO_ALUNO);
        endereco.setBairro(ENDERECO_BAIRRO_ALUNO);
        endereco.setCidade(ENDERECO_CIDADE_ALUNO);
        endereco.setEstado(ENDERECO_ESTADO_ALUNO);
        endereco.setCep(ENDERECO_CEP_ALUNO);
        aluno.setEndereco(endereco);

        Contato contato = new Contato();
        contato.setEmail(CONTATO_EMAIL_ALUNO);
        contato.setTelefone(CONTATO_TELEFONE_ALUNO);
        contato.setTipoTelefone(CONTATO_TIPO_TELEFONE_ALUNO);
        aluno.setContato(contato);

        return aluno;
    }

    @Test
    public void salvaAlunoSucesso(){
        Aluno aluno = createAluno();
        try {
            alunoService.postAluno(aluno);
        } catch (UncategorizedJmsException e) {
            System.out.println("Nao foi possivel gravar mensagem na fila");
        }
        assertEquals(1, alunoService.getAlunos().size());
    }
    

    @Test
    public void salvaAlunoErroEndereco(){
        Aluno aluno = createAluno();
        aluno.getEndereco().setCidade(null);
        try {
            alunoService.postAluno(aluno);
        } catch (UncategorizedJmsException ex) {
            System.out.println("Nao foi possivel gravar mensagem na fila");
        } catch (ConstraintViolationException ex){
            ex.getConstraintViolations().forEach(e -> {
                assertTrue(e.getMessageTemplate().contains("cidade"));
            });
        }
    }

    @Test
    public void salvaAlunoErroContato(){
        Aluno aluno = createAluno();
        aluno.getContato().setEmail(null);
        try {
            alunoService.postAluno(aluno);
        } catch (UncategorizedJmsException ex) {
            System.out.println("Nao foi possivel gravar mensagem na fila");
        } catch (ConstraintViolationException ex){
            ex.getConstraintViolations().forEach(e -> {
                assertTrue(e.getMessageTemplate().contains("email"));
            });
        }
    }
}
