package br.com.fiap.jpa.projetojpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan("br.com.fiap")
@ImportResource({"classpath*:spring.xml"})
public class ProjetoJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoJpaApplication.class, args);
	}
}
