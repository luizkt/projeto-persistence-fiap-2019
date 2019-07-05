package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.fiap.entity.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer>{

	@Query("SELECT p FROM PRODUTO p WHERE p.DESCRICAO = :descricao")
	public List<Produto> findByName(@Param("descricao") String descricao);
		
	@Query("SELECT p FROM PRODUTO p WHERE p.CODIGO = :codigo")
	public List<Produto> findByCode(@Param("codigo") String codigo);
		
	
}
