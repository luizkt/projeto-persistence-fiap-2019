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

import br.com.fiap.entity.node.Produto;
import br.com.fiap.model.ProdutoJson;
import br.com.fiap.repository.ProdutoRepository;

@RestController
@RequestMapping(path = "/produto")
@Component
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> add(@Valid @RequestBody Map<String, Object> payload) {
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			ProdutoJson produtoJson = mapper.convertValue(payload, ProdutoJson.class);
			Produto produto = new Produto();
			
			produto.setCodigo(produtoJson.getCodigo());
			produto.setDesc(produtoJson.getDescricao());
			produto.setPreco(produtoJson.getPreco());
			produto.setQuantidadeEstoque(produtoJson.getQuantidade());
			
			produtoRepository.save(produto);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
			String body = "{\"Mensagem\":\"Produto adicionado com sucesso\"}";
			
			return new ResponseEntity<>(body, headers, HttpStatus.CREATED);
			
		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
			String body = "{\"Mensagem\":\"Ocorreu um erro\", \"Execeção\":" + e.getMessage() + "}";
			
			return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);
		}
		
	
	}

	@Transactional(readOnly = true)
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Produto> findAll() {
		return produtoRepository.findAll();
	}

	// @Transactional
	// public void addAll(Collection<Produto> produtos) {
	// for (Produto produto : produtos) {
	// produtoRepository.save(produto);
	// }
	// }

	@Transactional(readOnly = true)
	@RequestMapping(path = "/desc/{descricao}", method = RequestMethod.GET)
	@ResponseBody
	public List<Produto> findByDesc(String desc) {
		return produtoRepository.findByDesc(desc);
	}	
	
	@Transactional(readOnly = true)
	@RequestMapping(path = "/codigo/{codigo}", method = RequestMethod.GET)
	@ResponseBody
	public List<Produto> findByCodigo(String codigo) {
		return produtoRepository.findByCodigo(codigo);
	}	
}
