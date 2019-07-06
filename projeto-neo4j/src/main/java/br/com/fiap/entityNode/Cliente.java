package br.com.fiap.entityNode;


import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Cliente {

	@Id
	@GeneratedValue
	private Integer clienteId;

	private String nome;

	private String rg;
	
	
	@Relationship(type = "REALIZA", direction = Relationship.UNDIRECTED)
	private Set<Pedido> pedidos;
	
	public void compras(Pedido pedido)
	{
		if(pedidos == null)
		{
			pedidos = new HashSet<Pedido>();
		}
		
		pedidos.add(pedido);
	}
	
	@Relationship(type ="RESIDE")
	private Endereco endereco;
	
	
	@SuppressWarnings("unused")
	public Cliente()
	{
		
	}
	
	
	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
