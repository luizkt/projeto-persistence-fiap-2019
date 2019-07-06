package br.com.fiap.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.entityNode.Pedido;
import br.com.fiap.repository.PedidoRepository;

@Component
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Transactional
	public void add(Pedido pedido) {
		pedidoRepository.save(pedido);
	}

	@Transactional(readOnly = true)
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	@Transactional
	public void addAll(Collection<Pedido> pedidos) {
		for (Pedido pedido : pedidos) {
			pedidoRepository.save(pedido);
		}
	}

	@Transactional(readOnly = true)
	public Optional<Pedido> findById(int id) {
		return pedidoRepository.findById(id);
	}
}
