package com.pabloramon.aulauml;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pabloramon.aulauml.domain.Categoria;
import com.pabloramon.aulauml.domain.Cidade;
import com.pabloramon.aulauml.domain.Cliente;
import com.pabloramon.aulauml.domain.Endereco;
import com.pabloramon.aulauml.domain.Estado;
import com.pabloramon.aulauml.domain.Pagamento;
import com.pabloramon.aulauml.domain.PagamentoComBoleto;
import com.pabloramon.aulauml.domain.PagamentoComCartao;
import com.pabloramon.aulauml.domain.Pedido;
import com.pabloramon.aulauml.domain.Produto;
import com.pabloramon.aulauml.domain.enums.EstadoPagamento;
import com.pabloramon.aulauml.domain.enums.TipoCliente;
import com.pabloramon.aulauml.repositories.CategoriaRepository;
import com.pabloramon.aulauml.repositories.CidadeRepository;
import com.pabloramon.aulauml.repositories.ClienteRepository;
import com.pabloramon.aulauml.repositories.EnderecoRepository;
import com.pabloramon.aulauml.repositories.EstadoRepository;
import com.pabloramon.aulauml.repositories.PagamentoRepository;
import com.pabloramon.aulauml.repositories.PedidoRepository;
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
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
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
		
		
		Cliente cli1 = new Cliente(null, "Joaquim Dunga", "joaca@dunga.com","000212554445", TipoCliente.PESSOAFISICA );
		cli1.getTelefones().addAll(Arrays.asList("83996554477", "8399665544"));
		
		Endereco e1 = new Endereco(null, "Rua Mata Grande", "121", "Apto 101", "Alagoinha", "58055444", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Manauara Zé", "21", "Casa B", "Estelinha", "21055422", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("19/01/1991 22:21"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("19/01/1987 16:15"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 5);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("19/01/1987 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	
	}

}
