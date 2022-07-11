package com.peter.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peter.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
}
