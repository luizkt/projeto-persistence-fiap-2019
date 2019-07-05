package br.com.fiap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fiap.entity.Cliente;
import br.com.fiap.repository.ClienteRepository;

@Controller
@RequestMapping(path = "/cliente")
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	@GetMapping(path = "/add")
	public @ResponseBody String add(@RequestParam String nome, @RequestParam String rg) {
		
		Cliente cliente	= new Cliente();
		
		cliente.setNome(nome);
		cliente.setRg(rg);

		clienteRepository.save(cliente);
		
		return "Salvo com sucesso";
	}
	
	@Transactional(readOnly = true)
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Cliente> getAllUsers() {
		return clienteRepository.findAll();
	}

//	@Transactional
//	public void addAll(Collection<Cliente> clientes) {
//		for (Cliente cliente : clientes) {
//			clienteRepository.save(cliente);
//		}
//	}

	@Transactional(readOnly = true)
	@GetMapping(path = "/nome")
	public @ResponseBody List<Cliente> findByName(@RequestParam String nome) {
		return clienteRepository.findByName(nome);
	}

//	@Transactional(readOnly = true)
//	public Optional<Cliente> findById(int id) {
//		return clienteRepository.findById(id);
//	}
}
