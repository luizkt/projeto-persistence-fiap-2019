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

	@Query("MATCH (c:Cliente {nome={0}}) WHERE c.nome = {nome} RETURN c")
	// @Query("SELECT e FROM CLIENTE p WHERE e.NOME = :nome")
	public List<Cliente> findByNome(@Param("nome") String nome);

	@Query("MATCH (c:Cliente) WHERE c.rg = '{0}' RETURN c")
	public List<Cliente> findByRg(String rg);

}
