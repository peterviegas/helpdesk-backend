package com.peter.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peter.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{
	
}
