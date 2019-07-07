package br.com.fiap.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.entity.node.Endereco;
import br.com.fiap.repository.EnderecoRepository;


@RestController
@RequestMapping(path = "/endereco")
@Component
public class EnderecoService {

	@Autowired
	private EnderecoRepository EnderecoRepository;

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
