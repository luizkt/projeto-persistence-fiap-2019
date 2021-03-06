package br.com.fiap.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.entity.node.Cliente;
import br.com.fiap.entity.node.Pedido;
import br.com.fiap.entity.node.Produto;
import br.com.fiap.entity.relationship.Item;
import br.com.fiap.model.PedidoJson;
import br.com.fiap.model.ProdutoJson;
import br.com.fiap.repository.ClienteRepository;
import br.com.fiap.repository.ItemRepository;
import br.com.fiap.repository.PedidoRepository;
import br.com.fiap.repository.ProdutoRepository;

@RestController
@RequestMapping(path = "/pedido")
@Component
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	

	@Transactional
	public void add(Pedido pedido) {
		pedidoRepository.save(pedido);
	}

	@Transactional(readOnly = true)
	public Iterable<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	@Transactional
	public void addAll(Collection<Pedido> pedidos) {
		for (Pedido pedido : pedidos) {
			pedidoRepository.save(pedido);
		}
	}

	@Transactional(readOnly = true)
	public Optional<Pedido> findById(Long id) {
		return pedidoRepository.findById(id);
	}
	
	
	
	@Transactional
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> add(@Valid @RequestBody Map<String, Object> payload) {

		try {

			ObjectMapper mapper = new ObjectMapper();
			PedidoJson pedidoJson = mapper.convertValue(payload, PedidoJson.class);
			Pedido pedido = new Pedido();
			Item item = new Item();
			
			Cliente cliente = clienteRepository.findByRg(pedidoJson.getRg()).get(0);
			
			pedido.setDesc(pedidoJson.getDescricao());
			pedido.pedidoPertenceAo(cliente);
			pedido.setCodigo(pedidoJson.getCodigo());
			
			pedidoRepository.save(pedido);
			
			for(int i = 0 ; i < pedidoJson.getProdutos().size() ; i++) {
				Produto produto = produtoRepository.findByCodigo(pedidoJson.getProdutos().get(i).getCodigo()).get(0);
				
				item.setPedido(pedido);
				item.setProduto(produto);
				item.setQuantidade(pedidoJson.getProdutos().get(i).getQuantidade());
				
				itemRepository.save(item);
				
			}
			
			
			
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
			String body = "{\"Mensagem\":\"Pedido adicionado com sucesso\"}";

			return new ResponseEntity<>(body, headers, HttpStatus.CREATED);

		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
			String body = "{\"Mensagem\":\"Ocorreu um erro\", \"Execeção\":" + e.getMessage() + "}";

			return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);
		}

	}
	
	
	@Transactional(readOnly = true)
	@RequestMapping(path = "/cliente/{rg}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<PedidoJson>> findAllOrdersOfACostumer(@PathVariable String rg) {
		
		Cliente cliente = clienteRepository.findByRg(rg).get(0);
		
		List<Pedido> pedidos = pedidoRepository.findAllOrdersOfACostumer(cliente);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
		
		List<PedidoJson> pedidosJson = new ArrayList<>();
	    for (Pedido pedido : pedidos) {
	        PedidoJson pedidoJson = new PedidoJson();
	        pedidoJson.setCodigo(pedido.getCodigo());
	        pedidoJson.setDescricao(pedido.getDesc());
	        pedidoJson.setRg(pedido.getCliente().getRg());
	        
	        List<ProdutoJson> produtosJson = new ArrayList<>();
	        
	        pedido.getItens().forEach(item -> {
	        	Produto produto = item.getProduto();
	        	ProdutoJson produtoJson = new ProdutoJson();
	        	
	        	produtoJson.setCodigo(produto.getCodigo());
	        	produtoJson.setDescricao(produto.getDesc());
	        	produtoJson.setPreco(produto.getPreco());
	        	produtoJson.setQuantidade(item.getQuantidade());
	        	
	        	produtosJson.add(produtoJson);
	        	
	        });
	        
	        pedidoJson.setProdutos(produtosJson);
	        pedidosJson.add(pedidoJson);
	    }
		
		return new ResponseEntity<>(pedidosJson, headers, HttpStatus.OK);
	}
	
	@Transactional(readOnly = true)
	@RequestMapping(path = "/codigo/{codigo}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> findOrderByCode(@PathVariable String codigo) {
		
		Pedido pedido = pedidoRepository.findOrderByCode(codigo).get(0);
		
		PedidoJson pedidoJson = new PedidoJson();
		
		pedidoJson.setCodigo(pedido.getCodigo());
		pedidoJson.setDescricao(pedido.getDesc());
		pedidoJson.setRg(pedido.getCliente().getRg());
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
		String body = "{"
							+ "\"codigo\":\"" + pedidoJson.getCodigo() + "\","
							+ "\"descricao\":\"" + pedidoJson.getDescricao() + "\","
							+ "\"rg\":\"" + pedidoJson.getRg() + "\""
					+ "}";

		return new ResponseEntity<>(body, headers, HttpStatus.OK);
		
	}
	
	
	
}
