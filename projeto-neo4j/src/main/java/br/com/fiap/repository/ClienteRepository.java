package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.nodeEntity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("SELECT c FROM CLIENTE c WHERE c.NOME = :nome")
	public List<Cliente> findByName(@Param("nome") String nome);
	
	@Query("SELECT c FROM CLIENTE c WHERE c.RG = :rg")
	public List<Cliente> findByDocument(@Param("rg") String rg);
	
}
