package com.peter.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peter.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{
	
}
