package com.peter.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peter.helpdesk.domain.Tecnico;
import com.peter.helpdesk.domain.dtos.TecnicoDTO;
import com.peter.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")	//Exemplo localhost:8080/tecnicos
public class TecnicoResource {
	
	@Autowired 
	private TecnicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
}