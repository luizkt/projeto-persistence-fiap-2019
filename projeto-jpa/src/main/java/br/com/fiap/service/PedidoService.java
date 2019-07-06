package br.com.fiap.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Pedido;
import br.com.fiap.entity.Produto;
import br.com.fiap.model.PedidoJson;
import br.com.fiap.repository.ClienteRepository;
import br.com.fiap.repository.PedidoRepository;
import br.com.fiap.repository.ProdutoRepository;

@Controller
@RequestMapping(path = "/pedido")
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> add(@Valid @RequestBody Map<String, Object> payload) {

		try {

			ObjectMapper mapper = new ObjectMapper();
			PedidoJson pedidoJson = mapper.convertValue(payload, PedidoJson.class);
			Pedido pedido = new Pedido();
			
			Cliente cliente = clienteRepository.findByDocument(pedidoJson.getRg()).get(0);
			Set<Produto> produtos = new HashSet<>();
			
			for(int i = 0 ; i < pedidoJson.getProdutos().size() ; i++) {
				Produto produto = produtoRepository.findByName(pedidoJson.getProdutos().get(i).getDescricao()).get(0);
				
				produto.setQuantidadeEstoque(pedidoJson.getProdutos().get(i).getQuantidade());
				
				produtos.add(produto);
			}
			
			pedido.setDesc(pedidoJson.getDescricao());
			pedido.setCliente(cliente);
			pedido.setProdutos_pedidos(produtos);
			
			pedidoRepository.save(pedido);

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
	@RequestMapping(path = "/cliente/{cliente}", method = RequestMethod.GET)
	@ResponseBody
	public List<Pedido> findAllOrdersOfACostumer(@PathVariable String rg) {
		return pedidoRepository.findAllOrdersOfACostumer(Integer.parseInt(rg));
	}

	// @Transactional
	// public void addAll(Collection<Pedido> pedidos) {
	// for (Pedido pedido : pedidos) {
	// pedidoRepository.save(pedido);
	// }
	// }
	//
	// @Transactional(readOnly = true)
	// public Optional<Pedido> findById(int id) {
	// return pedidoRepository.findById(id);
	// }
}
