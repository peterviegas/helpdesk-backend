package com.peter.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peter.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
}
