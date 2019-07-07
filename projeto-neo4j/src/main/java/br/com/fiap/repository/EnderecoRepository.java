package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.entity.node.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {
	
	@Query("MATCH (e:Endereco) WHERE e.RUA = :rua RETURN e")
 //	@Query("SELECT e FROM ENDERECO p WHERE e.RUA = :rua")
	public List<Endereco> findByStreet(@Param("rua") String rua);
	
	@Query("MATCH (e:Endereco) WHERE e.CEP = :cep RETURN e")
//	@Query("SELECT e FROM ENDERECO p WHERE e.CEP = :cep")
	public List<Endereco> findByPostalCode(@Param("cep") String cep);

	@Query("MATCH (e:Endereco) WHERE e.BAIRRO = :bairro RETURN e")
//	@Query("SELECT e FROM ENDERECO p WHERE e.bairro = :bairro")
	public List<Endereco> findByDistrict(@Param("bairro") String bairro);
	
	@Query("MATCH (e:Endereco) WHERE e.CIDADE = :cidade RETURN e")
//	@Query("SELECT e FROM ENDERECO p WHERE e.CIDADE = :cidade")
	public List<Endereco> findByCity(@Param("cidade") String cidade);
	
	@Query("MATCH (e:Endereco) WHERE e.ESTADO = :estado RETURN e")
//	@Query("SELECT e FROM ENDERECO p WHERE e.ESTADO = :estado")
	public List<Endereco> findByState(@Param("estado") String estado);
	
	@Query("MATCH (e:Endereco) WHERE e.PAIS = :pais RETURN e")
//	@Query("SELECT e FROM ENDERECO p WHERE e.PAIS = :pais")
	public List<Endereco> findByCountry(@Param("pais") String pais);
	
}
