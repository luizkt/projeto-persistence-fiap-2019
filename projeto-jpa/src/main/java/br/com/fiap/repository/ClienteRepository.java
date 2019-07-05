package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.fiap.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

	@Query("SELECT c FROM CLIENTE c WHERE c.NOME = :nome")
	public List<Cliente> findByName(@Param("nome") String nome);
	
	@Query("SELECT c FROM CLIENTE c WHERE c.RG = :rg")
	public List<Cliente> findByDocument(@Param("rg") String rg);
	
	List<Cliente> findClienteByName(@Param("nome") String s);
		
}
