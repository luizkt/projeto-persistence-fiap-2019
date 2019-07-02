package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	@Query("SELECT p FROM PEDIDO p WHERE p.IDCLIENTE = :idcliente")
	public List<Pedido> findAllOrdersOfACostumer(@Param("idcliente") Integer idcliente);
	
	
}
