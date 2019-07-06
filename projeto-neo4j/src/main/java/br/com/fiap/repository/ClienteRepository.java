package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.entity.node.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	@Query("")
	public List<Cliente> findByName(@Param("nome") String nome);
	
	@Query("")
	public List<Cliente> findByDocument(@Param("rg") String rg);
	
	
	@Query("")
	public List<Cliente> findAll();
	
}
