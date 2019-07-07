package br.com.fiap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import br.com.fiap.entity.node.Cliente;
import br.com.fiap.repository.ClienteRepository;

@SpringBootApplication
@EntityScan("br.com.fiap.entity")
@EnableNeo4jRepositories(basePackages = "br.com.fiap.repository")
// @EnableNeo4jRepositories(basePackageClasses = ClienteRepository.class)
public class ProjetoNeo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoNeo4jApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(final ClienteRepository clienteRepository) {
		return new CommandLineRunner() {
			public void run(String... args) throws Exception {

				// clienteRepository.deleteAll();

				Cliente p1 = new Cliente("asd", "123");
				// p1.setClienteId(1);
				// Cliente p2 = new Cliente();
				// Cliente p3 = new Cliente();

				// List<Cliente> time = Arrays.asList(p1, p2, p3);

				// time.stream().forEach(new Consumer<Cliente>() {
				// public void accept(Cliente cliente) {
				// System.out.println("\t" + cliente.toString());
				// }
				// });

				// Endereco end = new Endereco("av alguma coisa", "123");

				// p1.resideEm(end);

				clienteRepository.save(p1);
				// clienteRepository.save(p2);
				// clienteRepository.save(p3);

				p1 = clienteRepository.findByName(p1.getNome()).get(0);

				clienteRepository.save(p1);

				// p2 = clienteRepository.findByName(p2.getNome()).get(0);
				// p2.worksWith(p3); // Não é necessário ligar p1 com p2, pois já sabemos disso
				// na relação anterior.
				// clienteRepository.save(p2);

				// time.stream().forEach(new Consumer<Cliente>() {
				// public void accept(Cliente cliente) {
				// System.out.println("\t" +
				// clienteRepository.findByNome(cliente.getNome()).toString());
				// }
				// });
			}
		};
	}

}
