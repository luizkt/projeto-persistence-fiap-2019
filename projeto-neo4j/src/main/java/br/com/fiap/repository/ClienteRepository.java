package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.entity.node.Cliente;

//https://neo4j.com/developer/guide-sql-to-cypher/
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	@Query("MATCH (c:Cliente) WHERE c.NOME = :nome RETURN c")
//	@Query("SELECT e FROM CLIENTE p WHERE e.NOME = :nome")
	public List<Cliente> findByName(@Param("nome") String nome);
	
	@Query("MATCH (c:Cliente) WHERE c.RG = :rg RETURN c")
//	@Query("SELECT e FROM CLIENTE p WHERE e.RG = :rg")
	public List<Cliente> findByDocument(@Param("rg") String rg);

	
}
