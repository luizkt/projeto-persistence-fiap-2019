package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.entity.node.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long>{

	@Query("MATCH (e:Pedido) WHERE e.ID = :idcliente RETURN e")
	public List<Pedido> findAllOrdersOfACostumer(@Param("idcliente") Long idcliente);
	
	@Query("MATCH (e:Pedido) WHERE e.CODIGO = :codigo RETURN e")
	public List<Pedido> findOrderByCode(@Param("codigo") Long codigo);
	
	
}
