package br.com.fiap.jpa.projetojpa;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.fiap.entity.Produto;
import br.com.fiap.service.ProdutoService;

@ComponentScan("br.com.fiap")
@ImportResource({"classpath*:spring.xml"})
public class ProjetoJpaApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		 ProdutoService produtoService = ctx.getBean(ProdutoService.class);
				 
		Produto produto = new Produto();
		
		produto.setDesc("produto 1");

		produtoService.add(produto);
		
		ctx.close();
	}
}
