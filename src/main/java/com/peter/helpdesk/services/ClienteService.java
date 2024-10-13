package com.peter.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.peter.helpdesk.domain.Pessoa;
import com.peter.helpdesk.domain.Cliente;
import com.peter.helpdesk.domain.dtos.ClienteDTO;
import com.peter.helpdesk.repositories.PessoaRepository;
import com.peter.helpdesk.repositories.ClienteRepository;
import com.peter.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.peter.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " +id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		//Para evitar bagunça no banco de dados
		objDTO.setId(null); 
		
		//Criptografar senha
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}

	@Transactional
    public Cliente update(Integer id, ClienteDTO objDTO) {
        try {
            Cliente cliente = findById(id);
            updateData(cliente, objDTO);

            return repository.save(cliente);  // Aqui ocorre a validação e o commit da transação
        } catch (ConstraintViolationException ex) {
            // Lança uma exceção mais clara ou retorna um erro de validação para o controlador
            throw new ValidationException("Erro de validação: " + ex.getConstraintViolations().iterator().next().getMessage());
        }
    }

    private void updateData(Cliente cliente, ClienteDTO objDTO) {
        // Atualiza os dados do cliente
        cliente.setNome(objDTO.getNome());
        cliente.setCpf(objDTO.getCpf());  // A validação do CPF ocorre aqui
    }

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possue Ordem de Serviço e não pode ser deletado!");
		}
		
		repository.deleteById(id);
	}

	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent()&& obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
			
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent()&& obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}	
	}
}
