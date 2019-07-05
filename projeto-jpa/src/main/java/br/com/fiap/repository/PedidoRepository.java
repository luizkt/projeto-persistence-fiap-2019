package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.fiap.entity.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer>{

	@Query("SELECT p FROM PEDIDO p WHERE p.IDCLIENTE = :idcliente")
	public List<Pedido> findAllOrdersOfACostumer(@Param("idcliente") Integer idcliente);
	
	@Query("SELECT p FROM PEDIDO p WHERE p.CODIGO = :codigo")
	public List<Pedido> findOrderByCode(@Param("codigo") Integer codigo);
	
	
}
