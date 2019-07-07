package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.entity.node.Cliente;
import br.com.fiap.entity.node.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

//	@Query("MATCH (p:Pedido) WHERE p.rg = :rg RETURN p")
	@Query("MATCH (p:Pedido)"
			+ "<-[:Pedido]-(:realizouOsPedidos)<-[:Cliente]-(c:Cliente)\r\n" + 
			"RETURN distinct p;")
	public List<Pedido> findAllOrdersOfACostumer(@Param("Cliente") Cliente cliente);

	
	@Query("MATCH (p:Pedido) WHERE p.codigo = :codigo RETURN p")
	public List<Pedido> findOrderByCode(@Param("codigo") String codigo);

}
