package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.entityNode.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Integer>{

	@Query("")
	public List<Pedido> findAllOrdersOfACostumer(@Param("idcliente") Integer idcliente);
	
	@Query("")
	public List<Pedido> findOrderByCode(@Param("codigo") Integer codigo);
	
	
}
