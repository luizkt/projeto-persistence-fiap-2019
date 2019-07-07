package br.com.fiap.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.entity.node.Endereco;
import br.com.fiap.repository.EnderecoRepositoryImpl;

@Component
public class EnderecoService {

	@Autowired
	private EnderecoRepositoryImpl EnderecoRepository;

	@Transactional
	public void add(Endereco Endereco) {
		EnderecoRepository.save(Endereco);
	}

	@Transactional(readOnly = true)
	public Iterable<Endereco> findAll() {
		return EnderecoRepository.findAll();
	}

	@Transactional
	public void addAll(Collection<Endereco> Enderecos) {
		for (Endereco Endereco : Enderecos) {
			EnderecoRepository.save(Endereco);
		}
	}

	@Transactional(readOnly = true)
	public Optional<Endereco> findById(Long id) {
		return EnderecoRepository.findById(id);
	}
	
	
}
