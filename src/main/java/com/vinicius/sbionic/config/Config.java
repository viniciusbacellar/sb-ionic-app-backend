package com.vinicius.sbionic.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.vinicius.sbionic.domain.Categoria;
import com.vinicius.sbionic.domain.Produto;
import com.vinicius.sbionic.repositories.CategoriaRepository;
import com.vinicius.sbionic.repositories.ProdutoRepository;

@Configuration
public class Config implements CommandLineRunner{

	@Autowired
	private CategoriaRepository repoCat;
	
	@Autowired
	ProdutoRepository repoProd;
	
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		repoCat.saveAll(Arrays.asList(cat1, cat2));
		repoProd.saveAll(Arrays.asList(p1, p2, p3));
		
	}

}
