package com.peter.helpdesk.services;

import java.util.Arrays;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.peter.helpdesk.domain.Chamado;
import com.peter.helpdesk.domain.Cliente;
import com.peter.helpdesk.domain.Tecnico;
import com.peter.helpdesk.domain.enums.Perfil;
import com.peter.helpdesk.domain.enums.Prioridade;
import com.peter.helpdesk.domain.enums.Status;
import com.peter.helpdesk.repositories.ChamadoRepository;
import com.peter.helpdesk.repositories.ClienteRepository;
import com.peter.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;	
	@Autowired
	private ClienteRepository clienteRepository;	
	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instanciaDB() {
		Tecnico tec1 = new Tecnico(null, "Peter Viegas", "890.052.110-16", "peterviegas@outlook.com", encoder.encode("123"));
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", encoder.encode("123"));
		Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", encoder.encode("123"));
		Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", encoder.encode("123"));
		Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode("123"));
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "594.387.150-02", "torvalds@mail.com", "123");
		Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("123"));
		Cliente cli3 = new Cliente(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", encoder.encode("123"));
		Cliente cli4 = new Cliente(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", encoder.encode("123"));
		Cliente cli5 = new Cliente(null, "Max Planck", "081.399.300-83", "planck@mail.com", encoder.encode("123"));
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);
		Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
		Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);
		Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", tec2, cli1);
		Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 7", "Teste chamado 6", tec1, cli5);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
		chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
		
		//Update technician
		Optional<Tecnico> optionalTecObj = tecnicoRepository.findById(tec2.getId());

		if (optionalTecObj.isPresent()) {
		    Tecnico tecObj = optionalTecObj.get();
		    tecObj.setCpf("764.430.020-16");
		    tecObj.setEmail("tadeu.abuquerque@gmail.com");
		    tecObj.setSenha("secret");
		    tecObj.setNome("Tadeu Abuquerque");
		    tecnicoRepository.save(tecObj);
		    
		} else {
		    throw new EntityNotFoundException("Technician not found with ID: " + tec2.getId());
		}
		
		//Update customer
		Optional<Cliente> optionalObjCli = clienteRepository.findById(cli1.getId());
		
		if(optionalObjCli.isPresent()) {
			Cliente cliObj = optionalObjCli.get();
			cliObj.setCpf("349.744.220-81");
			cliObj.setEmail(null);
			cliObj.setSenha(null);
			cliObj.setNome("Thomas Grany");
			clienteRepository.save(cliObj);
		} else {
			throw new EntityNotFoundException("Customer not found with ID: " + cli1.getId());
		}
		
		//Update ticket
		Optional<Chamado> optionalObjTicket = chamadoRepository.findById(c1.getId());
		
		if(optionalObjTicket.isPresent()) {
			Chamado chamadoObj = optionalObjTicket.get();
			chamadoObj.setCliente(cli5);
			chamadoObj.setObservacoes("Update Test");
			chamadoObj.setPrioridade(Prioridade.ALTA);
			chamadoObj.setStatus(Status.ANDAMENTO);
			chamadoObj.setTecnico(tec2);
			chamadoObj.setTitulo("Test Update work");
			
			chamadoRepository.save(chamadoObj);
		} else {
			throw new EntityNotFoundException("Customer not found with ID: " + c1.getId());
		}
		
		//Delete
		tecnicoRepository.delete(tec5);
		clienteRepository.delete(cli4);
	}
}
