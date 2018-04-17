package com.pabloramon.aulauml;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pabloramon.aulauml.domain.Categoria;
import com.pabloramon.aulauml.domain.Cidade;
import com.pabloramon.aulauml.domain.Estado;
import com.pabloramon.aulauml.domain.Produto;
import com.pabloramon.aulauml.repositories.CategoriaRepository;
import com.pabloramon.aulauml.repositories.CidadeRepository;
import com.pabloramon.aulauml.repositories.EstadoRepository;
import com.pabloramon.aulauml.repositories.ProdutoRepository;

@SpringBootApplication
public class AulaModelagemUmlApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AulaModelagemUmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Notebook", 2500.00);
		Produto p2 = new Produto(null, "Impressora", 500.00);
		Produto p3 = new Produto(null, "Mouse", 70.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
					
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null,"Paraíba");
		Estado est2 = new Estado(null,"Amazonas");
		
		Cidade c1 = new Cidade(null, "Campina Grande", est1);
		Cidade c2 = new Cidade(null, "Manaus", est2);
		Cidade c3 = new Cidade(null, "Patos", est1);
		
		est1.getCidades().addAll(Arrays.asList(c1,c3));
		est2.getCidades().addAll(Arrays.asList(c2));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		

	}

}
