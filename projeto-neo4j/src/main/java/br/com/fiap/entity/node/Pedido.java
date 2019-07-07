package br.com.fiap.entity.node;

import java.util.HashSet;
import java.util.Set;

import javax.management.relation.Relation;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import br.com.fiap.entity.relationship.Item;

@NodeEntity
public class Pedido {

	@Id
	@GeneratedValue
	private Long pedidoId;
	private String desc;
	private String codigo;
	
	@Relationship(type = "PEDIDO_POSSUI")
	private Set<Item> itens;

	public void pedidoContem(Item item) {
		if (itens == null) {
			itens = new HashSet<Item>();
		}
		itens.add(item);
	}

	@Relationship(type = Relationship.INCOMING)
	private Cliente cliente;
	
	public void pedidoPertenceAo(Cliente cliente)
	{
		if(cliente == null)
		{
			cliente = new Cliente();
		}
		this.cliente = cliente;
	}
	
	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
