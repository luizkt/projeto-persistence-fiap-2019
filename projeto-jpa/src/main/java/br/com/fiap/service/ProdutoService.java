package br.com.fiap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fiap.entity.Produto;
import br.com.fiap.repository.ProdutoRepository;

@Controller
@RequestMapping(path = "/produto")
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	@GetMapping(path = "/add")
	public @ResponseBody String add(@RequestParam String codigo, @RequestParam String descricao, @RequestParam int quantidade,
			@RequestParam double preco) {

		Produto produto = new Produto();
		
		produto.setCodigo(codigo);
		produto.setDesc(descricao);
		produto.setQuantidadeEstoque(quantidade);
		produto.setPreco(preco);
		
		produtoRepository.save(produto);
		
		return "Salvo com sucesso";
	}

	@Transactional(readOnly = true)
	public @ResponseBody Iterable<Produto> findAll() {
		return produtoRepository.findAll();
	}

//	@Transactional
//	public void addAll(Collection<Produto> produtos) {
//		for (Produto produto : produtos) {
//			produtoRepository.save(produto);
//		}
//	}
	
	@Transactional(readOnly = true)
	@GetMapping(path = "/nome")
	public @ResponseBody List<Produto> findByName(@RequestParam String descricao) {
		return produtoRepository.findByName(descricao);
	}

//	@Transactional(readOnly = true)
//	public Optional<Produto> findById(int id) {
//		return produtoRepository.findById(id);
//	}
}
