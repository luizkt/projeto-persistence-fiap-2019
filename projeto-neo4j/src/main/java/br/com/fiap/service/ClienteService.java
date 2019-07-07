package br.com.fiap.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.entity.node.Cliente;
import br.com.fiap.repository.ClienteRepositoryImpl;

@Component
public class ClienteService {

	@Autowired
	private ClienteRepositoryImpl clienteRepository;

	@Transactional
	public void add(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Transactional(readOnly = true)
	public Iterable<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Transactional
	public void addAll(Collection<Cliente> clientes) {
		for (Cliente cliente : clientes) {
			clienteRepository.save(cliente);
		}
	}

	@Transactional(readOnly = true)
	public List<Cliente> findByName(String nome) {
		return clienteRepository.findByName(nome);
	}
	
	@Transactional(readOnly = true)
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}	
}
