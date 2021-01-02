package com.springboot.apisecretaria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.springboot.apisecretaria.api.exception.SecretariaNotFoundException;
import com.springboot.apisecretaria.model.Aluno;
import com.springboot.apisecretaria.model.dto.AlunoDTO;
import com.springboot.apisecretaria.model.dto.EmailDTO;
import com.springboot.apisecretaria.model.dto.JMSDTO;
import com.springboot.apisecretaria.repository.AlunoReposity;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlunoService {

	private static Logger logger = LoggerFactory.getLogger(AlunoService.class);

	@Autowired
	private AlunoReposity repository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${spring.activemq.queue.name}")
	private String queueName;

	public List<AlunoDTO> getAlunos() {
		List<Aluno> alunos = repository.findAll();
		if (alunos.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT);
		}
		List<AlunoDTO> alunosDTO = new ArrayList<>();
		alunos.forEach(e -> alunosDTO.add(AlunoDTO.create(e)));
		return alunosDTO;
	}

	public AlunoDTO getAlunoById(Long id) {
		Optional<Aluno> aluno = repository.findById(id);
		if (aluno.isPresent()) {
			AlunoDTO alunoDTO = AlunoDTO.create(aluno.get());
			return alunoDTO;
		}
		throw new ResponseStatusException(HttpStatus.NO_CONTENT);
	}

	public AlunoDTO postAluno(Aluno aluno) {
		EmailDTO checkEmail = emailService.checkEmail(aluno.getContato().getEmail());

		if (!checkEmail.getStatusEmail().equals("validated") || !checkEmail.isFormatoValido()) {
			throw new IllegalArgumentException("Email invalido");
		}

		repository.save(aluno);
		try {
			jmsTemplate.convertAndSend(queueName, new JMSDTO(aluno, "POST"));
		} catch (Exception e) {
			logger.error("Nao foi possivel enviar mensagem na fila. POST: " + aluno.toString());
		}

		return AlunoDTO.create(aluno);
	}

	public AlunoDTO putAluno(Aluno aluno, Long id) {
		Optional<Aluno> alunoGravado = repository.findById(id);
		if (alunoGravado.isPresent()) {
			merge(aluno, alunoGravado.get());
			repository.save(alunoGravado.get());
			try {
				jmsTemplate.convertAndSend(queueName, new JMSDTO(aluno, "PUT"));
			} catch (Exception e) {
				logger.error("Nao foi possivel enviar mensagem na fila. PUT: " + aluno.toString() + " com id=" + id);
			}
			return AlunoDTO.create(alunoGravado.get());
		}
		throw new SecretariaNotFoundException("Não é possivel atualizar o Aluno. Id " + id + " não encontrado");
	}

	public void deleteAluno(Long id) {
		if (repository.findById(id).isEmpty()) {
			throw new SecretariaNotFoundException("Não é possivel remover o Aluno. Id " + id + " não encontrado");
		}
		repository.deleteById(id);
		try {
			jmsTemplate.convertAndSend(queueName, new JMSDTO("id=" + id, "DELETE"));
		} catch (Exception e) {
			logger.error("Nao foi possivel enviar mensagem na fila. DELETE aluno de id=" + id);
		}
		
	}

	private Aluno merge(Aluno aluno, Aluno alunoGravado) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.getConfiguration().setDeepCopyEnabled(true);
		modelMapper.map(aluno, alunoGravado);
		return alunoGravado;
	}
}