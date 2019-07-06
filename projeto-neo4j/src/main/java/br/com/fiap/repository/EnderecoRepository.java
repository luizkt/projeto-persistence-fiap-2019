package br.com.fiap.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.entityNode.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {
	
	@Query("SELECT e FROM ENDERECO p WHERE e.RUA = :rua")
	public List<Endereco> findByStreet(@Param("rua") String rua);
	
	@Query("SELECT e FROM ENDERECO p WHERE e.CEP = :cep")
	public List<Endereco> findByPostalCode(@Param("cep") String cep);

	@Query("SELECT e FROM ENDERECO p WHERE e.bairro = :bairro")
	public List<Endereco> findByDistrict(@Param("bairro") String bairro);
	
	@Query("SELECT e FROM ENDERECO p WHERE e.CIDADE = :cidade")
	public List<Endereco> findByCity(@Param("cidade") String cidade);
	
	@Query("SELECT e FROM ENDERECO p WHERE e.ESTADO = :estado")
	public List<Endereco> findByState(@Param("estado") String estado);
	
	@Query("SELECT e FROM ENDERECO p WHERE e.PAIS = :pais")
	public List<Endereco> findByCountry(@Param("pais") String pais);
	
		
}
