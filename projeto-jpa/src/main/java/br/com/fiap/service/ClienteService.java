package br.com.fiap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.entity.Cliente;
import br.com.fiap.repository.ClienteRepository;

@RestController
@RequestMapping(path = "/cliente")
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(@RequestParam String nome, @RequestParam String rg) {
		
		Cliente cliente	= new Cliente();
		
		cliente.setNome(nome);
		cliente.setRg(rg);

		clienteRepository.save(cliente);
		
		return "Salvo com sucesso";
	}
	
	@Transactional(readOnly = true)
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Cliente> getAllUsers() {
		return clienteRepository.findAll();
	}

//	@Transactional
//	public void addAll(Collection<Cliente> clientes) {
//		for (Cliente cliente : clientes) {
//			clienteRepository.save(cliente);
//		}
//	}

	@Transactional(readOnly = true)
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	@ResponseBody
	public List<Cliente> findByName(@PathVariable String nome) {
		return clienteRepository.findByName(nome);
	}

//	@Transactional(readOnly = true)
//	public Optional<Cliente> findById(int id) {
//		return clienteRepository.findById(id);
//	}
}
