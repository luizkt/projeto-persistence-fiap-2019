package br.com.fiap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fiap.entity.Endereco;
import br.com.fiap.repository.EnderecoRepository;

@Controller
@RequestMapping(path = "/endereco")
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public @ResponseBody String add(@RequestParam String rua,
			@RequestParam String bairro, @RequestParam String numero, @RequestParam String cidade,
			@RequestParam String estado, @RequestParam String cep, @RequestParam String pais) {
		
		Endereco endereco = new Endereco();
		
		endereco.setRua(rua);
		endereco.setBairro(bairro);
		endereco.setNumero(numero);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		endereco.setCep(cep);
		endereco.setPais(pais);
		
		enderecoRepository.save(endereco);
		
		return "Salvo com sucesso";
	}

	@Transactional(readOnly = true)
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public @ResponseBody Iterable<Endereco> getAllUsers() {
		return enderecoRepository.findAll();
	}

//	@Transactional
//	public void addAll(Collection<Endereco> Enderecos) {
//		for (Endereco Endereco : Enderecos) {
//			enderecoRepository.save(Endereco);
//		}
//	}
//
//	@Transactional(readOnly = true)
//	public Optional<Endereco> findById(int id) {
//		return enderecoRepository.findById(id);
//	}
	
	
}
