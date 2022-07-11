package com.peter.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.peter.helpdesk.domain.Chamado;
import com.peter.helpdesk.domain.Cliente;
import com.peter.helpdesk.domain.Tecnico;
import com.peter.helpdesk.domain.enums.Perfil;
import com.peter.helpdesk.domain.enums.Prioridade;
import com.peter.helpdesk.domain.enums.Status;
import com.peter.helpdesk.repositories.ChamadoRepository;
import com.peter.helpdesk.repositories.ClienteRepository;
import com.peter.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner{

	@Autowired
	private TecnicoRepository tecnicoRepository;	
	@Autowired
	private ClienteRepository clienteRepository;	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "917.352.500-66", "valdir@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "594.387.150-02", "torvalds@mail.com", "123");
		//cli1.addPerfil(Perfil.CLIENTE);
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}

}
