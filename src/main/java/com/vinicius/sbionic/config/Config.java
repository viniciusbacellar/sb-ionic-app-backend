package com.vinicius.sbionic.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.vinicius.sbionic.domain.Categoria;
import com.vinicius.sbionic.domain.Cidade;
import com.vinicius.sbionic.domain.Cliente;
import com.vinicius.sbionic.domain.Endereco;
import com.vinicius.sbionic.domain.Estado;
import com.vinicius.sbionic.domain.ItemPedido;
import com.vinicius.sbionic.domain.Pagamento;
import com.vinicius.sbionic.domain.PagamentoComBoleto;
import com.vinicius.sbionic.domain.PagamentoComCartao;
import com.vinicius.sbionic.domain.Pedido;
import com.vinicius.sbionic.domain.Produto;
import com.vinicius.sbionic.domain.enums.EstadoPagamento;
import com.vinicius.sbionic.domain.enums.TipoCliente;
import com.vinicius.sbionic.repositories.CategoriaRepository;
import com.vinicius.sbionic.repositories.CidadeRepository;
import com.vinicius.sbionic.repositories.ClienteRepository;
import com.vinicius.sbionic.repositories.EnderecoRepository;
import com.vinicius.sbionic.repositories.EstadoRepository;
import com.vinicius.sbionic.repositories.ItemPedidoRepository;
import com.vinicius.sbionic.repositories.PagamentoRepository;
import com.vinicius.sbionic.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository repoPed;
	@Autowired
	private PagamentoRepository repoPag;
	@Autowired
	private ItemPedidoRepository repoItemPed;

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		repoCat.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
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

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com",
				"36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 203",
				"Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800",
				"Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		repoCli.saveAll(Arrays.asList(cli1));
		repoEnd.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,
				sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		repoPed.saveAll(Arrays.asList(ped1, ped2));
		repoPag.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		repoItemPed.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
