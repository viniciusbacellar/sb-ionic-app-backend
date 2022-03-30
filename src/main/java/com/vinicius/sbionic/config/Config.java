package com.vinicius.sbionic.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.vinicius.sbionic.domain.Categoria;
import com.vinicius.sbionic.domain.Cidade;
import com.vinicius.sbionic.domain.Cliente;
import com.vinicius.sbionic.domain.Endereco;
import com.vinicius.sbionic.domain.Estado;
import com.vinicius.sbionic.domain.Produto;
import com.vinicius.sbionic.domain.enums.TipoCliente;
import com.vinicius.sbionic.repositories.CategoriaRepository;
import com.vinicius.sbionic.repositories.CidadeRepository;
import com.vinicius.sbionic.repositories.ClienteRepository;
import com.vinicius.sbionic.repositories.EnderecoRepository;
import com.vinicius.sbionic.repositories.EstadoRepository;
import com.vinicius.sbionic.repositories.ProdutoRepository;

@Configuration
public class Config implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repoCat;
	@Autowired
	private ProdutoRepository repoProd;
	@Autowired
	private CidadeRepository repoCid;
	@Autowired
	private EstadoRepository repoEst;
	@Autowired
	private ClienteRepository repoCli;
	@Autowired
	private EnderecoRepository repoEnd;

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

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Saõ Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		repoEst.saveAll(Arrays.asList(est1, est2));
		repoCid.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		repoCli.saveAll(Arrays.asList(cli1));
		repoEnd.saveAll(Arrays.asList(e1, e2));
		
	}

}
