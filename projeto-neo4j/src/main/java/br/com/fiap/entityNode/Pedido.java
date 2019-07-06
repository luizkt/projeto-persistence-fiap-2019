package br.com.fiap.entityNode;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import br.com.fiap.entityRelationship.Item;

@NodeEntity
public class Pedido {

	@Id
	@GeneratedValue
	private Integer pedidoId;
	private String desc;
	private String codigo;

	@Relationship(type = "PEDIDO_POSSUI")
	private Set<Item> itens;

	public void orderContains(Item item) {
		if (itens == null) {
			itens = new HashSet<Item>();
		}
		itens.add(item);
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

	public Set<Item> getProdutos() {
		return itens;
	}

	public void setProdutos(Set<Item> itens) {
		this.itens = itens;
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
