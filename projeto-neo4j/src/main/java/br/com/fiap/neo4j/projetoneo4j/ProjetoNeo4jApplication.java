package br.com.fiap.neo4j.projetoneo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EntityScan("br.com.fiap.*")
@EnableNeo4jRepositories
public class ProjetoNeo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoNeo4jApplication.class, args);
	}

}
