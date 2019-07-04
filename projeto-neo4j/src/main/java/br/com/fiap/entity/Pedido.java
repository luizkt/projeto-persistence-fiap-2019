package br.com.fiap.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import br.com.fiap.nodeEntity.Cliente;

@NodeEntity
public class Pedido {

	@Id
	@GeneratedValue
	private Integer pedidoId;
	private String desc;
	private String codigo;

	@Relationship(type = "ORDER_CONTAINS")
	private Set<Produto> produtos = new HashSet<>();

	public void orderContains(Produto produto) {
		if (produtos == null) {
			produtos = new HashSet<Produto>();
		}
		produtos.add(produto);
	}

	
	private Cliente cliente;

	public Integer getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos_pedidos) {
		this.produtos = produtos_pedidos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
