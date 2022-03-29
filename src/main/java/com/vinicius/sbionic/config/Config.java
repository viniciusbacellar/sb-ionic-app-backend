package com.vinicius.sbionic.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.vinicius.sbionic.domain.Categoria;
import com.vinicius.sbionic.repositories.CategoriaRepository;

@Configuration
public class Config implements CommandLineRunner{

	@Autowired
	private CategoriaRepository repo;
	
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		repo.saveAll(Arrays.asList(cat1, cat2));
		
	}

}
