package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.entity.node.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer>{

	@Query("MATCH (e:Endereco) WHERE e.DESCRICAO = :descricao RETURN e;")
//	@Query("SELECT p FROM PRODUTO p WHERE p.DESCRICAO = :descricao")
	public List<Produto> findByName(@Param("descricao") String descricao);
		
	@Query("MATCH (PRODUTO) WHERE e.CODIGO = :codigo RETURN e;")
//	@Query("SELECT p FROM PRODUTO p WHERE p.CODIGO = :codigo")
	public List<Produto> findByCode(@Param("codigo") String codigo);
		
	
}
