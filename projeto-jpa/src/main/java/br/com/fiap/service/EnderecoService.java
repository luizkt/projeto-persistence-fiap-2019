package br.com.fiap.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.entity.Endereco;
import br.com.fiap.repository.EnderecoRepository;

@Component
public class EnderecoService {

	@Autowired
	private EnderecoRepository EnderecoRepository;

	@Transactional
	public void add(Endereco Endereco) {
		EnderecoRepository.save(Endereco);
	}

	@Transactional(readOnly = true)
	public List<Endereco> findAll() {
		return EnderecoRepository.findAll();
	}

	@Transactional
	public void addAll(Collection<Endereco> Enderecos) {
		for (Endereco Endereco : Enderecos) {
			EnderecoRepository.save(Endereco);
		}
	}

//	@Transactional(readOnly = true)
//	public Optional<Endereco> findById(int id) {
//		return EnderecoRepository.findById(id);
//	}
	
	
}
