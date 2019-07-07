package br.com.fiap.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.entity.node.Pedido;
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
}
