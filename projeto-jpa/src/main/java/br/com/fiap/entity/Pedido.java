package br.com.fiap.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PEDIDO", catalog = "pdv", uniqueConstraints = { @UniqueConstraint(columnNames = "CODIGO_PEDIDO") })
@NamedQuery(name = "Pedido.findAll", query = "select p from Pedido p")
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "DESCRICAO")
	private String desc;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "PRODUTOS_PEDIDOS", catalog = "pdv", joinColumns = 
	{ 
		@JoinColumn(name = "PRODUTO_ID", nullable = false, updatable=false) })
	private Set<Produto> produtos_pedidos = new HashSet<>();
	
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="IDCLIENTE")
	private Cliente cliente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<Produto> getProdutos_pedidos() {
		return produtos_pedidos;
	}

	public void setProdutos_pedidos(Set<Produto> produtos_pedidos) {
		this.produtos_pedidos = produtos_pedidos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	


	
	
}
