package br.com.fiap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fiap.entity.Pedido;
import br.com.fiap.repository.PedidoRepository;

@Controller
@RequestMapping(path = "/pedido")
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Transactional
	@RequestMapping(path = "/add", method = RequestMethod.POST)	
	public void add(Pedido pedido) {
		// TODO achar um meio de utilizar a URL para adicionar pedidos
		pedidoRepository.save(pedido);
	}

	@Transactional(readOnly = true)
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public @ResponseBody Iterable<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

//	@Transactional
//	public void addAll(Collection<Pedido> pedidos) {
//		for (Pedido pedido : pedidos) {
//			pedidoRepository.save(pedido);
//		}
//	}
//
//	@Transactional(readOnly = true)
//	public Optional<Pedido> findById(int id) {
//		return pedidoRepository.findById(id);
//	}
}
