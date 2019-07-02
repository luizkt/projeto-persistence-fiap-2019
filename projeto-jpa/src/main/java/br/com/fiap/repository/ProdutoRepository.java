package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	@Query("SELECT p FROM PRODUTO p WHERE p.DESCRICAO = :descricao")
	public List<Produto> findByName(@Param("descricao") String descricao);
		
	@Query("SELECT p FROM PRODUTO p WHERE p.CODIGO = :codigo")
	public List<Produto> findByCode(@Param("codigo") String codigo);
		
	
}
