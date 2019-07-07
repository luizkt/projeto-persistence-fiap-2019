package br.com.fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan("br.com.fiap")
@EnableJpaRepositories(basePackages={"br.com.fiap.repository"})
@EntityScan(basePackages="br.com.fiap.entity")
@ComponentScan(basePackages={"br.com.fiap"})
public class ProjetoJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoJpaApplication.class, args);
	}
}
