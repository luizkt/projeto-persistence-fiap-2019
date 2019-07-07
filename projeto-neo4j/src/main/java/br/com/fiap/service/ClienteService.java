package br.com.fiap.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.entity.node.Cliente;
import br.com.fiap.entity.node.Endereco;
import br.com.fiap.model.ClienteJson;
import br.com.fiap.repository.ClienteRepository;
import br.com.fiap.repository.EnderecoRepository;

@RestController
@RequestMapping(path = "/cliente")
@Component
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	

	@Transactional
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> add(@Valid @RequestBody Map<String, Object> payload) {

		try {

			ObjectMapper mapper = new ObjectMapper();
			ClienteJson clienteJson = mapper.convertValue(payload, ClienteJson.class);
			Cliente cliente = new Cliente();
			Endereco endereco = new Endereco();
			
			cliente.setNome(clienteJson.getNome());
			cliente.setRg(clienteJson.getRg());
			endereco.setRua(clienteJson.getEndereco().getRua());
			endereco.setBairro(clienteJson.getEndereco().getBairro());
			endereco.setNumero(clienteJson.getEndereco().getNumero());
			endereco.setCidade(clienteJson.getEndereco().getCidade());
			endereco.setEstado(clienteJson.getEndereco().getEstado());
			endereco.setCep(clienteJson.getEndereco().getCep());
			endereco.setPais(clienteJson.getEndereco().getPais());
			cliente.setEndereco(endereco);

			clienteRepository.save(cliente);

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
			String body = "{\"Mensagem\":\"Cliente adicionado com sucesso\"}";

			return new ResponseEntity<>(body, headers, HttpStatus.CREATED);

		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
			String body = "{\"Mensagem\":\"Ocorreu um erro\", \"Execeção\":" + e.getMessage() + "}";

			return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);
		}

	}

	// @Transactional
	// public void add(Cliente cliente) {
	// clienteRepository.save(cliente);
	// }

	@Transactional(readOnly = true)
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	// @Transactional
	// public void addAll(Collection<Cliente> clientes) {
	// for (Cliente cliente : clientes) {
	// clienteRepository.save(cliente);
	// }
	// }

	@Transactional(readOnly = true)
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	@ResponseBody
	public List<Cliente> findByNome(String nome) {
		return clienteRepository.findByNome(nome);
	}

	@Transactional(readOnly = true)
	@RequestMapping(value = "/rg/{rg}", method = RequestMethod.GET)
	@ResponseBody
	public List<Cliente> findByRg(String rg) {
		return clienteRepository.findByRg(rg);
	}
}
