package br.com.fiap.entity.node;

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
	private Long clienteId;
	private String nome;
	private String rg;
//	private String rua;
//	private String bairro;
//	private String numero;
//	private String cidade;
//	private String estado;
//	private String cep;
//	private String pais;
	
	
	@Relationship(type = "REALIZA", direction = Relationship.UNDIRECTED)
	private Set<Pedido> pedidos;

	public void realizouOsPedidos(Pedido pedido) {
		if (pedidos == null) {
			pedidos = new HashSet<Pedido>();
		}

		pedidos.add(pedido);
	}

	@Relationship(type = "resideEm")
	private Endereco endereco;

	@SuppressWarnings("unused")
	public Cliente() {

	}

	public void resideEm(Endereco endereco) {
		if (endereco == null) {
			endereco = new Endereco();
		}
		this.endereco = endereco;
	}

	public Cliente(String nome, String rg) {
		this.nome = nome;
		this.rg = rg;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
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
	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
	}

//	public String getRua() {
//		return rua;
//	}
//
//	public void setRua(String rua) {
//		this.rua = rua;
//	}
//
//	public String getBairro() {
//		return bairro;
//	}
//
//	public void setBairro(String bairro) {
//		this.bairro = bairro;
//	}
//
//	public String getNumero() {
//		return numero;
//	}
//
//	public void setNumero(String numero) {
//		this.numero = numero;
//	}
//
//	public String getCidade() {
//		return cidade;
//	}
//
//	public void setCidade(String cidade) {
//		this.cidade = cidade;
//	}
//
//	public String getEstado() {
//		return estado;
//	}
//
//	public void setEstado(String estado) {
//		this.estado = estado;
//	}
//
//	public String getCep() {
//		return cep;
//	}
//
//	public void setCep(String cep) {
//		this.cep = cep;
//	}
//
//	public String getPais() {
//		return pais;
//	}
//
//	public void setPais(String pais) {
//		this.pais = pais;
//	}

}
