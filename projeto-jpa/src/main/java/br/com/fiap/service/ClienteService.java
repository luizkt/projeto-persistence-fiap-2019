package br.com.fiap.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.entity.Cliente;
import br.com.fiap.repository.ClienteRepository;

@Component
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public void add(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
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
}
